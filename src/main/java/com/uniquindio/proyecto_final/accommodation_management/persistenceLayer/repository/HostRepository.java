package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<HostEntity, Integer> {

    HostEntity findByEmail(String email);

}
