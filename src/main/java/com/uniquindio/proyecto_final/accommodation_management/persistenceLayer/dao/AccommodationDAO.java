package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.AccommodationMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

    public AccommodationDTO searchAvailableAccommodations(AccommodationDTO dto) {
        AccommodationEntity entity = accommodationMapper.toEntity(dto);
        AccommodationEntity savedEntity = accommodationRepository.save(entity);
        return accommodationMapper.toDTO(savedEntity);
    }

    public AccommodationDTO ownAccommodationList(AccommodationDTO dto) {
        AccommodationEntity entity = accommodationMapper.toEntity(dto);
        AccommodationEntity savedEntity = accommodationRepository.save(entity);
        return accommodationMapper.toDTO(savedEntity);
    }

    public AccommodationDTO findById(AccommodationDTO dto) {
        AccommodationEntity entity = accommodationMapper.toEntity(dto);
        AccommodationEntity savedEntity = accommodationRepository.save(entity);
        return accommodationMapper.toDTO(savedEntity);
    }

    public AccommodationDTO averageGrades(AccommodationDTO dto) {
        AccommodationEntity entity = accommodationMapper.toEntity(dto);
        AccommodationEntity savedEntity = accommodationRepository.save(entity);
        return accommodationMapper.toDTO(savedEntity);
    }

}