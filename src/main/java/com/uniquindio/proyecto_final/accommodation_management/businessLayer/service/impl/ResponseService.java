package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ResponseService {

    ResponseEntity<ResponseDTO> save(ResponseDTO response);
}
