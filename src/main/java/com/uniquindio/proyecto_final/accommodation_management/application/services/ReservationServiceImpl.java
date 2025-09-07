package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Accommodation;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Reservation;
import com.uniquindio.proyecto_final.accommodation_management.business.respositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository repository;

    @Override
    public ResponseEntity<Reservation> save(Reservation reservation) {
        return null;
    }

    @Override
    public ResponseEntity<Reservation> makeReservations(LocalDate checkIn, LocalDate checkOut) {
        return null;
    }

    @Override
    public ResponseEntity<Reservation> cancelReservations(int idReservation) {
        return null;
    }

    @Override
    public ResponseEntity<List<Accommodation>> viewReservationHistory(int idUser) {
        return null;
    }

    @Override
    public ResponseEntity<Accommodation> viewAccommodationDetails(int idAccommodation) {
        return null;
    }
}
