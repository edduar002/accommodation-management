package com.uniquindio.proyecto_final.accommodation_management.persistence.respositories;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
