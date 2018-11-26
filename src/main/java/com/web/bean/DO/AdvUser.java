package com.web.bean.DO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 *
 * 用户表
 * @author chxy
 */
@Data
@NoArgsConstructor
public class AdvUser {

    /**主键id*/
    private int id;

    /**账号名*/
    private String username;

    /**密码名称*/
    private String password;

    /**
     * 性别 1 男  2女
     */
    private int sex;

    /**
     * 电话号码
     */
    private String phone;

    /**邮箱*/
    private String email;

    /**假删除标志 */
    private String sts;

    /** 标签*/
    private String tag;

    /** 添加时间*/
    private Timestamp addTime;

    /** 保留字段*/
    private String account;

    /**真实姓名*/
    private String realName;

}
