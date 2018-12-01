package com.web.config;


import com.web.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * spring容器初始化 Servlet的本质是一个Java对象，这个对象拥有一系列的方法来处理HTTP请求。
 * servlet -->  servlet构造函数 --> postConstruct --> Init --> Service --> destory -->PreDestory --> 服务卸载servlet
 * @author chenxy
 */
@Configuration
public class ApplicationContextInit {
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        Constants.APPLICATION_CONTEXT = applicationContext;
    }
}
