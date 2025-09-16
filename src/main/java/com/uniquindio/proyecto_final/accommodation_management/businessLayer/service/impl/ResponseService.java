package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.ResponseComentEntity;
import org.springframework.http.ResponseEntity;

public interface ResponseService {

    ResponseComentEntity save(ResponseComentEntity response);
}
