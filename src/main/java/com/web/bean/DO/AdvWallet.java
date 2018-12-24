package com.web.bean.DO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 钱包表
 * @author chxy
 */
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class AdvWallet {

    /** 主键id*/
    private int id;

    /** 用户id*/
    private int user_id;

    /** 用户余额*/
    private BigDecimal balance;

    /** 用户地址*/
    private String address;
}
