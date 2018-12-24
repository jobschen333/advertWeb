package com.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.web.bean.BO.UserSessionBO;
import com.web.bean.VO.UserVO;
import com.web.service.IUserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户
 * @author chxy
 */
@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("userInfo")
    @ResponseBody
    public JSONObject userInfo(HttpServletRequest request, UserSessionBO userSessionBO){
        JSONObject jsonObject = new JSONObject();
        UserVO userVO = userService.getUser(userSessionBO.getUserId());
        if (userVO!=null){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "查找数据成功!");
            jsonObject.put("data",userVO);
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "查找数据失败!");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }


}
