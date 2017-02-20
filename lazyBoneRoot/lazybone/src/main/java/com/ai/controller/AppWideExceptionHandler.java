package com.ai.controller;

/**
 * Created by eason on 2017/2/16.
 */

import com.ai.util.consts.CommonConst;
import com.ai.util.exception.Error;
import com.ai.util.exception.ResourceExistException;
import com.ai.util.exception.ResourceNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by eason on 2017/2/16.
 * 全局捕获异常
 */
@ControllerAdvice
public  class AppWideExceptionHandler {
    public static final Logger log= LoggerFactory.getLogger(AppWideExceptionHandler.class);
    @ExceptionHandler(ResourceExistException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity resourceAlreadyExist(ResourceExistException resourceExistEx){
        log.error("==================ResourceExistException====================");
        String id=resourceExistEx.getId();
        return new ResponseEntity(new Error(CommonConst.ErrorCode.existsCode,"id:"+id+resourceExistEx.getMessage()),HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ResourceNotExistException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity resourceNotExist(ResourceNotExistException resourceNotExistEx){
        log.error("==================ResourceNotExistException====================");
        String id=resourceNotExistEx.getId();
        return new ResponseEntity(new Error(CommonConst.ErrorCode.notExistsCode,"id:"+id+resourceNotExistEx.getMessage()),HttpStatus.CONFLICT);
    }

}
