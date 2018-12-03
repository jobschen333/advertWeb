package com.web.bean.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户session表
 * @author chxy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionBO implements Serializable {

    /**sessionId*/
    private String sessionId;

    /** 用户id */
    private int userId;

    /** 商家ids*/
    private int businessId;

    /** 用户账号*/
    private String username;

    /** 用户账号*/
    private String account;

    /** 过期时间*/
    private long outTime;
}
