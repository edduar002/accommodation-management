package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa los roles de los usuarios y hosts en el sistema.
 *
 * <p>Contiene el nombre del rol, estado de actividad, y las relaciones con los usuarios y hosts asociados.</p>
 */
@Entity
@Table(name="roles")
public class RoleEntity {

    /** Identificador único del rol */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del rol */
    private String name;

    /** Estado de actividad del rol */
    private boolean active;

    /** Fecha de creación del registro */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Fecha de última actualización del registro */
    @Column(name = "updated_at", nullable = false, updatable = true)
    private LocalDateTime updatedAt;

    // --- Relaciones ---
    /** Lista de usuarios asociados a este rol */
    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;

    /** Lista de hosts asociados a este rol */
    @OneToMany(mappedBy = "role")
    private List<HostEntity> hosts;

    /** Constructor vacío requerido por JPA */
    public RoleEntity() {}

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