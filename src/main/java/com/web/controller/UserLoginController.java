package com.web.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.web.bean.BO.ResultBO;
import com.web.bean.DO.AdvUser;
import com.web.config.ReturnCodeConfig;
import com.web.service.IUserService;
import com.web.util.MD5Util;
import com.web.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * 用户登录
 * @author chxy
 */
@Controller
@RequestMapping("index")
public class UserLoginController {

    @Autowired
    private IUserService userService;

    /**
     * 验证码标识
     */
    private static final String VERIFY_KEY = "verifyCode";

    /**
     * 验证码工具
     */
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
     * 登录
     * @param request
     * @param userAccount
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public ResultBO login(HttpServletRequest request, @RequestParam("userAccount") @NotNull(message = "用户名不能为空") String userAccount,
                          @RequestParam("password") @NotNull(message = "密码不能为空") String password,
                          @RequestParam("validateCode") @NotNull(message = "验证码不能为空") String validateCode) {

        String sessionCode = (String) request.getSession().getAttribute(VERIFY_KEY);

        validateCode = validateCode.toLowerCase();
        sessionCode = sessionCode.toLowerCase();
        if (!validateCode.equals(sessionCode)) {
            Results.fail(ReturnCodeConfig.ParamError, "验证码错误");
        }
        password = MD5Util.toMd5(password);
        AdvUser advUser = userService.selectByUserAccountAndPassword(userAccount, password);
        if (advUser == null ) {
            Results.fail(ReturnCodeConfig.ParamError, "账号或者密码错误");
        }
        return Results.success("登录成功！");
    }

    @RequestMapping("defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute(VERIFY_KEY, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();

    }

}
