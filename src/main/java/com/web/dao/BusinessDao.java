package com.web.dao;

import com.web.bean.DO.AdvBusiness;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * 商家dao
 * @author chxy
 */
@Repository
public interface BusinessDao {
    /**
     * 减少
     * @param businessId
     * @param clickToken
     * @return
     */
    boolean decrease(@Param("businessId") int businessId, @Param("clickToken") BigDecimal clickToken) ;

    /**
     * 通过userId查找AdvBusiness
     * @param userId
     * @return
     */
    AdvBusiness selectByUserId(int userId);
}
