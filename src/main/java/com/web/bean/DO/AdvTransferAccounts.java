package com.web.bean.DO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 转账记录
 * @author chxy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvTransferAccounts {

    /**主键id*/
    private int id;

    /** 用户id*/
    private int userId;

    /** 转出地址*/
    private String sendAddress;

    /** 用户地址*/
    private String localAddress;

    /** 添加时间*/
    private Timestamp addTime;

    /** 转账金额*/
    private BigDecimal sendToken;

    /** 备注 */
    private String remark;

    /** 转账状态  0 未到账 1 到账成功 2 异常*/
    private int status;
}
