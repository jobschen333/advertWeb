package com.web.bean.ENUM;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 防伪码相关返回错误码+信息
 *
 * @author shenwei
 */
public enum CodeResultEnum implements ResultError {
    /**
     * 操作成功
     */
    SUCCESS(1, "操作成功"),

    /**
     * 操作失败
     */
    FAILED(2, "操作失败"),

    /**
     * 无效参数
     */
    ILLEGAL_PARAMETER(-101, "无效参数");


    private int code;
    private String message;

    CodeResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private static Map<Integer, CodeResultEnum> map = Arrays
            .asList(CodeResultEnum.values())
            .stream()
            .collect(Collectors.toMap(CodeResultEnum::getCode, it -> it));

    public static CodeResultEnum resultCodeOf(int code) {
        return map.get(code);
    }

    public static boolean isResultCode(int value, CodeResultEnum code) {
        return resultCodeOf(value) == code;
    }
}
