package com.ryo.medicineservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "UNCATEGORIZED_ERROR", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1001, "USER EXISTED", HttpStatus.BAD_REQUEST)

    ;
    ErrorCode(int code, String message, HttpStatusCode httpStatusCode)
    {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }


    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
