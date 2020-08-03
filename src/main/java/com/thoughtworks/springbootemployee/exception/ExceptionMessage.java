package com.thoughtworks.springbootemployee.exception;

public enum ExceptionMessage {
    NOT_SUCH_DATA("not such data"), ILLEGAL_OPERATION("illegal operation");


    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
