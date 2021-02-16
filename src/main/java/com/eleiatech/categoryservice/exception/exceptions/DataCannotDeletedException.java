package com.eleiatech.categoryservice.exception.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataCannotDeletedException extends RuntimeException{

    public DataCannotDeletedException(String dataType, String message){
        super(message);
        log.error(dataType + " Cannot Deleted Exception Message: {}" + message);
    }

}
