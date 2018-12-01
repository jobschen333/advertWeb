package com.web.exception;


import com.web.bean.BO.ResultBO;
import com.web.util.LogUtil;
import com.web.util.Results;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: ebusincess
 * @description: 全局异常处理
 * @author: chenxy
 * @create: 2018-11-16 16:56
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultBO handleException(Exception ex) {
        LogUtil.printErrorLog(ex);
        return Results.fail(-1,ex.getMessage());
    }

    /**
     * 处理所有的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBO handleAllException(Exception ex) {
        LogUtil.printErrorLog(ex);
        return Results.fail(-1,"系统异常");
    }



}
