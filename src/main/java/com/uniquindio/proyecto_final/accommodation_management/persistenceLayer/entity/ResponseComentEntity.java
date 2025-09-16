package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="responses")
public class ResponseComentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private int hostsId;
    private int commentsId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ResponseComentEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHostsId() {
        return hostsId;
    }

    public void setHostsId(int hostsId) {
        this.hostsId = hostsId;
    }

    public int getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(int commentsId) {
        this.commentsId = commentsId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
