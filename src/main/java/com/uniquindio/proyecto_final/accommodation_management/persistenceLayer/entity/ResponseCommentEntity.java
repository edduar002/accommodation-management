package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa la respuesta de un host a un comentario realizado sobre un alojamiento.
 *
 * <p>Contiene el contenido de la respuesta, la fecha, y las relaciones con el host y el comentario asociado.</p>
 */
@Entity
@Table(name="responses")
public class ResponseCommentEntity {

    /** Identificador único de la respuesta */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Contenido de la respuesta */
    private String content;

    /** Fecha de creación de la respuesta */
    private LocalDateTime date;

    /** Id del host que realiza la respuesta */
    @Column(name = "hosts_id")
    private int hostsId;

    /** Id del comentario al que se responde */
    @Column(name = "comments_id")
    private int commentsId;

    /** Fecha de creación en base de datos */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /** Fecha de última actualización en base de datos */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // --- Relaciones ---
    /** Host que realiza la respuesta */
    @ManyToOne
    @JoinColumn(name = "hosts_id", insertable = false, updatable = false)
    private HostEntity host;

    /** Comentario al que se responde */
    @ManyToOne
    @JoinColumn(name = "comments_id", insertable = false, updatable = false)
    private CommentEntity comment;

    /** Constructor vacío requerido por JPA */
    public ResponseCommentEntity() {}

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public int getHostsId() { return hostsId; }
    public void setHostsId(int hostsId) { this.hostsId = hostsId; }

    public HostEntity getHost() { return host; }
    public void setHost(HostEntity host) { this.host = host; }

    public int getCommentsId() { return commentsId; }
    public void setCommentsId(int commentsId) { this.commentsId = commentsId; }

    public CommentEntity getComment() { return comment; }
    public void setComment(CommentEntity comment) { this.comment = comment; }

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