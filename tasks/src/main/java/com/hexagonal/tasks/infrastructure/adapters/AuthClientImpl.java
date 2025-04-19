package com.hexagonal.tasks.infrastructure.adapters;


import com.hexagonal.tasks.domain.models.Token;
import com.hexagonal.tasks.domain.ports.out.AuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthClientImpl implements AuthClient {

    private final RestTemplate restTemplate;

    private static final String AUTH_URL = "https://api-test-gq.vercel.app/api/auth/login";

    public AuthClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Token authenticate(String username, String password) {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(AUTH_URL, request, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String tokenString = (String) response.getBody().get("token");
            return new Token(tokenString);
        } else {
            throw new RuntimeException("Error al autenticar: " + response.getStatusCode());
        }
    }
}

