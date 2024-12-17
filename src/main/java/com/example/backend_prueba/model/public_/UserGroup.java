package com.example.backend_prueba.model.public_;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_groups", schema = "public")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String name;

    @ElementCollection
    @CollectionTable(name = "user_group_privileges", joinColumns = @JoinColumn(name = "user_group_id"))
    @Column(name = "privilege")
    private List<String> privileges;

    // Constructors
    public UserGroup() {}

    public UserGroup(String name, List<String> privileges) {
        this.name = name;
        this.privileges = privileges;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }
}
