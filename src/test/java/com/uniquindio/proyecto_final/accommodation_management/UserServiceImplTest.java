package com.uniquindio.proyecto_final.accommodation_management;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ChangePasswordDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.UserServiceImpl;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.UserDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para UserServiceImpl.
 * Casos:
 *  - save: delega y retorna; propaga excepción.
 *  - login: delega y retorna (null si DAO retorna null).
 *  - edit: actualiza nombre si existe; empty si no existe.
 *  - changePassword: ok si old coincide; empty si old no coincide; empty si no existe.
 *  - recoveryPassword: ok si existe; empty si no existe.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock UserDAO dao;
    @InjectMocks UserServiceImpl service;

    // ---- save ----
    @Test
    @DisplayName("save: delega en el DAO y retorna su resultado")
    void save_delegates_andReturns() {
        UserDTO in  = mock(UserDTO.class);
        UserDTO out = mock(UserDTO.class);
        when(dao.save(in)).thenReturn(out);

        UserDTO res = service.save(in);

        assertThat(res).isSameAs(out);
        verify(dao).save(in);
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("save: propaga excepciones del DAO")
    void save_propagatesException() {
        UserDTO in = mock(UserDTO.class);
        when(dao.save(in)).thenThrow(new IllegalStateException("dao-fail"));

        assertThatThrownBy(() -> service.save(in))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("dao-fail");

        verify(dao).save(in);
        verifyNoMoreInteractions(dao);
    }

    // ---- login ----
    @Nested
    class LoginBehavior {
        @Test
        @DisplayName("login: delega en DAO y retorna el usuario autenticado")
        void login_delegates_andReturns() {
            LoginDTO creds = mock(LoginDTO.class);
            UserDTO user = mock(UserDTO.class);
            when(dao.login(creds)).thenReturn(user);

            UserDTO res = service.login(creds);

            assertThat(res).isSameAs(user);
            verify(dao).login(creds);
            verifyNoMoreInteractions(dao);
        }

        @Test
        @DisplayName("login: retorna null cuando DAO no autentica")
        void login_returnsNull_whenDaoReturnsNull() {
            LoginDTO creds = mock(LoginDTO.class);
            when(dao.login(creds)).thenReturn(null);

            UserDTO res = service.login(creds);

            assertThat(res).isNull();
            verify(dao).login(creds);
            verifyNoMoreInteractions(dao);
        }
    }

    // ---- edit ----
    @Nested
    class EditBehavior {
        @Test
        @DisplayName("edit: si existe, actualiza name y guarda; retorna Optional con actualizado")
        void edit_updatesName_whenFound() {
            int id = 5;
            UserDTO existing = mock(UserDTO.class);
            UserDTO input    = mock(UserDTO.class);

            when(input.getName()).thenReturn("Nuevo Nombre");
            when(dao.findById(id)).thenReturn(Optional.of(existing));
            when(dao.save(existing)).thenReturn(existing);

            var result = service.edit(id, input);

            assertThat(result).isPresent();
            assertThat(result.get()).isSameAs(existing);
            verify(existing).setName("Nuevo Nombre");
            verify(dao).findById(id);
            verify(dao).save(existing);
            verifyNoMoreInteractions(dao);
        }

        @Test
        @DisplayName("edit: si NO existe, retorna Optional.empty() y NO guarda")
        void edit_returnsEmpty_whenNotFound() {
            int id = 404;
            when(dao.findById(id)).thenReturn(Optional.empty());

            var result = service.edit(id, mock(UserDTO.class));

            assertThat(result).isEmpty();
            verify(dao).findById(id);
            verify(dao, never()).save(any());
            verifyNoMoreInteractions(dao);
        }
    }

    // ---- changePassword ----
    @Nested
    class ChangePasswordBehavior {
        @Test
        @DisplayName("changePassword: actualiza si old coincide; guarda y retorna Optional con DTO")
        void changePassword_ok_whenOldMatches() {
            int id = 10;
            UserDTO user = mock(UserDTO.class);
            ChangePasswordDTO payload = mock(ChangePasswordDTO.class);

            when(dao.findById(id)).thenReturn(Optional.of(user));
            when(user.getPassword()).thenReturn("old");
            when(payload.getOldPassword()).thenReturn("old");
            when(payload.getNewPassword()).thenReturn("new");

            var result = service.changePassword(id, payload);

            assertThat(result).isPresent();
            assertThat(result.get()).isSameAs(user);
            verify(user).setPassword("new");
            verify(dao).findById(id);
            verify(dao).save(user);
            verifyNoMoreInteractions(dao);
        }

        @Test
        @DisplayName("changePassword: retorna empty si old NO coincide; NO guarda")
        void changePassword_empty_whenOldDoesNotMatch() {
            int id = 10;
            UserDTO user = mock(UserDTO.class);
            ChangePasswordDTO payload = mock(ChangePasswordDTO.class);

            when(dao.findById(id)).thenReturn(Optional.of(user));
            when(user.getPassword()).thenReturn("old");
            when(payload.getOldPassword()).thenReturn("wrong");

            var result = service.changePassword(id, payload);

            assertThat(result).isEmpty();
            verify(dao).findById(id);
            verify(user, never()).setPassword(anyString());
            verify(dao, never()).save(any());
            verifyNoMoreInteractions(dao);
        }

        @Test
        @DisplayName("changePassword: retorna empty si el usuario NO existe")
        void changePassword_empty_whenNotFound() {
            int id = 404;
            when(dao.findById(id)).thenReturn(Optional.empty());

            var result = service.changePassword(id, mock(ChangePasswordDTO.class));

            assertThat(result).isEmpty();
            verify(dao).findById(id);
            verify(dao, never()).save(any());
            verifyNoMoreInteractions(dao);
        }
    }

    // ---- recoveryPassword ----
    @Nested
    class RecoveryPasswordBehavior {
        @Test
        @DisplayName("recoveryPassword: actualiza password si existe; guarda y retorna Optional con DTO")
        void recoveryPassword_ok_whenFound() {
            int id = 7;
            UserDTO user = mock(UserDTO.class);
            when(dao.findById(id)).thenReturn(Optional.of(user));

            var result = service.recoveryPassword(id, "newpass");

            assertThat(result).isPresent();
            assertThat(result.get()).isSameAs(user);
            verify(user).setPassword("newpass");
            verify(dao).findById(id);
            verify(dao).save(user);
            verifyNoMoreInteractions(dao);
        }

        @Test
        @DisplayName("recoveryPassword: retorna empty si NO existe; NO guarda")
        void recoveryPassword_empty_whenNotFound() {
            int id = 404;
            when(dao.findById(id)).thenReturn(Optional.empty());

            var result = service.recoveryPassword(id, "np");

            assertThat(result).isEmpty();
            verify(dao).findById(id);
            verify(dao, never()).save(any());
            verifyNoMoreInteractions(dao);
        }
    }
}
