package com.ai.util.exception;

/**
 * Created by eason on 2017/2/16.
 */
public class ResourceNotExistException extends RuntimeException {
    private  String id;
    private String message=" resource not exists";

    public ResourceNotExistException(String id,String type) {
        this.id = id;
        this.message=" "+type+this.message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
