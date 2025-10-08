package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ChangePasswordDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.HostService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.HostDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostDAO dao;

    public HostServiceImpl(HostDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public HostDTO save(HostDTO dto) {
        return dao.save(dto);
    }

    @Override
    @Transactional
    public Optional<HostDTO> edit(int idHost, HostDTO host) {
        Optional<HostDTO> hostDb = dao.findById(idHost);
        if (hostDb.isPresent()) {
            HostDTO hostNew = hostDb.orElseThrow();
            hostNew.setName(host.getName());
            return Optional.of(dao.save(hostNew));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<HostDTO> changePassword(int id, ChangePasswordDTO user) {
        Optional<HostDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            HostDTO userNew = userDb.get();
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

    @Override
    public HostDTO login(LoginDTO login) {
        return dao.login(login);
    }

    @Transactional
    @Override
    public Optional<HostDTO> recoveryPassword(int id, String newPassword) {
        Optional<HostDTO> userDb = dao.findById(id);
        if (userDb.isPresent()) {
            HostDTO userNew = userDb.get();
            userNew.setPassword(newPassword);
            dao.save(userNew);
            return Optional.of(userNew);
        }
        return Optional.empty();
    }
}