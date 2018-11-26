package com.web.bean.DO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 后台账号表
 * @author chxy
 */
@Data
@NoArgsConstructor
public class AdvBackAccount {

    /**主键id*/
    private int id;

    /** 用户账号*/
    private String userAccount;

    /** 用户密码*/
    private String password;

    /** 添加时间*/
    private Timestamp addTime;

    /** 昵称*/
    private String nikeName;

}
