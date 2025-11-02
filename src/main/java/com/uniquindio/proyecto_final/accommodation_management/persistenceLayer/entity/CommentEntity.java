package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa un comentario realizado por un usuario sobre un alojamiento.
 *
 * <p>Incluye el contenido del comentario, la fecha de creación, la relación con el usuario que comenta,
 * el alojamiento comentado y las respuestas asociadas a este comentario.</p>
 */
@Entity
@Table(name="comments")
public class CommentEntity {

    /** Identificador único del comentario */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Contenido del comentario */
    private String content;

    /** Fecha y hora en la que se realizó el comentario */
    private LocalDateTime date;

    /** ID del alojamiento al que pertenece el comentario */
    @Column(name = "accommodations_id")
    private int accommodationsId;

    /** ID del usuario que realiza el comentario */
    @Column(name = "users_id")
    private int usersId;

    /** Fecha y hora de creación del registro */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /** Fecha y hora de la última actualización del registro */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /** Usuario que realiza el comentario */
    @ManyToOne
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private UserEntity user;

    /** Alojamiento sobre el que se realiza el comentario */
    @ManyToOne
    @JoinColumn(name = "accommodations_id", insertable = false, updatable = false)
    private AccommodationEntity accommodation;

    /** Respuestas asociadas a este comentario */
    @OneToMany(mappedBy = "comment")
    private List<ResponseCommentEntity> responses;

    /** Constructor vacío requerido por JPA */
    public CommentEntity() {}

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public int getAccommodationsId() { return accommodationsId; }
    public void setAccommodationsId(int accommodationsId) { this.accommodationsId = accommodationsId; }

    public int getUsersId() { return usersId; }
    public void setUsersId(int usersId) { this.usersId = usersId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

    public AccommodationEntity getAccommodation() { return accommodation; }
    public void setAccommodation(AccommodationEntity accommodation) { this.accommodation = accommodation; }

    public List<ResponseCommentEntity> getResponses() { return responses; }
    public void setResponses(List<ResponseCommentEntity> responses) { this.responses = responses; }

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