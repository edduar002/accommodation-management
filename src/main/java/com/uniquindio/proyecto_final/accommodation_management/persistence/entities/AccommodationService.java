package com.uniquindio.proyecto_final.accommodation_management.persistence.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="accommodation_service")
public class AccommodationService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int accommodationsId;
    private int servicesId;

    public AccommodationService() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccommodationsId() {
        return accommodationsId;
    }

    public void setAccommodationsId(int accommodationsId) {
        this.accommodationsId = accommodationsId;
    }

    public int getServicesId() {
        return servicesId;
    }

    public void setServicesId(int servicesId) {
        this.servicesId = servicesId;
    }
}
