package com.uniquindio.proyecto_final.accommodation_management;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LegalDocumentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.LegalDocumentServiceImpl;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.LegalDocumentDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para LegalDocumentServiceImpl.
 * Casos:
 *  - save: delega en el DAO y retorna su resultado.
 *  - save: propaga excepciones del DAO.
 */
@ExtendWith(MockitoExtension.class)
class LegalDocumentServiceImplTest {

    @Mock
    LegalDocumentDAO dao;                 // Doble para aislar persistencia

    @InjectMocks
    LegalDocumentServiceImpl service;     // SUT

    @Test
    @DisplayName("save: delega en el DAO y retorna su resultado (happy path)")
    void save_delegates_andReturns() {
        // Arrange
        LegalDocumentDTO in  = mock(LegalDocumentDTO.class);
        LegalDocumentDTO out = mock(LegalDocumentDTO.class);
        when(dao.save(in)).thenReturn(out);

        // Act
        LegalDocumentDTO res = service.save(in);

        // Assert (AAA)
        assertThat(res).isSameAs(out);
        verify(dao, times(1)).save(in);
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("save: propaga excepciones lanzadas por el DAO (error path)")
    void save_propagatesException() {
        // Arrange
        LegalDocumentDTO in = mock(LegalDocumentDTO.class);
        when(dao.save(in)).thenThrow(new IllegalStateException("dao-fail"));

        // Act + Assert
        assertThatThrownBy(() -> service.save(in))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("dao-fail");

        verify(dao).save(in);
        verifyNoMoreInteractions(dao);
    }
}
