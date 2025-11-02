package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa un departamento dentro de la aplicación.
 *
 * <p>Un departamento puede contener múltiples usuarios, anfitriones, alojamientos y ciudades.</p>
 */
@Entity
@Table(name="departments")
public class DepartmentEntity {

    /** Identificador único del departamento */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del departamento */
    private String name;

    /** Estado del departamento (activo/inactivo) */
    private boolean active;

    /** Fecha y hora de creación del registro */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Fecha y hora de la última actualización del registro */
    @Column(name = "updated_at", nullable = false, updatable = true)
    private LocalDateTime updatedAt;

    /** Lista de anfitriones asociados a este departamento */
    @OneToMany(mappedBy = "department")
    private List<HostEntity> hosts;

    /** Lista de usuarios asociados a este departamento */
    @OneToMany(mappedBy = "department")
    private List<UserEntity> users;

    /** Lista de alojamientos asociados a este departamento */
    @OneToMany(mappedBy = "department")
    private List<AccommodationEntity> accommodations;

    /** Lista de ciudades asociadas a este departamento */
    @OneToMany(mappedBy = "department")
    private List<CityEntity> cities;

    /** Constructor vacío requerido por JPA */
    public DepartmentEntity() {}

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

    public List<HostEntity> getHosts() { return hosts; }
    public void setHosts(List<HostEntity> hosts) { this.hosts = hosts; }

    public List<UserEntity> getUsers() { return users; }
    public void setUsers(List<UserEntity> users) { this.users = users; }

    public List<AccommodationEntity> getAccommodations() { return accommodations; }
    public void setAccommodations(List<AccommodationEntity> accommodations) { this.accommodations = accommodations; }

    public List<CityEntity> getCities() { return cities; }
    public void setCities(List<CityEntity> cities) { this.cities = cities; }

    // --- Callbacks de JPA ---
    /**
     * Se ejecuta automáticamente antes de insertar un registro en la base de datos.
     * Inicializa createdAt y updatedAt con la fecha y hora actuales.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * Se ejecuta automáticamente antes de actualizar un registro en la base de datos.
     * Actualiza updatedAt con la fecha y hora actuales.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}