// src/main/java/com/example/backend_prueba/service/UserService.java
package com.example.backend_prueba.service;

import com.example.backend_prueba.dto.RegisterRequest;
import com.example.backend_prueba.model.public_.User;
import com.example.backend_prueba.model.public_.UserGroup;
import com.example.backend_prueba.model.public_.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(RegisterRequest request) {
        if(request.getUsername() == null || request.getUsername().isEmpty()) {
            return "El nombre de usuario no puede estar vacío.";
        }
        if(request.getEmail() == null || request.getEmail().isEmpty()) {
            return "El email no puede estar vacío.";
        }
        if(request.getPassword() == null || request.getPassword().isEmpty()) {
            return "La contraseña no puede estar vacía.";
        }

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "El email ya está registrado.";
        }
        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "El nombre de usuario ya está en uso.";
        }

        // Aquí podrías asignar un grupo por defecto, si lo necesitas.
        UserGroup defaultGroup = null;
        User newUser = new User(request.getUsername(), request.getEmail(), request.getPassword(), defaultGroup);
        userRepository.save(newUser);

        return "Usuario registrado con éxito.";
    }
}
