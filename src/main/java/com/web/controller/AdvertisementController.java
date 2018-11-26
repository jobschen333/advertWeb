package com.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.web.bean.DO.AdvAdvert;
import com.web.oss.OSSUploadFile;
import com.web.service.IAdvertismentService;
import com.web.service.IBussinessService;
import com.web.util.FileUtil;
import com.web.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 广告
 * @author chxy
 */
@Controller
@RequestMapping("advert")
public class AdvertisementController {

    @Autowired
    private IAdvertismentService advertismentService;

    @Autowired
    private IBussinessService bussinessService;

    /**
     * 图片列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public String list(HttpServletRequest request){
        String title = request.getParameter("title");
        AdvAdvert advAdvert = new AdvAdvert();
        advAdvert.setTitle(title);
        List<AdvAdvert> list = advertismentService.select(advAdvert);
        return JsonUtil.imageListToJson(list);
    }

    /**
     * 商家后台列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "businessList", method = RequestMethod.GET)
    public String businessList(HttpServletRequest request){
        String title = request.getParameter("title");
        String addTime = request.getParameter("addTime");
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        AdvAdvert advAdvert = new AdvAdvert();
        advAdvert.setTitle(title);
        int page = 1 ;
        int limit = 10;
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
    public JSONObject addAdvert(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String url = request.getParameter("url");
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        String mustClick = request.getParameter("mustClick");
        String wasteToken = request.getParameter("wasteToken");
        String pic = request.getParameter("pic");
        String clickTokenStr = request.getParameter("clickToken");
        int must_click = 0;
        AdvAdvert advAdvert = new AdvAdvert();
        advAdvert.setTitle(title);
        advAdvert.setAdd_time(new Date());
        advAdvert.setContent(content);
        if (mustClick!=null &&mustClick!=""){
            must_click = Integer.parseInt(mustClick);
        }
        advAdvert.setMust_click(must_click);
        advAdvert.setUrl(url);
        double waste_token = 0;
        if (wasteToken!=null &&wasteToken!=""){
            waste_token = Double.parseDouble(wasteToken);
        }
        double clickToken = 0;
        if (clickTokenStr!=null&&clickTokenStr!=""){
            clickToken = Double.parseDouble(clickTokenStr);
        }
        advAdvert.setWaste_token(waste_token);
        advAdvert.setPic(pic);
        advAdvert.setCount_click(0);
        advAdvert.setClickToken(clickToken);
        boolean boo = advertismentService.insert(advAdvert);
        if (boo == true){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "添加广告成功!");
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "添加广告失败!");
        }
        return jsonObject;
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
            System.out.println(file2.length());
            url= OSSUploadFile.uploadFile(file2);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (url!=""||url!=null){
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
    public JSONObject clickAdvert(HttpServletRequest request){
        String idStr = request.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        int id = 0;
        if (idStr!=null && idStr!=""){
            id = Integer.parseInt(idStr);
        }
        // TODO: 2018/10/7 通过redis获取  redis 就等同于session.  尤其早期的项目多服务必须用redis
        int userId = 1;
        AdvAdvert advAdvert = advertismentService.selectOne(id);
        if (advAdvert.getStatus()!=1){
            jsonObject.put("code", -3);
            jsonObject.put("msg", "图片状态不对!");
            return jsonObject;
        }
        int status = advertismentService.clickAdv(advAdvert);
        if (status == -1){
            jsonObject.put("code", -2);
            jsonObject.put("msg", "已经超出点击范围!");
            return jsonObject;
        }

        if (status== 1){
            boolean boo = bussinessService.changeToken(advAdvert.getClickToken(),advAdvert.getBusinessId(),userId);
            if (boo == false){
                jsonObject.put("code", -1);
                jsonObject.put("msg","点击失败!");
                return jsonObject;
            }
            jsonObject.put("code", 1);
            jsonObject.put("msg", "点击成功!");
            jsonObject.put("url", advAdvert.getUrl());
        }

        return jsonObject;

    }

}
