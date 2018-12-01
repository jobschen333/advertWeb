package com.web.util;

import org.springframework.context.ApplicationContext;

/**
 * 系统常量定义
 * @author wjc
 */
public class Constants {
    /**
     * spring容器
     */
    public static ApplicationContext APPLICATION_CONTEXT;

    /**
     * 请求接口返回码(成功)
     */
    public static final Integer SUCCESS = 1;

    /**
     * 请求失败返回编码
     */
    public static final Integer ERROR_CODE = 3;
}
