package com.demo.taskproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class UserException extends Exception {

    public UserException(String message) {
        super(message);
    }
}
