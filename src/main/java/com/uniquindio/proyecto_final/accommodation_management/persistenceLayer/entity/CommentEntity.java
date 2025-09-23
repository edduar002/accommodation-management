package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @Column(name = "accommodations_id")
    private int accommodationsId;

    @Column(name = "users_id")
    private int usersId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "accommodations_id", insertable = false, updatable = false)
    private AccommodationEntity accommodation;

    @ManyToOne
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "comment")
    private List<ResponseCommentEntity> responses;

    public CommentEntity() {
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

    public int getAccommodationsId() {
        return accommodationsId;
    }

    public void setAccommodationsId(int accommodationsId) {
        this.accommodationsId = accommodationsId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
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
