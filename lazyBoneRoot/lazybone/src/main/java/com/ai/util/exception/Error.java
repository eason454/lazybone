package com.ai.util.exception;

/**
 * Created by eason on 2017/2/16.
 */
public class Error {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Error(int code, String message) {

        this.code = code;
        this.message = message;
    }
}
