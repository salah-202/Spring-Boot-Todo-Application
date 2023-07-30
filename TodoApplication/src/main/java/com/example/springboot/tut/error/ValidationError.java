package com.example.springboot.tut.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationError {

    private List<String> errors;
    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ValidationError(){
        this.errors = new ArrayList<>();
        this.timestamp = new Date();
    }

    public void addError(String error){
        this.errors.add(error);
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getUrl() {
        return url;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
