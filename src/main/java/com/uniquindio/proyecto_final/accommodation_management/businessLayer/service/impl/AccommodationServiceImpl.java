package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationService;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.UserDAO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.AccommodationEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationDAO dao;

    public AccommodationServiceImpl(AccommodationDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public AccommodationDTO save(AccommodationDTO dto) {
        return dao.save(dto);
    }

    @Override
    public List<AccommodationDTO> searchAvailableAccommodations() {
        return dao.searchAvailableAccommodations();
    }

    @Override
    public List<AccommodationDTO> ownAccommodationList(int idHost) {
        return dao.ownAccommodationList(idHost);
    }

    @Transactional
    @Override
    public Optional<AccommodationDTO> edit(int id, AccommodationDTO accommodation) {
        Optional<AccommodationDTO> accommodationDb = dao.findById(id);
        if(accommodationDb.isPresent()){
            AccommodationDTO accommodationNew = accommodationDb.orElseThrow();
            accommodationNew.setPrice(accommodation.getPrice());
            return Optional.of(dao.save(accommodationNew));
        }
        return accommodationDb;
    }

    @Transactional
    @Override
    public Optional<AccommodationDTO> delete(int id) {
        Optional<AccommodationDTO> accommodationDb = dao.findById(id);
        if(accommodationDb.isPresent()){
            AccommodationDTO accommodationNew = accommodationDb.orElseThrow();
            accommodationNew.setActive(false);
            return Optional.of(dao.save(accommodationNew));
        }
        return accommodationDb;
    }

    @Override
    public AccommodationDTO detail(int accommodationId) {
        return dao.findById(accommodationId).orElse(null);
    }

    @Override
    public ResponseEntity<AccommodationDTO> viewMetrics(int accommodation) {
        return null;
    }

    @Override
    public Double averageGrades(int idAccommodation) {
        return dao.averageGrades(idAccommodation);
    }

}
