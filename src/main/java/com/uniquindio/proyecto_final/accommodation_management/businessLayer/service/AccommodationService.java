package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    AccommodationDTO save(AccommodationDTO accommodation);

    List<AccommodationDTO> searchAvailableAccommodations();

    List<AccommodationDTO> ownAccommodationList(int idHost);

    Optional<AccommodationDTO> edit(int idAccommodation, AccommodationDTO accommodation);

    Optional<AccommodationDTO> delete(int idAccommodation);

    AccommodationDTO detail(int accommodation);

}
