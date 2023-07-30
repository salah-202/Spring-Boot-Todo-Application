package com.example.springboot.tut.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorDetails {
    private String message;
    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ErrorDetails(){
        this.timestamp = new Date();
    }

    public ErrorDetails(String message,String url){
        this();
        this.message = message;
        this.url = url;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
