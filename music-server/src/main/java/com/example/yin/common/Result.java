package com.example.yin.common;

import lombok.Data;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/4 19:04
 **/
@Data
public class Result {

    private int code;

    private String message;

    private String type;

    private Boolean success;

    private Object data;

    public static Result success(String message) {
        Result r = new Result();
        r.setCode(200);
        r.setMessage(message);
        r.setSuccess(true);
        r.setType("success");
        r.setData(null);
        return r;
    }

    public static Result success(String message, Object data) {
        Result r = success(message);
        r.setData(data);
        return r;
    }

    public static Result warning(String message) {
        Result r = error(message);
        r.setType("warning");
        return r;
    }

    public static Result error(String message) {
        Result r = success(message);
        r.setSuccess(false);
        r.setType("error");
        return r;
    }

    public static Result fatal(String message) {
        Result r = error(message);
        r.setCode(500);
        return r;
    }
}
