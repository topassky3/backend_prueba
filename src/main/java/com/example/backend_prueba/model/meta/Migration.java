package com.example.backend_prueba.model.meta;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "migrations", schema = "meta")
public class Migration {

    @Id
    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "name")
    private String name;

    @Column(name = "applied_at", nullable = false, updatable = false)
    private ZonedDateTime appliedAt;

    // Constructors
    public Migration() {
        this.appliedAt = ZonedDateTime.now();
    }

    public Migration(String version, String name) {
        this.version = version;
        this.name = name;
        this.appliedAt = ZonedDateTime.now();
    }

    // Getters y Setters

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ZonedDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(ZonedDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
