package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ReservationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.ReservationMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ReservationDTO> viewAccommodationReservations(int idAccommodation) {
        List<ReservationEntity> entities = reservationRepository.viewAccommodationReservations(idAccommodation);
        return entities.stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ReservationDTO> viewReservationHistory(int idUser) {
        List<ReservationEntity> entities = reservationRepository.viewReservationHistory(idUser);
        return entities.stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReservationDTO> findById(int id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toDTO);
    }
}