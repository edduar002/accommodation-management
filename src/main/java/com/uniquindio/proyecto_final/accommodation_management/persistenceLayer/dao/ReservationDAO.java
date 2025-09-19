package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.ReservationMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationDAO {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationDTO save(ReservationDTO dto) {
        ReservationEntity entity = reservationMapper.toEntity(dto);
        ReservationEntity savedEntity = reservationRepository.save(entity);
        return reservationMapper.toDTO(savedEntity);
    }

    public ReservationDTO viewAccommodationReservations(ReservationDTO dto) {
        ReservationEntity entity = reservationMapper.toEntity(dto);
        ReservationEntity savedEntity = reservationRepository.save(entity);
        return reservationMapper.toDTO(savedEntity);
    }

    public ReservationDTO viewReservationHistory(ReservationDTO dto) {
        ReservationEntity entity = reservationMapper.toEntity(dto);
        ReservationEntity savedEntity = reservationRepository.save(entity);
        return reservationMapper.toDTO(savedEntity);
    }

    public ReservationDTO findById(ReservationDTO dto) {
        ReservationEntity entity = reservationMapper.toEntity(dto);
        ReservationEntity savedEntity = reservationRepository.save(entity);
        return reservationMapper.toDTO(savedEntity);
    }

}