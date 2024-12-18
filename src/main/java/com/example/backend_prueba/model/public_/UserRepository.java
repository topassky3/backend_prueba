// src/main/java/com/example/backend_prueba/model/public_/UserRepository.java
package com.example.backend_prueba.model.public_;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
