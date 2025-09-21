package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.AccommodationMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AccommodationDAO {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper accommodationMapper;

    public AccommodationDTO save(AccommodationDTO dto) {
        AccommodationEntity entity = accommodationMapper.toEntity(dto);
        AccommodationEntity savedEntity = accommodationRepository.save(entity);
        return accommodationMapper.toDTO(savedEntity);
    }

    public List<AccommodationDTO> searchAvailableAccommodations() {
        List<AccommodationEntity> entities = accommodationRepository.searchAvailableAccommodations();
        return entities.stream()
                .map(accommodationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AccommodationDTO> ownAccommodationList(int idHost) {
        List<AccommodationEntity> entities = accommodationRepository.ownAccommodationList(idHost);
        return entities.stream()
                .map(accommodationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AccommodationDTO> findById(int id) {
        return accommodationRepository.findById(id)
                .map(accommodationMapper::toDTO);
    }

    public Double averageGrades(int idAccommodation) {
        return accommodationRepository.averageGrades(idAccommodation);
    }
}