package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository;

import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad UserEntity.
 * Proporciona operaciones CRUD básicas mediante JpaRepository
 * y métodos personalizados de búsqueda de usuarios.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Busca un usuario por su correo electrónico.
     * @param email correo electrónico del usuario.
     * @return entidad UserEntity correspondiente o null si no existe.
     */
    // Método derivado de Spring Data para buscar usuario por email
    UserEntity findByEmail(String email);

    /**
     * Obtiene todos los usuarios activos.
     * @return lista de usuarios activos.
     */
    // Consulta que selecciona todos los usuarios donde active = true
    @Query("SELECT a FROM UserEntity a WHERE a.active = true")
    List<UserEntity> allUsers();

}