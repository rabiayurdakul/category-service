package com.eleiatech.categoryservice.exception.handler;

import com.eleiatech.categoryservice.exception.exceptions.DataAlreadyDeletedException;
import com.eleiatech.categoryservice.exception.exceptions.DataNotFoundException;
import com.eleiatech.categoryservice.exception.exceptions.TypeCannotDeletedException;
import com.eleiatech.categoryservice.response.InternalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public InternalApiResponse<String> handleDataNotFoundException(DataNotFoundException dataNotFoundException){
        return InternalApiResponse.<String>builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .hasError(true)
                .errorMessages(Collections.singletonList(dataNotFoundException.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAlreadyDeletedException.class)
    public InternalApiResponse<String> handleDataAlreadyDeletedException(DataAlreadyDeletedException dataAlreadyDeletedException){
        return InternalApiResponse.<String>builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages((Collections.singletonList(dataAlreadyDeletedException.getMessage())))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeCannotDeletedException.class)
    public InternalApiResponse<String> handleTypeCannotDeletedException(TypeCannotDeletedException typeCannotDeletedException){
        return InternalApiResponse.<String>builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(typeCannotDeletedException.getMessage()))
                .build();
    }
}
