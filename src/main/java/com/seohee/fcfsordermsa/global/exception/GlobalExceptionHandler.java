package com.seohee.fcfsordermsa.global.exception;

import com.seohee.fcfsordermsa.global.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ApiResponse<ExceptionResponse> handleBadRequestException(final BadRequestException e) {

        log.warn(e.getMessage(), e);

        return ApiResponse.error(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateException.class)
    public ApiResponse<ExceptionResponse> handleDuplicateException(final DuplicateException e) {

        log.warn(e.getMessage(), e);

        return ApiResponse.error(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<ExceptionResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {

        log.warn(e.getMessage(), e);

        return ApiResponse.error(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<ExceptionResponse> handleIllegalArgumentException(final IllegalArgumentException e) {

        log.warn(e.getMessage(), e);

        return ApiResponse.error(e.getCode(), e.getMessage());
    }

}