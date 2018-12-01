package com.web.dao;


import com.web.bean.DO.UserSessionDO;
import org.springframework.stereotype.Repository;

/**
 * 用户登录记录
 * @author whx
 *
 */
@Repository
public interface IUserSessionDao {

	/**
	 * 新增用户登录记录
	 * @param userSessionDO 用户登录记录
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	boolean insertUserSession(UserSessionDO userSessionDO);
	

}
