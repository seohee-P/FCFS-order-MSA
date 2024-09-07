package com.seohee.fcfsordermsa.global.exception;

import lombok.Getter;

@Getter
public class MethodArgumentNotValidException extends RuntimeException {

    private final int code;
    private final String message;

    public MethodArgumentNotValidException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
