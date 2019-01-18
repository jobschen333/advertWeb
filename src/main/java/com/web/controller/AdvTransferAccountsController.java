package com.web.controller;

import com.web.bean.BO.ResultBO;
import com.web.bean.BO.UserSessionBO;
import com.web.bean.DO.AdvTransferAccounts;
import com.web.service.IAdvTransferAccountsService;
import com.web.util.DateUtil;
import com.web.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/** 用户转账
 * @author chxy
 * @date 2019/1/18 3:39 PM
 */
@Controller
@RequestMapping("transfer")
public class AdvTransferAccountsController {

    @Autowired
    private IAdvTransferAccountsService advTransferAccountsService;

    /**
     * 转账
     * @param request
     * @param advAdress
     * @return
     */
    @RequestMapping(value = "transferAccounts", method = RequestMethod.POST)
    @ResponseBody
    public ResultBO transferAccounts(HttpServletRequest request, @RequestParam("advAddress") String advAdress,
                                     @RequestParam("balance") BigDecimal balance,
                                     @RequestParam(value = "remark", required = false) String remark,
                                     UserSessionBO userSessionBO) {
        AdvTransferAccounts advTransferAccounts = AdvTransferAccounts.builder()
                .addTime(DateUtil.getCurrentTime())
                .userId(userSessionBO.getUserId())
                .sendAddress(advAdress)
                .remark(remark)
                .sendToken(balance)
                .status(0)
                .build();

        boolean boo = advTransferAccountsService.insertTransferAccount(advTransferAccounts);
        if (boo) {
            return Results.success("转账成功!");
        }
        return Results.fail("转账失败!");


    }
}
