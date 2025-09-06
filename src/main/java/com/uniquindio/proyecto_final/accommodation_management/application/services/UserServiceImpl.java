package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.User;
import com.uniquindio.proyecto_final.accommodation_management.persistence.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public ResponseEntity<User> create(User user) {
        return null;
    }

    @Override
    public ResponseEntity<User> register(User user) {
        return null;
    }

    @Override
    public ResponseEntity<User> login(String email, String password) {
        return null;
    }

    @Override
    public ResponseEntity<User> edit(int idUser) {
        return null;
    }

    @Override
    public ResponseEntity<User> changePassword(int idUser) {
        return null;
    }
}
