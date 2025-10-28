package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;

import java.util.Optional;

public interface AdministratorService {

    AdministratorDTO save(AdministratorDTO administrator);

    AdministratorDTO login(LoginDTO login);

    Optional<AdministratorDTO> changePassword(int id, ChangePasswordDTO user);

    AdministratorDTO detail(int accommodation);

    Optional<AdministratorDTO> edit(int idHost, AdministratorDTO host);

}
