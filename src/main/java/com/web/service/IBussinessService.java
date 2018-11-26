package com.web.service;

/**
 *
 * @author chxy
 */
public interface IBussinessService {
    /**
     * 交易token
     * @param id
     * @param businessId
     * @param userId
     * @return
     */
    boolean changeToken(double id, int businessId, int userId);
}
