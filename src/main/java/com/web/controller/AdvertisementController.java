package com.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.web.bean.BO.ResultBO;
import com.web.bean.BO.UserSessionBO;
import com.web.bean.DO.AdvAdvert;
import com.web.bean.DO.AdvBusiness;
import com.web.bean.DO.AdvRecordDO;
import com.web.bean.VO.AdvAdvertVO;
import com.web.config.ProjectConfig;
import com.web.interceptor.UserWebInterceptor;
import com.web.oss.OSSUploadFile;
import com.web.service.IAdvRecordService;
import com.web.service.IAdvertismentService;
import com.web.service.IBusinessService;
import com.web.util.FileUtil;
import com.web.util.JsonUtil;
import com.web.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 首页广告
 * @author chxy
 */
@Controller
@RequestMapping("advert")
public class AdvertisementController {

    @Autowired
    private IAdvertismentService advertismentService;

    @Autowired
    private IBusinessService bussinessService;

    @Autowired
    private IAdvRecordService advRecordService;

    /**
     * 图片列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public String list(HttpServletRequest request){
        UserSessionBO userSessionBO = UserWebInterceptor.getUserSessionBO(request);
        int userId = 0;
        if (userSessionBO != null) {
            userId = userSessionBO.getUserId();
        }

        String title = request.getParameter("title");
        AdvAdvert advAdvert = new AdvAdvert();
        advAdvert.setTitle(title);
        //todo 查询条件给子查询
        List<AdvAdvertVO> list = advertismentService.select(advAdvert, userId);
        return JsonUtil.imageListToJson(list);
    }

    /**
     * 商家后台列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "businessList", method = RequestMethod.GET)
    public String businessList(HttpServletRequest request, UserSessionBO userSessionBO){
        String title = request.getParameter("title");
        String addTime = request.getParameter("addTime");
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        AdvAdvert advAdvert = new AdvAdvert();
        advAdvert.setTitle(title);
        int page = ProjectConfig.FRIST_PAGE ;
        int limit = ProjectConfig.PAGE_SIZE;
        if (pageStr!=""||pageStr!=null){
            page = Integer.parseInt(pageStr);
        }
        if (limitStr!=""||limitStr!=null){
            limit = Integer.parseInt(limitStr);
        }

        PageInfo pageInfo = advertismentService.selectPage(advAdvert,page, limit);

        return JsonUtil.requestListToJson(pageInfo.getList(), (int) pageInfo.getTotal());

    }

    /**
     * 添加广告
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addAdvert", method = RequestMethod.POST)
    public ResultBO addAdvert(HttpServletRequest request, @RequestParam String url,
                                @RequestParam String content,
                                @RequestParam String title,
                                @RequestParam Integer mustClick,
                                @RequestParam String pic,
                                @RequestParam BigDecimal wasteToken,
                                @RequestParam BigDecimal clickToken,
                                UserSessionBO userSessionBO){
        AdvBusiness advBusiness = bussinessService.selectByUserId(userSessionBO.getUserId());
        AdvAdvert advAdvert = AdvAdvert.builder()
                .add_time(new Date())
                .businessId(advBusiness.getId())
                .content(content)
                .title(title)
                .url(url)
                .pic(pic)
                .must_click(mustClick)
                .clickToken(clickToken)
                .waste_token(wasteToken)
                .count_click(0)
                .build();
        boolean boo = advertismentService.insert(advAdvert);
        if (!boo){
            return Results.fail("添加广告失败!");
        }
        return Results.success("添加广告成功!");
    }

    /**
     *  上传图片
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public JSONObject upload(HttpServletRequest request, MultipartFile file){
        JSONObject jsonObject = new JSONObject();
        String url= "";
        try {
            File file2 = FileUtil.transferTo(file);
            url= OSSUploadFile.uploadFile(file2);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (url!="" && url!=null){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "上传图片成功!");
            jsonObject.put("path", url);
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传图片失败!");
        }
        return jsonObject;
    }

    /**
     * 点击广告
     * @param request
     * @return
     */
    @RequestMapping(value = "clickAdvert", method = RequestMethod.POST)
    @ResponseBody
    public ResultBO clickAdvert(HttpServletRequest request, UserSessionBO userSessionBO, @RequestParam Integer id){
        AdvAdvert advAdvert = advertismentService.selectOne(id);
        if (advAdvert.getStatus()!=1){
            return Results.fail(3, "图片的状态不对!");
        }
        int status = advertismentService.clickAdv(advAdvert);
        if (status == -1){
            return Results.fail(2, "已经超出点击范围");
        }

        AdvRecordDO advRecordDO = advRecordService.selectRecord(id, userSessionBO.getUserId());
        if (advRecordDO != null) {
            return Results.fail(advAdvert.getUrl(), -1 , "您已经点击过!");
        }

        if (status == 1){
            boolean boo = bussinessService.changeToken(advAdvert.getId(),advAdvert.getClickToken(),advAdvert.getBusinessId(), userSessionBO.getUserId());
            if (!boo){
                return Results.fail("点击失败!");
            }
            return Results.success(advAdvert.getUrl(), "点击成功!");
        } else {
            return Results.fail(5,"未知错误");
        }

    }

}
