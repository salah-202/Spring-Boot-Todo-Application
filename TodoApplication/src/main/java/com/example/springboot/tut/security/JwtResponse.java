package com.example.springboot.tut.security;

public class JwtResponse {
    private String token;

    public JwtResponse(String token){
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
