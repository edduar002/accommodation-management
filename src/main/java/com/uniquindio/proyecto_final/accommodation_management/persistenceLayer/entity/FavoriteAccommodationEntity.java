package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="favorite_accommodation")
public class FavoriteAccommodationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean active;

    @Column(name = "favorites_id")
    private Integer favoritesId;

    @Column(name = "accommodations_id")
    private Integer accommodationsId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "favorites_id", insertable = false, updatable = false)
    private FavoriteEntity favorite;

    @ManyToOne
    @JoinColumn(name = "accommodations_id", insertable = false, updatable = false)
    private AccommodationEntity accommodation;

    public FavoriteAccommodationEntity(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(Integer favoritesId) {
        this.favoritesId = favoritesId;
    }

    public Integer getAccommodationsId() {
        return accommodationsId;
    }

    public void setAccommodationsId(Integer accommodationsId) {
        this.accommodationsId = accommodationsId;
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
