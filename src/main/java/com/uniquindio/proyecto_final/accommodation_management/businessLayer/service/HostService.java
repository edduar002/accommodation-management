package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface HostService {

    HostDTO save(HostDTO host);

    Optional<HostDTO> edit(int idHost, HostDTO host);

    ResponseEntity<HostDTO> changePassword(int idHost);

    HostDTO login(LoginDTO login);
}
