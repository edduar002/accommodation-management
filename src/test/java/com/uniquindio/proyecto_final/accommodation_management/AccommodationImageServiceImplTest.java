package com.uniquindio.proyecto_final.accommodation_management;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationImageDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AccommodationImageServiceImpl;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationImageDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Tests unitarios para AccommodationImageServiceImpl.
 * En esta clase la lógica es de delegación: service.save(dto) -> dao.save(dto).
 * Verificamos:
 *  1) Que delega correctamente en el DAO (interacción).
 *  2) Que retorna exactamente lo que devuelve el DAO (estado).
 *  3) Que las excepciones del DAO se propagan (errores).
 *
 * Patrón AAA usado en cada test:
 *  - Arrange: preparamos stubs/mocks y datos de entrada.
 *  - Act: ejecutamos el método bajo prueba (SUT).
 *  - Assert: comprobamos resultado y/o interacciones.
 */
@ExtendWith(MockitoExtension.class) // Habilita la extensión de Mockito para JUnit 5
class AccommodationImageServiceImplTest {

    // Dependencia externa del servicio: la "mockeamos" para aislar el test
    @Mock
    AccommodationImageDAO dao;

    // SUT (System Under Test): se inyectan los @Mock automáticamente en el constructor
    @InjectMocks
    AccommodationImageServiceImpl service;

    @Test
    @DisplayName("save delega en el DAO y retorna su resultado (happy path)")
    void save_ok_delegatesToDao_andReturnsResult() {
        // ========= Arrange =========
        // Podemos usar mocks ligeros para el DTO; no nos interesa su comportamiento interno.
        AccommodationImageDTO dtoIn = mock(AccommodationImageDTO.class);
        AccommodationImageDTO dtoOut = mock(AccommodationImageDTO.class);

        // Stub: cuando el DAO reciba exactamente dtoIn, devolverá dtoOut
        when(dao.save(dtoIn)).thenReturn(dtoOut);

        // =========== Act ===========
        AccommodationImageDTO result = service.save(dtoIn);

        // ========= Assert ==========
        // Estado: el servicio debe devolver lo MISMO que devolvió el DAO (misma referencia)
        assertThat(result).isSameAs(dtoOut);

        // Comportamiento: el DAO debe ser invocado exactamente una vez con dtoIn
        verify(dao, times(1)).save(dtoIn);

        // Aseguramos que no hubo más interacciones inesperadas con el DAO
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("save propaga excepciones lanzadas por el DAO")
    void save_propagatesDaoException() {
        // ========= Arrange =========
        AccommodationImageDTO dtoIn = mock(AccommodationImageDTO.class);

        // Stub: simulamos un error en la capa DAO (por ejemplo, validación/constraint)
        when(dao.save(dtoIn)).thenThrow(new IllegalArgumentException("invalid"));

        // =========== Act + Assert ===========
        // Comprobamos que el servicio NO atrapa ni altera la excepción (se propaga igual)
        assertThatThrownBy(() -> service.save(dtoIn))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid");

        // Además, verificamos la interacción esperada (una llamada) y ninguna extra
        verify(dao, times(1)).save(dtoIn);
        verifyNoMoreInteractions(dao);
    }
}
