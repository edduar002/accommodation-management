package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ChangePasswordDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;

import java.util.Optional;

public interface AdministratorService {

    AdministratorDTO save(AdministratorDTO administrator);

    AdministratorDTO login(LoginDTO login);

    Optional<AdministratorDTO> changePassword(int id, ChangePasswordDTO user);

}
