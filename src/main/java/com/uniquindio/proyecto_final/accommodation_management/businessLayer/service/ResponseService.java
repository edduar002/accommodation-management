package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ResponseDTO;

import java.util.List;

public interface ResponseService {

    ResponseDTO save(ResponseDTO response);

    List<ResponseDTO> getByComment(int idHost);
}
