package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.LegalDocumentService;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Image;
import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.LegalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/legaldocuments")
public class LegalDocumentController {

    @Autowired
    private LegalDocumentService service;

    @PostMapping
    public ResponseEntity<LegalDocument> create(@RequestBody LegalDocument legalDocument){
        return service.save(legalDocument);
    }

}
