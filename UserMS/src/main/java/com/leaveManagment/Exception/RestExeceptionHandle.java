package com.leaveManagment.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExeceptionHandle {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String,String> illegalArgumentExceptionHandler(IllegalArgumentException illegalArgumentException){
        Map<String,String> map = new HashMap<>();
        map.put("error",illegalArgumentException.getMessage());
      return map;
    }
}
