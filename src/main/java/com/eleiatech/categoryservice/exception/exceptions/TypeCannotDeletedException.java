package com.eleiatech.categoryservice.exception.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TypeCannotDeletedException  extends RuntimeException{

    public TypeCannotDeletedException(String dataType, String message){
        super(message);
        log.error(dataType + " Cannot Deleted Exception Message: {}" + message);
    }

}
