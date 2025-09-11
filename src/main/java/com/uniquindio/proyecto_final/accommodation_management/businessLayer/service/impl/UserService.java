package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<UserEntity> create(UserEntity user);

    ResponseEntity<UserEntity> register(UserEntity user);

    ResponseEntity<UserEntity> login(String email, String password);

    ResponseEntity<UserEntity> edit(int idUser);

    ResponseEntity<UserEntity> changePassword(int idUser);
}
