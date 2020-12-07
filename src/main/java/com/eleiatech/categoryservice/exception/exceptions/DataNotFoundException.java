package com.eleiatech.categoryservice.exception.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String dataType, String message){
        super(message);
        log.error(dataType + " Not Found Exception Message: {}",message);
    }
}
