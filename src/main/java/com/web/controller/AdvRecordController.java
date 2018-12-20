package com.web.controller;

import com.github.pagehelper.PageInfo;
import com.web.bean.BO.UserSessionBO;
import com.web.service.IAdvRecordService;
import com.web.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 广告点击记录
 * @author chxy
 */
@RestController
@RequestMapping("record")
public class AdvRecordController {

    @Autowired
    private IAdvRecordService advRecordService;

    /**
     * 服务列表
     * @param userSessionBO
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("list")
    public String listRecord(UserSessionBO userSessionBO,
                             @RequestParam(value = "page" , defaultValue = "1") Integer page,
                             @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        PageInfo pageInfo = advRecordService.selectRecordList(userSessionBO.getUserId(), page, limit);
        return JsonUtil.requestListToJson(pageInfo.getList(), (int) pageInfo.getTotal());
    }

}
