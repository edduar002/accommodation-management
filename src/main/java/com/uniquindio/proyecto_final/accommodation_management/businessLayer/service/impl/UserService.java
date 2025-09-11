package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<UserDTO> create(UserDTO user);

    ResponseEntity<UserDTO> register(UserDTO user);

    ResponseEntity<UserDTO> login(String email, String password);

    ResponseEntity<UserDTO> edit(int idUser);

    ResponseEntity<UserDTO> changePassword(int idUser);
}
