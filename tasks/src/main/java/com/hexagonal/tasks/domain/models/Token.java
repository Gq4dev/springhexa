package com.hexagonal.tasks.domain.models;

public class Token {
    private final String token;

    public Token(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
