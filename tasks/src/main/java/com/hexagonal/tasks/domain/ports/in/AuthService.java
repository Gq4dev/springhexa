package com.hexagonal.tasks.domain.ports.in;

import com.hexagonal.tasks.domain.models.Token;

public interface AuthService {
    Token login(String username, String password);
}
