package com.example.springboot.tut.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NotFoundException extends ApiBaseException{
    public NotFoundException(String message){
        super(message);
    }
    public HttpStatus getStatusCode(){
        return HttpStatus.NOT_FOUND;
    }
}
