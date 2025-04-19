package com.hexagonal.tasks.infrastructure.controllers;


import com.hexagonal.tasks.infrastructure.adapters.ProtectedApiClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/protected")
public class ProtectedController {

    private final ProtectedApiClient protectedApiClient;

    public ProtectedController(ProtectedApiClient protectedApiClient) {
        this.protectedApiClient = protectedApiClient;
    }

    @GetMapping("/data")
    public ResponseEntity<Map> getProtectedData() {
        Map<String, Object> result = protectedApiClient.getProtectedInfo();
        return ResponseEntity.ok(result);
    }

}
