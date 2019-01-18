package com.web.bean;

import com.web.bean.DO.AdvAdvert;
import org.springframework.beans.factory.FactoryBean;

/**
 * factoryBean注册组件
 * @author chxy
 * @date 2019/1/9 7:24 PM
 */
public class AdvFactoryBean implements FactoryBean<AdvAdvert> {


    @Override
    public AdvAdvert getObject() throws Exception {
        System.out.println("");
        return new AdvAdvert();
    }

    @Override
    public Class<?> getObjectType() {
        return AdvAdvert.class;
    }

    /**
     * 是否单例
     *
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
