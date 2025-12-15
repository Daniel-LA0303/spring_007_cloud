package com.kafka.provider.exmple.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.kafka.provider.exmple.model.response.ErrorResponse;

import static com.kafka.provider.exmple.model.response.enums.ErrorType.FUNCTIONAL;
import static com.kafka.provider.exmple.model.response.enums.ErrorType.SYSTEM;
import static com.kafka.provider.exmple.utils.ErrorCatalog.INTERNAL_SERVER_ERROR;
import static com.kafka.provider.exmple.utils.ErrorCatalog.INVALID_PARAMETERS;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;;


@RestControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ErrorResponse handleHandlerMethodValidationException(
        HandlerMethodValidationException e) {

        ErrorResponse response = new ErrorResponse();
        response.setCode(INVALID_PARAMETERS.getCode());
        response.setType(FUNCTIONAL);
        response.setMessage(INVALID_PARAMETERS.getMessage());
        response.setDetails(Collections.singletonList(e.getMessage()));
        response.setTimestamp(LocalDate.now().toString());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        List<String> details = bindingResult.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ErrorResponse response = new ErrorResponse();
        response.setCode(INVALID_PARAMETERS.getCode());
        response.setType(FUNCTIONAL);
        response.setMessage(INVALID_PARAMETERS.getMessage());
        response.setDetails(details);
        response.setTimestamp(LocalDate.now().toString());
        return response;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        log.error("Unhandled exception: ", e);

        ErrorResponse response = new ErrorResponse();
        response.setCode(INTERNAL_SERVER_ERROR.getCode());
        response.setType(SYSTEM);
        response.setMessage(INTERNAL_SERVER_ERROR.getMessage());
        response.setDetails(Collections.singletonList(e.getMessage()));
        response.setTimestamp(LocalDate.now().toString());
        return response;
    }
}