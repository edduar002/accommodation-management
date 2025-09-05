package com.uniquindio.proyecto_final.accommodation_management.application.services;

import com.uniquindio.proyecto_final.accommodation_management.persistence.respositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository repository;

}
