package com.test.devteria.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(404, "User Not Found", HttpStatus.NOT_FOUND),
    USER_NOT_EXIST(404, "User Not Exist", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXIST(409, "User Already Exist", HttpStatus.BAD_REQUEST),
    USER_EMAIL_ALREADY_EXIST(409, "User Email Already Exist", HttpStatus.BAD_REQUEST),
    USER_PASSWORD_ALREADY_EXIST(409, "User Password Already Exist", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(409, "Username is Invalid", HttpStatus.NOT_FOUND),
    PASSWORD_INVALID(409, "Password is Invalid", HttpStatus.NOT_FOUND),
    INVALID(409, "Invalid Enum", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401, "You do not have permission", HttpStatus.UNAUTHORIZED),

    UNCAUGHT_EXCEPTION(500, "Uncaught Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
