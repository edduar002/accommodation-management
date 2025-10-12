package com.uniquindio.proyecto_final.accommodation_management;


import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AdministratorDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AdministratorServiceImpl;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AdministratorDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para AdministratorServiceImpl.
 * Casos cubiertos:
 *  - save: delegación al DAO y retorno del resultado.
 *  - save: propagación de excepciones desde el DAO.
 *  - login: delegación al DAO y retorno del administrador autenticado.
 *  - login: retorno nulo cuando las credenciales no son válidas (DAO devuelve null).
 */
@ExtendWith(MockitoExtension.class)
class AdministratorServiceImplTest {

    @Mock
    AdministratorDAO dao;                         // Doble para aislar la capa de persistencia

    @InjectMocks
    AdministratorServiceImpl service;             // SUT

    // ---------- save ----------
    @Test
    @DisplayName("save: delega en el DAO y retorna su resultado (happy path)")
    void save_delegates_andReturns() {
        // Arrange
        AdministratorDTO in  = mock(AdministratorDTO.class); // usamos mocks para no acoplar a campos
        AdministratorDTO out = mock(AdministratorDTO.class);
        when(dao.save(in)).thenReturn(out);

        // Act
        AdministratorDTO res = service.save(in);

        // Assert
        assertThat(res).isSameAs(out);
        verify(dao, times(1)).save(in);
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("save: propaga excepciones del DAO (error path)")
    void save_propagatesException() {
        // Arrange
        AdministratorDTO in = mock(AdministratorDTO.class);
        when(dao.save(in)).thenThrow(new IllegalStateException("dao-fail"));

        // Act + Assert
        assertThatThrownBy(() -> service.save(in))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("dao-fail");

        verify(dao).save(in);
        verifyNoMoreInteractions(dao);
    }

    // ---------- login ----------
    @Nested
    class LoginBehavior {

        @Test
        @DisplayName("login: delega en el DAO y retorna el administrador autenticado")
        void login_delegates_andReturns() {
            // Arrange
            LoginDTO credentials = mock(LoginDTO.class);
            AdministratorDTO admin = mock(AdministratorDTO.class);
            when(dao.login(credentials)).thenReturn(admin);

            // Act
            AdministratorDTO res = service.login(credentials);

            // Assert
            assertThat(res).isSameAs(admin);
            verify(dao).login(credentials);
            verifyNoMoreInteractions(dao);
        }

        @Test
        @DisplayName("login: retorna null cuando las credenciales no son válidas")
        void login_returnsNull_whenInvalidCredentials() {
            // Arrange
            LoginDTO credentials = mock(LoginDTO.class);
            when(dao.login(credentials)).thenReturn(null); // DAO no autentica

            // Act
            AdministratorDTO res = service.login(credentials);

            // Assert
            assertThat(res).isNull();
            verify(dao).login(credentials);
            verifyNoMoreInteractions(dao);
        }
    }
}