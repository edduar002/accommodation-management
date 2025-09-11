package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ReservationRepository;
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
    public ResponseEntity<ReservationDTO> save(ReservationDTO reservation) {
        return null;
    }

    @Override
    public ResponseEntity<ReservationDTO> makeReservations(LocalDate checkIn, LocalDate checkOut) {
        return null;
    }

    @Override
    public ResponseEntity<ReservationDTO> cancelReservations(int idReservation) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccommodationDTO>> viewReservationHistory(int idUser) {
        return null;
    }

    @Override
    public ResponseEntity<AccommodationDTO> viewAccommodationDetails(int idAccommodation) {
        return null;
    }
}
