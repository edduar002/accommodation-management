package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.CityEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseRepository extends JpaRepository<ResponseCommentEntity, Integer> {

    @Query("SELECT r FROM ResponseCommentEntity r WHERE r.commentsId = :idComment")
    List<ResponseCommentEntity> findAllResponses(@Param("idComment") int idComment);

}
