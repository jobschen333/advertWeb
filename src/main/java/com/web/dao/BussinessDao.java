package com.web.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author chxy
 */
public interface BussinessDao {
    /**
     * 减少
     * @param businessId
     * @param clickToken
     * @return
     */
    boolean decrease(@Param("businessId") int businessId, @Param("clickToken") double clickToken) ;
}
