package com.uniquindio.proyecto_final.accommodation_management.business.respositories;

import com.uniquindio.proyecto_final.accommodation_management.persistence.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
