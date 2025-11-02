package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa una ciudad dentro de un departamento.
 *
 * <p>Contiene información básica de la ciudad, su estado activo,
 * referencia al departamento al que pertenece y auditoría de creación y actualización.</p>
 */
@Entity
@Table(name="cities")
public class CityEntity {

    /** Identificador único de la ciudad */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre de la ciudad */
    private String name;

    /** Indica si la ciudad está activa en el sistema */
    private boolean active;

    /** ID del departamento al que pertenece la ciudad */
    @Column(name = "departments_id")
    private Integer departmentsId;

    /** Fecha y hora de creación del registro */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Fecha y hora de la última actualización del registro */
    @Column(name = "updated_at", nullable = false, updatable = true)
    private LocalDateTime updatedAt;

    /** Departamento asociado a la ciudad */
    @ManyToOne
    @JoinColumn(name = "departments_id", insertable = false, updatable = false)
    private DepartmentEntity department;

    /** Constructor vacío requerido por JPA */
    public CityEntity() {}

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Integer getDepartmentsId() { return departmentsId; }
    public void setDepartmentsId(Integer departmentsId) { this.departmentsId = departmentsId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public DepartmentEntity getDepartment() { return department; }
    public void setDepartment(DepartmentEntity department) { this.department = department; }

    // --- Callbacks de JPA ---
    /**
     * Se ejecuta automáticamente antes de insertar un registro en la base de datos.
     * Establece createdAt y updatedAt con la fecha y hora actuales.
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