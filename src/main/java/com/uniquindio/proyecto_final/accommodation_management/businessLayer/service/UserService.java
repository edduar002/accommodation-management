package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ChangePasswordDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface UserService {

    UserDTO save(UserDTO user);

    UserDTO login(LoginDTO login);

    Optional<UserDTO> edit(int idAccommodation, UserDTO user);

    Optional<UserDTO> changePassword(int id, ChangePasswordDTO user);

    Optional<UserDTO> recoveryPassword(int id, String newPassword);

}
