package com.web.service;

import java.sql.Timestamp;

/**
 * 用户登录记录
 * @author chxy
 */
public interface IUserSessionService {
    /**
     * 新增用户登录记录
     * @param sessionId sessionId，业务类型（2）+日期（6）+随机位（12）
     * @param userId 用户Id
     * @param loginForm 登录来源，1：web端，2：wap端，3：APP端
     * @param ipAddress 操作时的ip地址
     * @param loginTime 登录时间
     * @return 操作成功：返回true，操作失败：返回false
     */
    boolean insertUserSession(String sessionId, int userId, int loginForm, String ipAddress, Timestamp loginTime);
}
