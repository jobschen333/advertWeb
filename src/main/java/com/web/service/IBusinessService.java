package com.web.service;

import com.web.bean.DO.AdvBusiness;

import java.math.BigDecimal;

/**
 * 商家服务
 * @author chxy
 */
public interface IBusinessService {
    /**
     * 交易token
     * @param advertId 广告id
     * @param clickToken 单次点击token
     * @param businessId
     * @param userId
     * @return
     */
    boolean changeToken(int advertId,BigDecimal clickToken, int businessId, int userId);

    /**
     * 通过userId查找商家账号
     * @param userId
     * @return
     */
    AdvBusiness selectByUserId(int userId);
}
