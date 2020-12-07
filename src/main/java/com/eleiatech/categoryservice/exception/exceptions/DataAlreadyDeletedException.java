package com.eleiatech.categoryservice.exception.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataAlreadyDeletedException extends RuntimeException{

    public DataAlreadyDeletedException(String dataType, String message){
        super(message);
        log.error(dataType + " Already Deleted Exception Message: {}",message);
    }

}
