package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public ResponseEntity<UserEntity> create(UserEntity user) {
        return null;
    }

    @Override
    public ResponseEntity<UserEntity> register(UserEntity user) {
        return null;
    }

    @Override
    public ResponseEntity<UserEntity> login(String email, String password) {
        return null;
    }

    @Override
    public ResponseEntity<UserEntity> edit(int idUser) {
        return null;
    }

    @Override
    public ResponseEntity<UserEntity> changePassword(int idUser) {
        return null;
    }
}
