package com.web.service.Impl;

import com.web.bean.DO.AdvUser;
import com.web.bean.VO.UserVO;
import com.web.dao.UserDao;
import com.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务层
 * @author LinkinStar
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;


    /**
     * 获得user信息
     * @param id
     * @return
     */
    @Override
    public UserVO getUser(int id) {
        return userDao.getUser(id);
    }

    /**
     * 通过用户名和密码查找
     * @param userAccount
     * @param password
     * @return
     */
    @Override
    public AdvUser selectByUserAccountAndPassword(String userAccount, String password) {
        return userDao.selectByUserAccountAndPassword(userAccount, password);
    }

}
