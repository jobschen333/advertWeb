package com.web.interceptor;

import com.web.bean.BO.UserSessionBO;
import com.web.config.ProjectConfig;
import com.web.service.IUserSessionService;
import com.web.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * web端访问拦截器
 * @author chxy
 */
public class UserWebInterceptor implements HandlerInterceptor {


    /** 用户登录记录 */
    @Autowired
    private static IUserSessionService userSessionService;

    /** 用户登录记录 */
    private static IUserSessionService getSessionService(HttpServletRequest request) {
        Object o = Constants.APPLICATION_CONTEXT;
        if (userSessionService == null) {
            userSessionService = (IUserSessionService) Constants.APPLICATION_CONTEXT.getBean("userSessionService");
        }
        return userSessionService;
    }

    /**
     * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*String sessionId = (String) request.getSession().getAttribute("sessionId");
        if (!StringUtil.isNotNull(sessionId)) {
            request.setAttribute("code", -1);
            request.setAttribute("message", "未登录，请登录");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }*/
        return false;
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


        System.out.println("postHandle");
    }


    /**
     * 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

    /**
     * 记录登录成功
     * @param request 当前用户请求
     * @param userSession 商家管理员信息
     * @return 操作成功：返回sessionId，操作失败：返回null
     */
    public static String loginSuccess(HttpServletRequest request, UserSessionBO userSession) {
        Timestamp curTime = DateUtil.getCurrentTime();
        String sessionId = ProjectConfig.LOGIN_USER + DateUtil.longToTimeStr(curTime.getTime(), DateUtil.dateFormat10) +
                NumberUtil.createNumberStr(12);
        String ipAddress = IpAddressUtil.getIpAddress(request);
        //
        boolean insertResult = getSessionService(request).insertUserSession(sessionId, userSession.getUserId(),
                1, ipAddress, curTime);
        if (!insertResult) {
            return null;
        }
            userSession.setSessionId(sessionId);
        userSession.setOutTime(curTime.getTime());
        request.getSession().setAttribute("sessionId",sessionId);
        request.getSession().setAttribute(sessionId,userSession);
        request.getSession().setAttribute("userAccount",userSession.getUsername());
        return sessionId;
    }

    /**
     * 获取用户信息
     * @param request 当前用户请求
     * @return 查询成功：返回数据，未查询到数据或查询失败：返回NULL
     */
    public static UserSessionBO getUserSessionBO(HttpServletRequest request) {
        String sessionId = (String) request.getSession().getAttribute("sessionId");
        if (!StringUtil.isNotNull(sessionId)) {
            return null;
        }
        return (UserSessionBO)request.getSession().getAttribute(sessionId);
    }



}
