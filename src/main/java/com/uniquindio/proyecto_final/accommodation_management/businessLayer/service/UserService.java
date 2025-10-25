package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO save(UserDTO user);

    UserDTO login(LoginDTO login);

    Optional<UserDTO> edit(int idAccommodation, UserDTO user);

    Optional<UserDTO> changePassword(int id, ChangePasswordDTO user);

    Optional<UserDTO> recoveryPassword(int id, String newPassword);

    List<UserDTO> usersList();

    Optional<UserDTO> delete(int idAccommodation);

}
