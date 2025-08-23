package com.ryo.medicineservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "UNCATEGORIZED_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "USER_EXISTED", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002, "USER_NOT_FOUND", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1003, "USERNAME_INVALID", HttpStatus.BAD_REQUEST),
    LASTNAME_INVALID(1004, "LASTNAME_INVALID", HttpStatus.BAD_REQUEST),
    FIRSTNAME_INVALID(1005, "FIRSTNAME_INVALID", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1006, "PASSWORD_INVALID", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007,"UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    INVALID_KEY(1008,"INVALID_KEY", HttpStatus.BAD_REQUEST)
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
