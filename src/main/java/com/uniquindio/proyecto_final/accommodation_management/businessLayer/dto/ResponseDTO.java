package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    // Identificador único de la respuesta
    private int id;

    // Contenido de la respuesta escrita por el anfitrión
    private String content;

    // Identificador del anfitrión que realizó la respuesta
    private int hostsId;

    // Identificador del comentario al cual se responde
    private int commentsId;

    // Fecha en la que se realizó la respuesta
    private LocalDateTime date;

    // Fecha en la que se creó el registro en la base de datos
    private LocalDateTime createdAt;

    // Fecha de la última actualización del registro
    private LocalDateTime updatedAt;

    // Nombre del anfitrión (para mostrar sin necesidad de otra consulta)
    private String hostName;

}