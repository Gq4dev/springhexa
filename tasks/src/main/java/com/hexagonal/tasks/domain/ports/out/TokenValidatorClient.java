package com.hexagonal.tasks.domain.ports.out;

public interface TokenValidatorClient {
    boolean isValid(String token);
}
