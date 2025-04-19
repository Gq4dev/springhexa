package com.hexagonal.tasks.application.usecases;


import com.hexagonal.tasks.domain.models.Token;
import com.hexagonal.tasks.domain.ports.in.AuthService;
import com.hexagonal.tasks.domain.ports.out.AuthClient;
import com.hexagonal.tasks.infrastructure.cache.TokenStore;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthClient authClient;
    private final TokenStore tokenStore;

    public AuthServiceImpl(AuthClient authClient, TokenStore tokenStore) {
        this.authClient = authClient;
        this.tokenStore = tokenStore;
    }

    @Override
    public Token login(String username, String password) {
        Token token = authClient.authenticate(username, password);
        tokenStore.setToken(token.getToken());
        return token;
    }
}

