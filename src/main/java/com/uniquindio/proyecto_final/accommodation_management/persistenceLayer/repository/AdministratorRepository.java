package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AdministratorEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<AdministratorEntity, Integer> {

    AdministratorEntity findByEmail(String email);

}
