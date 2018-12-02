package com.web.service;

import com.web.bean.DO.AdvBusiness;

/**
 * 商家服务
 * @author chxy
 */
public interface IBusinessService {
    /**
     * 交易token
     * @param id
     * @param businessId
     * @param userId
     * @return
     */
    boolean changeToken(double id, int businessId, int userId);

    /**
     * 通过userId查找商家账号
     * @param userId
     * @return
     */
    AdvBusiness selectByUserId(int userId);
}
