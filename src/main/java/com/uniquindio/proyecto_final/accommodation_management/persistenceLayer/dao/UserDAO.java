package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.UserMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO save(UserDTO dto) {
        UserEntity entity = userMapper.toEntity(dto);
        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toDTO(savedEntity);
    }

    public UserDTO login(LoginDTO login) {
        UserEntity entity = userRepository.findByEmail(login.getEmail());
        if (entity == null) {
            return null;
        }
        if (!entity.getPassword().equals(login.getPassword())) {
            return null;
        }
        return userMapper.toDTO(entity);
    }

    public Optional<UserDTO> findById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

}
