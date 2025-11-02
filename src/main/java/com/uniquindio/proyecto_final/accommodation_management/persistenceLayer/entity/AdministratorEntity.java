package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa un administrador del sistema.
 *
 * <p>Contiene información personal del administrador, credenciales y referencia a su rol
 * en el sistema. También mantiene auditoría de creación y actualización.</p>
 */
@Entity
@Table(name="administrators")
public class AdministratorEntity {

    /** Identificador único del administrador */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del administrador */
    private String name;

    /** Apellido del administrador */
    private String surname;

    /** Correo electrónico del administrador */
    private String email;

    /** Contraseña del administrador (almacenada de forma segura en hash) */
    private String password;

    /** ID del rol asociado al administrador */
    @Column(name = "roles_id")
    private Integer rolesId;

    /** Fecha y hora de creación del registro */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /** Fecha y hora de la última actualización del registro */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /** Rol asociado al administrador */
    @ManyToOne
    @JoinColumn(name = "roles_id", insertable = false, updatable = false)
    private RoleEntity role;

    /** Constructor vacío requerido por JPA */
    public AdministratorEntity() {}

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Integer getRolesId() { return rolesId; }
    public void setRolesId(Integer rolesId) { this.rolesId = rolesId; }

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