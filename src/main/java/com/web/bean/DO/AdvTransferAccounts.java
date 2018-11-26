package com.web.bean.DO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 转账记录
 * @author chxy
 */
@Data
@NoArgsConstructor
public class AdvTransferAccounts {

    /**主键id*/
    private int id;

    /** 用户id*/
    private int userId;

    /** 转出地址*/
    private String sendAddress;

    /** 用户地址*/
    private String localAddress;
}
