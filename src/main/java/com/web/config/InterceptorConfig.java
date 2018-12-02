package com.web.config;


import com.web.interceptor.UserWebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置
 * @author chenxy
 * create at 2018-09-11
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Bean
    public UserWebInterceptor userWebInterceptor(){
        return new UserWebInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userWebInterceptor()).addPathPatterns("/advert/clickAdvert");
        super.addInterceptors(registry);
    }
}
