package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CityDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;

import java.util.List;
import java.util.Optional;

public interface CityService {

    CityDTO save(CityDTO city);

    List<CityDTO> citiesList();

    CityDTO detail(int accommodation);

    Optional<CityDTO> edit(int idAccommodation, CityDTO user);

    Optional<CityDTO> delete(int idAccommodation);
}
