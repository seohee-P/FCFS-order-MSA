package com.seohee.fcfsordermsa.global.response;

import com.seohee.fcfsordermsa.global.exception.ExceptionResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {


    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data) {

        return new ApiResponse<>(200, null, data);
    }

    public static ApiResponse<?> ok() {

        return new ApiResponse<>(200, null, null);
    }

    public static <T> ApiResponse<T> ok(String message, T data) {

        return new ApiResponse<>(200, message, data);
    }

    public static ApiResponse<ExceptionResponse> error(int errorCode, String message) {

        return new ApiResponse<>(errorCode, message, null);
    }


    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
