package com.seohee.fcfsordermsa.global.exception;

import lombok.Getter;

@Getter
public class IllegalArgumentException extends RuntimeException {

    private final int code;
    private final String message;

    public IllegalArgumentException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}