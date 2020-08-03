package com.thoughtworks.springbootemployee.exception;

public class GlobalException {
    private final String message;

    public GlobalException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
