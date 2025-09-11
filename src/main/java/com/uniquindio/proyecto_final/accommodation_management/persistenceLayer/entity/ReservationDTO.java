package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="reservations")
public class ReservationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer numberGuests;
    private String state;
    private boolean confirmed;
    private Integer accommodationsId;
    private Integer hostsId;
    private Integer usersId;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public ReservationDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getNumberGuests() {
        return numberGuests;
    }

    public void setNumberGuests(Integer numberGuests) {
        this.numberGuests = numberGuests;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getAccommodationsId() {
        return accommodationsId;
    }

    public void setAccommodationsId(Integer accommodationsId) {
        this.accommodationsId = accommodationsId;
    }

    public Integer getHostsId() {
        return hostsId;
    }

    public void setHostsId(Integer hostsId) {
        this.hostsId = hostsId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
