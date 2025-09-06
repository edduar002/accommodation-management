package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<User> create(User user);

    ResponseEntity<User> register(User user);

    ResponseEntity<User> login(String email, String password);

    ResponseEntity<User> edit(int idUser);

    ResponseEntity<User> changePassword(int idUser);
}
