package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    // Identificador único del comentario
    private int id;

    // Contenido del comentario realizado por el usuario
    private String content;

    // Identificador del alojamiento al que pertenece el comentario
    private int accommodationsId;

    // Identificador del usuario que realizó el comentario
    private int usersId;

    // Fecha en la que se registró el comentario
    private LocalDateTime date;

    // Fecha en la que el registro fue creado en la base de datos
    private LocalDateTime createdAt;

    // Fecha en la que el registro fue actualizado por última vez
    private LocalDateTime updatedAt;

    // Nombre del usuario que realizó el comentario (útil para visualización)
    private String userName;

}