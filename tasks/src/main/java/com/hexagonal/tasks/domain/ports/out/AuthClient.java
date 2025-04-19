package com.hexagonal.tasks.domain.ports.out;


import com.hexagonal.tasks.domain.models.Token;

public interface AuthClient {
    Token authenticate(String username, String password);
}
