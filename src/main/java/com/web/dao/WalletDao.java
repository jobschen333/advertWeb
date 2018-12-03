package com.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * 钱包dao
 * @author chxy
 */
@Repository
public interface WalletDao {

    /**
     * 新增Token
     * @param userId
     * @param clickToken
     * @return
     */
    boolean addToken(@Param("userId") int userId, @Param("clickToken") BigDecimal clickToken);
}
