package com.uniquindio.proyecto_final.accommodation_management.application.controllers;

import com.uniquindio.proyecto_final.accommodation_management.application.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

}
