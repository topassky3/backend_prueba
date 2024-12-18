// src/main/java/com/example/backend_prueba/controller/AuthController.java
package com.example.backend_prueba.controller;

import com.example.backend_prueba.dto.RegisterRequest;
import com.example.backend_prueba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // Especificar el origen permitido
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String result = userService.registerUser(request);
        if ("Usuario registrado con éxito.".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // Opcional: Endpoint de login
    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody LoginRequest request) { ... }
}
