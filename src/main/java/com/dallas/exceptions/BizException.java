package com.dallas.exceptions;

import org.springframework.http.HttpStatus;

public class BizException extends RuntimeException{

    public BizException(String message) {
        super(message);
    }
}
