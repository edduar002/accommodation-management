package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.respositories.LegalDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegalDocumentServiceImpl implements LegalDocumentService{

    @Autowired
    private LegalDocumentRepository repository;

}
