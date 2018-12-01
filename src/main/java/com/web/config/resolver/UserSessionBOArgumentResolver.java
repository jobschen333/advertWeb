package com.web.config.resolver;


import com.web.bean.BO.UserSessionBO;
import com.web.interceptor.UserWebInterceptor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * UserSessionBO解析器
 * @author chxy
 */
public class UserSessionBOArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 支持参数
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz= parameter.getParameterType();
        //class在方法区
        return clazz== UserSessionBO.class;

    }

    /**
     * 处理参数
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        /**如果为抛出异常*/
        UserSessionBO userSessionBO = UserWebInterceptor.getUserSessionBO((HttpServletRequest) webRequest.getNativeRequest());
        if (userSessionBO == null) {
            throw new RuntimeException("请重新登录!");
        }
        return  userSessionBO;
    }

}
