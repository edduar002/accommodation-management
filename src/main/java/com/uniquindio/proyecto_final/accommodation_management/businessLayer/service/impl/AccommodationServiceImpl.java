package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.AccommodationService;
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

    @Autowired
    private AccommodationRepository repository;

    @Override
    @Transactional
    public AccommodationEntity save(AccommodationEntity dto) {
        return repository.save(dto);
    }

    @Override
    public List<AccommodationEntity> searchAvailableAccommodations() {
        return repository.searchAvailableAccommodations();
    }

    @Override
    public List<AccommodationEntity> ownAccommodationList(int idHost) {
        return repository.ownAccommodationList(idHost);
    }

    @Transactional
    @Override
    public Optional<AccommodationEntity> edit(int id, AccommodationEntity accommodation) {
        Optional<AccommodationEntity> accommodationDb = repository.findById(id);
        if(accommodationDb.isPresent()){
            AccommodationEntity accommodationNew = accommodationDb.orElseThrow();
            accommodationNew.setPrice(accommodation.getPrice());
            return Optional.of(repository.save(accommodationNew));
        }
        return accommodationDb;
    }

    @Transactional
    @Override
    public Optional<AccommodationEntity> delete(int id) {
        Optional<AccommodationEntity> accommodationDb = repository.findById(id);
        if(accommodationDb.isPresent()){
            AccommodationEntity accommodationNew = accommodationDb.orElseThrow();
            accommodationNew.setActive(false);
            return Optional.of(repository.save(accommodationNew));
        }
        return accommodationDb;
    }

    @Override
    public AccommodationEntity detail(int accommodationId) {
        return repository.findById(accommodationId).orElse(null);
    }

    @Override
    public ResponseEntity<AccommodationEntity> viewMetrics(int accommodation) {
        return null;
    }

    @Override
    public Double averageGrades(int idAccommodation) {
        return repository.averageGrades(idAccommodation);
    }

}
