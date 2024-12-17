package com.example.backend_prueba.model.meta;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "embeddings", schema = "meta")
public class Embedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "embedding", nullable = false, columnDefinition = "public.vector(384)")
    private String embedding; // Considera usar un tipo adecuado para 'vector'

    // Constructors
    public Embedding() {
        this.createdAt = ZonedDateTime.now();
    }

    public Embedding(String content, String embedding) {
        this.content = content;
        this.embedding = embedding;
        this.createdAt = ZonedDateTime.now();
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmbedding() {
        return embedding;
    }

    public void setEmbedding(String embedding) {
        this.embedding = embedding;
    }
}
