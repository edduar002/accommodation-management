package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface HostService {

    HostDTO save(HostDTO host);

    Optional<HostDTO> edit(int idHost, HostDTO host);

    Optional<HostDTO> changePassword(int id, ChangePasswordDTO user);

    HostDTO detail(int accommodation);

    HostDTO login(LoginDTO login);

    Optional<HostDTO> recoveryPassword(int id, String newPassword);

    List<HostDTO> hostsList();

    Optional<HostDTO> delete(int idAccommodation);
}
