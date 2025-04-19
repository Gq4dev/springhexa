package com.hexagonal.tasks.infrastructure.controllers;


import com.hexagonal.tasks.domain.models.Token;
import com.hexagonal.tasks.domain.ports.in.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequest request) {
        Token token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(token);
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public LoginRequest(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

