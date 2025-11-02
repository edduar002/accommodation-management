package com.uniquindio.proyecto_final.accommodation_management.presentation.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Controlador REST para la gestión de imágenes.
 * Permite subir archivos a Cloudinary y obtener la URL de subida.
 */
@RestController
@RequestMapping("/api/images")
public class ImageController {

    // Inyección del cliente Cloudinary para manejo de imágenes
    @Autowired
    private Cloudinary cloudinary;

    /**
     * Sube un archivo a Cloudinary.
     * @param file archivo enviado en la petición multipart.
     * @return ResponseEntity con el mapa de resultados de la subida (incluyendo URL).
     * @throws IOException en caso de fallo al leer el archivo.
     */
    // Endpoint POST para subir imágenes
    @PostMapping("/upload")
    public ResponseEntity<Map> upload(@RequestParam("file") MultipartFile file) throws IOException {
        // Se sube el archivo a Cloudinary y se obtiene el resultado como Map
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return ResponseEntity.ok(uploadResult);
    }
}