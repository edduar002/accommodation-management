package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ChangePasswordDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface HostService {

    HostDTO save(HostDTO host);

    Optional<HostDTO> edit(int idHost, HostDTO host);

    Optional<HostDTO> changePassword(int id, ChangePasswordDTO user);

    HostDTO login(LoginDTO login);

    Optional<HostDTO> recoveryPassword(int id, String newPassword);

    List<HostDTO> hostsList();
}
