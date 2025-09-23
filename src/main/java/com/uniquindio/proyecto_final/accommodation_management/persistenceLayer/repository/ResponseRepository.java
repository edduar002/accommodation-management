package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<ResponseCommentEntity, Integer> {
}
