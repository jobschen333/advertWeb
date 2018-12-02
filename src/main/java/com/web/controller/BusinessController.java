package com.web.controller;

import com.web.bean.BO.ResultBO;
import com.web.bean.BO.UserSessionBO;
import com.web.bean.DO.AdvBusiness;
import com.web.service.IBusinessService;
import com.web.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 商家控制器
 * @author chxy
 */
@Controller
@RequestMapping("business")
public class BusinessController {

    @Autowired
    private IBusinessService bussinessService;

    /**
     * 检查是否是商家账号
     * @param request
     * @return
     */
    @GetMapping("checkUpBusiness")
    public ResultBO checkUpBussiness(HttpServletRequest request, UserSessionBO userSessionBO){
        AdvBusiness advBusiness = bussinessService.selectByUserId(userSessionBO.getUserId());
        if (advBusiness == null) {
            return Results.fail("不是商家账号!");
        }
        return Results.success("是商家账号");
    }



}
