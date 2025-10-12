package com.uniquindio.proyecto_final.accommodation_management;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationServiceDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AccommodationServiceServiceImpl;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationServiceDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para AccommodationServiceServiceImpl.
 * Lógica: delegación directa a dao.save(dto).
 *
 * Patrón AAA:
 *  - Arrange: preparamos mocks/stubs
 *  - Act    : ejecutamos el SUT
 *  - Assert : verificamos resultado e interacciones
 */
@ExtendWith(MockitoExtension.class)
class AccommodationServiceServiceImplTest {

    @Mock
    AccommodationServiceDAO dao;                // Doble de prueba (aislamos la capa de persistencia)

    @InjectMocks
    AccommodationServiceServiceImpl service;    // SUT (System Under Test)

    @Test
    @DisplayName("save delega en el DAO y retorna su resultado (happy path)")
    void save_delegates_andReturns() {
        // Arrange
        AccommodationServiceDTO dtoIn  = mock(AccommodationServiceDTO.class); // usamos mocks para evitar acoplar a campos
        AccommodationServiceDTO dtoOut = mock(AccommodationServiceDTO.class);
        when(dao.save(dtoIn)).thenReturn(dtoOut);

        // Act
        AccommodationServiceDTO result = service.save(dtoIn);

        // Assert (estado + comportamiento)
        assertThat(result).isSameAs(dtoOut);  // retorna exactamente lo que devuelve el DAO
        verify(dao, times(1)).save(dtoIn);    // se invoca el DAO con el mismo objeto
        verifyNoMoreInteractions(dao);        // no hay llamadas extra
    }

    @Test
    @DisplayName("save propaga excepciones del DAO (error path)")
    void save_propagatesException() {
        // Arrange
        AccommodationServiceDTO dtoIn = mock(AccommodationServiceDTO.class);
        when(dao.save(dtoIn)).thenThrow(new IllegalStateException("dao-fail"));

        // Act + Assert
        assertThatThrownBy(() -> service.save(dtoIn))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("dao-fail");

        verify(dao).save(dtoIn);
        verifyNoMoreInteractions(dao);
    }
}
