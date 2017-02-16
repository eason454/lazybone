package com.ai.controller;

/**
 * Created by eason on 2017/2/16.
 */

import com.ai.util.exception.Error;
import com.ai.util.exception.ResourceExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by eason on 2017/2/16.
 * 全局捕获异常
 */
@ControllerAdvice
public  class AppWideExceptionHandler {
    @ExceptionHandler(ResourceExistException.class)
    @ResponseStatus(HttpStatus.OK)
    public Error resourceAlreadyExist(ResourceExistException resourceExistEx){
        String id=resourceExistEx.getId();
        return new Error(1001,id+resourceExistEx.getMessage());
    }
}
