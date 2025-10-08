package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ChangePasswordDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.UserService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.UserDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.UserMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO dao;

    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public UserDTO save(UserDTO dto) {
        return dao.save(dto);
    }

    @Override
    public UserDTO login(LoginDTO login) {
        return dao.login(login);
    }

    @Transactional
    @Override
    public Optional<UserDTO> edit(int id, UserDTO user) {
        Optional<UserDTO> userDb = dao.findById(id);
        if(userDb.isPresent()){
            UserDTO userNew = userDb.orElseThrow();
            userNew.setName(user.getName());
            return Optional.of(dao.save(userNew));
        }
        return userDb;
    }

    @Transactional
    @Override
    public Optional<UserDTO> changePassword(int id, ChangePasswordDTO user) {
        Optional<UserDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            UserDTO userNew = userDb.get();
            if (userNew.getPassword().equals(user.getOldPassword())) {
                userNew.setPassword(user.getNewPassword());
                dao.save(userNew);
                return Optional.of(userNew);
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<UserDTO> recoveryPassword(int id, String newPassword) {
        Optional<UserDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            UserDTO userNew = userDb.get();
                userNew.setPassword(newPassword);
                dao.save(userNew);
                return Optional.of(userNew);
        }
        return Optional.empty();
    }

}
