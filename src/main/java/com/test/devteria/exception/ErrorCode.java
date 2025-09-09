package com.test.devteria.exception;

public enum ErrorCode {
    USER_NOT_FOUND(404, "User Not Found"),
    USER_NOT_EXIST(404, "User Not Exist"),
    USER_ALREADY_EXIST(409, "User Already Exist"),
    USER_EMAIL_ALREADY_EXIST(409, "User Email Already Exist"),
    USER_PASSWORD_ALREADY_EXIST(409, "User Password Already Exist"),
    USERNAME_INVALID(409, "Username is Invalid"),
    PASSWORD_INVALID(409, "Password is Invalid"),
    INVALID(409, "Invalid Enum"),

    UNCAUGHT_EXCEPTION(500, "Uncaught Exception"),
    ;

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
