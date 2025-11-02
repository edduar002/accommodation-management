package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa los servicios disponibles para los alojamientos.
 *
 * <p>Contiene el nombre del servicio, estado de actividad, fechas de creación y actualización,
 * y la relación con los alojamientos que lo utilizan.</p>
 */
@Entity
@Table(name="services")
public class ServiceEntity {

    /** Identificador único del servicio */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del servicio */
    private String name;

    /** Estado de actividad del servicio */
    private boolean active;

    /** Fecha de creación del registro */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Fecha de última actualización del registro */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /** Lista de asociaciones de este servicio con alojamientos */
    @OneToMany(mappedBy = "service")
    private List<AccommodationServiceEntity> accommodationServices;

    /** Constructor vacío requerido por JPA */
    public ServiceEntity() {}

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // --- Callbacks de JPA ---
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