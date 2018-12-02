package com.web.dao;

import com.web.bean.DO.AdvUser;
import com.web.bean.VO.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 数据访问层
 * @author LinkinStar
 */
@Repository
public interface UserDao {

    /**
     * 获得userInfo
     * @param id
     * @return
     */
    UserVO getUser(int id);

    /**
     * 通过用户名密码查找
     * @param username
     * @param password
     * @return
     */
    AdvUser selectByUserAccountAndPassword(@Param("username") String username, @Param("password") String password);
}
