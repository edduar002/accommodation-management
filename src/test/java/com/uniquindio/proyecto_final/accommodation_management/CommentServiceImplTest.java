package com.uniquindio.proyecto_final.accommodation_management;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.CommentDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.CommentServiceImpl;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.CommentDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para CommentServiceImpl.
 * Casos:
 *  - save: delega en el DAO y retorna su resultado.
 *  - save: propaga excepciones del DAO.
 *  - commentsList: retorna lista vacía (stub) y no toca el DAO.
 *  - respondComments: retorna null (stub) y no toca el DAO.
 */
@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    CommentDAO dao;                 // doble para aislar persistencia

    @InjectMocks
    CommentServiceImpl service;     // SUT

    @Test
    @DisplayName("save: delega en el DAO y retorna su resultado (happy path)")
    void save_delegates_andReturns() {
        // Arrange
        CommentDTO in  = mock(CommentDTO.class);
        CommentDTO out = mock(CommentDTO.class);
        when(dao.save(in)).thenReturn(out);

        // Act
        CommentDTO res = service.save(in);

        // Assert
        assertThat(res).isSameAs(out);
        verify(dao).save(in);
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("save: propaga excepciones del DAO (error path)")
    void save_propagatesException() {
        // Arrange
        CommentDTO in = mock(CommentDTO.class);
        when(dao.save(in)).thenThrow(new IllegalStateException("dao-fail"));

        // Act + Assert
        assertThatThrownBy(() -> service.save(in))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("dao-fail");

        verify(dao).save(in);
        verifyNoMoreInteractions(dao);
    }

    @Nested
    class StubsBehavior {

        @Test
        @DisplayName("commentsList: retorna lista vacía y NO usa el DAO (stub)")
        void commentsList_returnsEmpty_andNoDaoCalls() {
            // Act
            List<CommentDTO> res = service.commentsList(123);

            // Assert
            assertThat(res).isEmpty();
            verifyNoInteractions(dao);
        }

        @Test
        @DisplayName("respondComments: retorna null y NO usa el DAO (stub)")
        void respondComments_returnsNull_andNoDaoCalls() {
            // Act
            CommentDTO res = service.respondComments(10, mock(CommentDTO.class));

            // Assert
            assertThat(res).isNull();
            verifyNoInteractions(dao);
        }
    }
}
