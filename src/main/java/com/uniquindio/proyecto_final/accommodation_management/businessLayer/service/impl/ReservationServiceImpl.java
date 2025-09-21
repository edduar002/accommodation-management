package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.ReservationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ReservationDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationDAO dao;

    public ReservationServiceImpl(ReservationDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public ReservationDTO save(ReservationDTO dto) {
        return dao.save(dto);
    }

    @Override
    public List<ReservationDTO> viewAccommodationReservations(int idAccommodation) {
        return dao.viewAccommodationReservations(idAccommodation);
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
    public List<ReservationDTO> viewReservationHistory(int idUser) {
        return dao.viewReservationHistory(idUser);
    }

    @Override
    public ReservationDTO viewReservationDetails(int idReservation) {
        return dao.findById(idReservation).orElse(null);
    }

    @Override
    public ReservationDTO acceptReservationRequests(int idAccommodation) {
        return null;
    }

    @Override
    public ReservationDTO rejectReservationRequests(int idAccommodation) {
        return null;
    }
}