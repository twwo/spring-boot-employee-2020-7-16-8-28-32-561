package com.thoughtworks.springbootemployee.config;

import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NotSuchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String NOT_SUCH_DATA = "not such data";

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotSuchDataException.class)
    String handleNotSuchDataException() {
        return NOT_SUCH_DATA;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalOperationException.class)
    String handleIllegalOperationException() {
        return "illegal operation";
    }
}
