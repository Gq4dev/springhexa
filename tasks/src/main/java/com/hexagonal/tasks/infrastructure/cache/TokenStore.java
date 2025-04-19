package com.hexagonal.tasks.infrastructure.cache;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenStore {

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public Optional<String> getToken() {
        return Optional.ofNullable(token);
    }

    public void clear() {
        this.token = null;
    }
}