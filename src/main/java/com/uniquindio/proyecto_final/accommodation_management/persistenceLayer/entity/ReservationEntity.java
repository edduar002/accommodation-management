package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad que representa una reserva realizada por un usuario en un alojamiento.
 *
 * <p>Contiene información sobre fechas de ingreso y salida, cantidad de huéspedes, estado,
 * calificación, y relaciones con alojamiento, host y usuario.</p>
 */
@Entity
@Table(name="reservations")
public class ReservationEntity {

    /** Identificador único de la reserva */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Fecha de check-in de la reserva */
    @Column(name = "check_in")
    private LocalDate checkIn;

    /** Fecha de check-out de la reserva */
    @Column(name = "check_out")
    private LocalDate checkOut;

    /** Número de huéspedes para esta reserva */
    @Column(name = "number_guests")
    private Integer numberGuests;

    /** Estado de la reserva (PENDIENTE, ACEPTADA, RECHAZADA, CANCELADA, etc.) */
    private String state;

    /** Calificación de la reserva por parte del usuario */
    private int calification;

    /** Id del alojamiento reservado */
    @Column(name = "accommodations_id")
    private Integer accommodationsId;

    /** Id del host del alojamiento */
    @Column(name = "hosts_id")
    private Integer hostsId;

    /** Id del usuario que realiza la reserva */
    @Column(name = "users_id")
    private Integer usersId;

    /** Fecha de creación de la reserva */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /** Fecha de última actualización de la reserva */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // --- Relaciones ---

    /** Alojamiento asociado a la reserva */
    @ManyToOne
    @JoinColumn(name = "accommodations_id", insertable = false, updatable = false)
    private AccommodationEntity accommodation;

    /** Host asociado a la reserva */
    @ManyToOne
    @JoinColumn(name = "hosts_id", insertable = false, updatable = false)
    private HostEntity host;

    /** Usuario que realizó la reserva */
    @ManyToOne
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private UserEntity user;

    /** Constructor vacío requerido por JPA */
    public ReservationEntity() {}

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }

    public LocalDate getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }

    public Integer getNumberGuests() { return numberGuests; }
    public void setNumberGuests(Integer numberGuests) { this.numberGuests = numberGuests; }

    public int getCalification() { return calification; }
    public void setCalification(int calification) { this.calification = calification; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public Integer getAccommodationsId() { return accommodationsId; }
    public void setAccommodationsId(Integer accommodationsId) { this.accommodationsId = accommodationsId; }

    public Integer getHostsId() { return hostsId; }
    public void setHostsId(Integer hostsId) { this.hostsId = hostsId; }

    public Integer getUsersId() { return usersId; }
    public void setUsersId(Integer usersId) { this.usersId = usersId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public AccommodationEntity getAccommodation() { return accommodation; }
    public void setAccommodation(AccommodationEntity accommodation) { this.accommodation = accommodation; }

    public HostEntity getHost() { return host; }
    public void setHost(HostEntity host) { this.host = host; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

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