// src/main/java/com/example/backend_prueba/model/public_/User.java
package com.example.backend_prueba.model.public_;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable=false, columnDefinition="TEXT")
    private String username;

    @Column(name="email", nullable=false, unique=true, columnDefinition="TEXT")
    private String email;

    @Column(name="password", nullable=false, columnDefinition="TEXT")
    private String password;

    @Column(name="created_at", updatable=false)
    private ZonedDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="group_id")
    private UserGroup group;

    public User() {
        this.createdAt = ZonedDateTime.now();
    }

    public User(String username, String email, String password, UserGroup group) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.group = group;
        this.createdAt = ZonedDateTime.now();
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public ZonedDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(ZonedDateTime createdAt) { this.createdAt = createdAt; }
    public UserGroup getGroup() { return group; }
    public void setGroup(UserGroup group) { this.group = group; }
}
