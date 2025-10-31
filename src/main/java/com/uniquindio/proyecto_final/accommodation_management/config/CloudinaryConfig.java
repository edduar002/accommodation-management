package com.uniquindio.proyecto_final.accommodation_management.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "daeixho4x", // ✅ solo el nombre de la nube
                "api_key", "356184896876497",
                "api_secret", "C4HKwYZF4WgBuAAmWH6mulrl68I"
        ));
    }
}