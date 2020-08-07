package com.auth.majkl.springsecurityjwt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MZAuthException extends RuntimeException{
    public MZAuthException(String message){
        super(message);
    }
}
