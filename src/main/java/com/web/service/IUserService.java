package com.web.service;

import com.web.bean.DO.AdvUser;
import com.web.bean.VO.UserVO;

/**
 * 服务层接口
 * @author LinkinStar
 */
public interface IUserService {


    /**
     * 获得userInfo
     * @param id
     * @return
     */
    UserVO getUser(int id);

    /**
     * 通过用户名和密码查找
     * @param userAccount
     * @param password
     * @return
     */
    AdvUser selectByUserAccountAndPassword(String userAccount, String password);

    /**
     * 注册
     * @param advUser
     * @return
     */
    boolean register(AdvUser advUser);
}
