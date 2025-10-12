package com.uniquindio.proyecto_final.accommodation_management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SmokeTest {

    @Autowired Environment env;

    @Test
    void contextLoads_withTestProfileAndH2() {
        // Perfil activo = "test" (lo pusimos desde Gradle)
        assertThat(env.getActiveProfiles()).contains("test");
        // La URL de la datasource debe apuntar a H2 en memoria
        assertThat(env.getProperty("spring.datasource.url"))
                .isNotNull()
                .contains("jdbc:h2:mem:");
    }
}
