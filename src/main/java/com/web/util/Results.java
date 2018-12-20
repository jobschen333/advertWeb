package com.web.util;


import com.web.bean.BO.ResultBO;

/**
 * 封装我们的ResultBO
 * @author chenxy
 */
public class Results {

    public static <T> ResultBO result(T data, Integer code, String msg) {
        return new ResultBO().data(data).code(code).message(msg);
    }

    public static <T> ResultBO success(T data) {
        return result(data, 1, null);
    }

    public static <T> ResultBO success(String msg){
        return result(null, 1, msg);
    }

    public static <T> ResultBO success(T data, String msg){
        return result(data, 1, msg);
    }

    public static <T> ResultBO success(int code,T data, String msg){
        return result(data, code, msg);
    }

    public static <T> ResultBO fail(Integer code, String msg) {
        return result(null, code, msg);
    }

    public static <T> ResultBO fail(String error) {
        return fail(2, error);
    }
}
