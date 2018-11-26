package com.web.bean.BO;

import java.io.Serializable;

/**
 * 返回信息
 * @author chxy
 */
public class ResultBO<T> implements Serializable {

    /**
     * 业务状态（编码） 1 代表成功 其他代表失败
     */
    private Integer code;

    /**
     * 反馈信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public ResultBO<T> data(T data) {
        this.setData(data);
        return this;
    }

    public ResultBO<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultBO<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }
}
