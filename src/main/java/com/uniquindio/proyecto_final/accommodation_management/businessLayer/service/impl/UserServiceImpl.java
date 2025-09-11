package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public ResponseEntity<UserDTO> create(UserDTO user) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> register(UserDTO user) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> login(String email, String password) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> edit(int idUser) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> changePassword(int idUser) {
        return null;
    }
}
