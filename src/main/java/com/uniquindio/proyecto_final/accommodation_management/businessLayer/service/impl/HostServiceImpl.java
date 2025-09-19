package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.HostService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    @Autowired
    private HostRepository repository;

    @Override
    @Transactional
    public HostEntity save(HostEntity dto) {
        return repository.save(dto);
    }

    @Transactional
    @Override
    public Optional<HostEntity> edit(int id, HostEntity host) {
        Optional<HostEntity> hostDb = repository.findById(id);
        if(hostDb.isPresent()){
            HostEntity hostNew = hostDb.orElseThrow();
            hostNew.setName(host.getName());
            return Optional.of(repository.save(hostNew));
        }
        return hostDb;
    }

    @Override
    public ResponseEntity<HostEntity> changePassword(int idHost) {
        return null;
    }
}
