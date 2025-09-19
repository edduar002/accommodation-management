package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.FavoriteDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.HostService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.FavoriteDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.HostDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Transactional
    @Override
    public Optional<HostEntity> edit(int id, HostEntity host) {
        Optional<HostEntity> hostDb = dao.findById(id);
        if(hostDb.isPresent()){
            HostDTO hostNew = hostDb.orElseThrow();
            hostNew.setName(host.getName());
            return Optional.of(dao.save(hostNew));
        }
        return hostDb;
    }

    @Override
    public ResponseEntity<HostDTO> changePassword(int idHost) {
        return null;
    }
}
