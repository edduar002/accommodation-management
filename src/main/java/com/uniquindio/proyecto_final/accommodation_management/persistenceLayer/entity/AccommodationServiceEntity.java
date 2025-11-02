package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa la relación entre un alojamiento y los servicios que ofrece.
 *
 * <p>Esta entidad funciona como tabla intermedia entre {@link AccommodationEntity} y {@link ServiceEntity},
 * permitiendo asignar múltiples servicios a un alojamiento y almacenar información de auditoría
 * como fecha de creación y actualización.</p>
 */
@Entity
@Table(name="accommodation_service")
public class AccommodationServiceEntity {

    /** Identificador único de la relación alojamiento-servicio */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** ID del alojamiento asociado */
    @Column(name = "accommodations_id")
    private int accommodationsId;

    /** ID del servicio asociado */
    @Column(name = "services_id")
    private int servicesId;

    /** Fecha y hora de creación del registro */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /** Fecha y hora de la última actualización del registro */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Relaciones con otras entidades

    /** Alojamiento asociado a este servicio */
    @ManyToOne
    @JoinColumn(name = "accommodations_id", insertable = false, updatable = false)
    private AccommodationEntity accommodation;

    /** Servicio asociado al alojamiento */
    @ManyToOne
    @JoinColumn(name = "services_id", insertable = false, updatable = false)
    private ServiceEntity service;

    /** Constructor vacío requerido por JPA */
    public AccommodationServiceEntity() {}

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAccommodationsId() { return accommodationsId; }
    public void setAccommodationsId(int accommodationsId) { this.accommodationsId = accommodationsId; }
    public int getServicesId() { return servicesId; }
    public void setServicesId(int servicesId) { this.servicesId = servicesId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

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