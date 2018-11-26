package com.web.bean.DO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 钱包表
 * @author chxy
 */
@NoArgsConstructor
@Data
public class AdvWallet {

    /** 主键id*/
    private int id;

    /** 用户id*/
    private int user_id;

    /** 用户余额*/
    private double balance;

    /** 用户地址*/
    private String address;
}
