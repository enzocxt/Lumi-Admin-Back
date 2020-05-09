package com.tao.lumiadmin.result;

public class ResultFactory {

    public static Result buildSuccessResult(Object data) {
        return buildResult(200, "成功", data);
    }

    public static Result buildFailResult(Object data) {
        return buildResult(400, "成功", data);
    }

    public static Result buildResult(int code, String message, Object data) {
        return new Result(code, message, data);
    }
    
}