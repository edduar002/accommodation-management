package com.uniquindio.proyecto_final.accommodation_management;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.AccommodationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.AccommodationServiceImpl;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.AccommodationDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Suite de pruebas UNITARIAS para AccommodationServiceImpl.
 *
 * Qué validamos:
 *  - Métodos que sólo DELEGAN al DAO (save, searchAvailableAccommodations, ownAccommodationList,
 *    detail, averageGrades) → que llamen al DAO correcto y devuelvan su resultado.
 *  - Reglas simples antes de guardar (edit: actualiza price; delete: pone active=false)
 *    → que modifiquen el DTO y llamen a dao.save(...) con el valor esperado.
 *
 * Patrón AAA en cada test:
 *  Arrange (preparación: stubs y datos) → Act (ejecución del SUT) → Assert (estado + verify de interacciones).
 */
@ExtendWith(MockitoExtension.class) // habilita @Mock/@InjectMocks sin levantar Spring
class AccommodationServiceImplTest {

    @Mock
    AccommodationDAO dao; // Doble de prueba para aislar al servicio (SUT) de la capa de persistencia

    @InjectMocks
    AccommodationServiceImpl service; // SUT = clase que estamos probando

    // Helper para construir DTOs usados en los tests.
    // Nota: price es Integer y el flag se consulta con isActive() (no getActive()).
    private static AccommodationDTO dto(Integer price, Boolean active) {
        AccommodationDTO a = new AccommodationDTO();
        if (price != null)  a.setPrice(price);
        if (active != null) a.setActive(active);
        return a;
    }

    // ---------- Delegaciones simples ----------

    @Test
    @DisplayName("save: delega en el DAO y retorna su resultado")
    void save_delegates_andReturns() {
        // Arrange
        AccommodationDTO in  = dto(100, true);
        AccommodationDTO out = dto(100, true);
        when(dao.save(in)).thenReturn(out); // stub del DAO

        // Act
        AccommodationDTO res = service.save(in);

        // Assert (estado + comportamiento)
        assertThat(res).isSameAs(out);  // devuelve exactamente lo que regresó el DAO
        verify(dao).save(in);           // se llamó al DAO con el mismo objeto
        verifyNoMoreInteractions(dao);  // sin llamadas extra
    }

    @Test
    @DisplayName("searchAvailableAccommodations: delega en el DAO")
    void searchAvailable_delegates() {
        // Arrange
        List<AccommodationDTO> expected = List.of(dto(200, true));
        when(dao.searchAvailableAccommodations()).thenReturn(expected);

        // Act
        var res = service.searchAvailableAccommodations();

        // Assert
        assertThat(res).isSameAs(expected);
        verify(dao).searchAvailableAccommodations();
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("ownAccommodationList: delega en el DAO (host específico)")
    void ownAccommodationList_delegates() {
        // Arrange
        int hostId = 7;
        List<AccommodationDTO> expected = List.of(dto(300, true));
        when(dao.ownAccommodationList(hostId)).thenReturn(expected);

        // Act
        var res = service.ownAccommodationList(hostId);

        // Assert
        assertThat(res).isSameAs(expected);
        verify(dao).ownAccommodationList(hostId);
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("detail: retorna el DTO cuando existe; null cuando no")
    void detail_returnsDto_orNull() {
        // Arrange
        when(dao.findById(1)).thenReturn(Optional.of(dto(150, true))); // encontrado
        when(dao.findById(999)).thenReturn(Optional.empty());          // no encontrado

        // Act + Assert (dos verificaciones rápidas)
        assertThat(service.detail(1)).isNotNull();
        assertThat(service.detail(999)).isNull();

        verify(dao).findById(1);
        verify(dao).findById(999);
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("averageGrades: delega en el DAO")
    void averageGrades_delegates() {
        // Arrange
        when(dao.averageGrades(10)).thenReturn(4.25);

        // Act
        Double res = service.averageGrades(10);

        // Assert
        assertThat(res).isEqualTo(4.25);
        verify(dao).averageGrades(10);
        verifyNoMoreInteractions(dao);
    }

    // ---------- Reglas (edit / delete) ----------
    @Nested
    class EditBehavior {

        @Test
        @DisplayName("edit: si existe, actualiza price y guarda; retorna Optional con el guardado")
        void edit_updatesPrice_whenFound() {
            // Arrange
            int id = 5;
            var existing = dto(100, true); // lo que trae findById
            var input    = dto(200, true); // nuevo precio a aplicar

            when(dao.findById(id)).thenReturn(Optional.of(existing));
            // Hacemos que el mock de save(...) devuelva el mismo objeto que recibe,
            // así podemos validar exactamente qué se intentó guardar
            when(dao.save(any())).thenAnswer(inv -> inv.getArgument(0));

            // Act
            var result = service.edit(id, input);

            // Assert (estado)
            assertThat(result).isPresent();
            assertThat(result.get().getPrice()).isEqualTo(200);

            // Assert (comportamiento): capturamos el DTO con el que se llamó a save(...)
            ArgumentCaptor<AccommodationDTO> cap = ArgumentCaptor.forClass(AccommodationDTO.class);
            verify(dao).findById(id);
            verify(dao).save(cap.capture());
            verifyNoMoreInteractions(dao);

            // El DTO que se guardó debe tener el price actualizado
            assertThat(cap.getValue().getPrice()).isEqualTo(200);
        }

        @Test
        @DisplayName("edit: si NO existe, retorna Optional.empty() y NO guarda")
        void edit_returnsEmpty_whenNotFound() {
            // Arrange
            int id = 404;
            when(dao.findById(id)).thenReturn(Optional.empty());

            // Act
            var result = service.edit(id, dto(999, true));

            // Assert
            assertThat(result).isEmpty();       // nada que editar
            verify(dao).findById(id);
            verify(dao, never()).save(any());   // no se intenta guardar
            verifyNoMoreInteractions(dao);
        }
    }

    @Nested
    class DeleteBehavior {

        @Test
        @DisplayName("delete: si existe, marca active=false y guarda; retorna Optional con el guardado")
        void delete_setsInactive_whenFound() {
            // Arrange
            int id = 8;
            var existing = dto(100, true); // actualmente activo
            when(dao.findById(id)).thenReturn(Optional.of(existing));
            when(dao.save(any())).thenAnswer(inv -> inv.getArgument(0));

            // Act
            var result = service.delete(id);

            // Assert (estado)
            assertThat(result).isPresent();
            assertThat(result.get().isActive()).isFalse(); // la regla se aplicó

            // Assert (comportamiento)
            ArgumentCaptor<AccommodationDTO> cap = ArgumentCaptor.forClass(AccommodationDTO.class);
            verify(dao).findById(id);
            verify(dao).save(cap.capture());
            verifyNoMoreInteractions(dao);

            // Confirmamos que el objeto guardado venía con active=false
            assertThat(cap.getValue().isActive()).isFalse();
        }

        @Test
        @DisplayName("delete: si NO existe, retorna Optional.empty() y NO guarda")
        void delete_returnsEmpty_whenNotFound() {
            // Arrange
            int id = 404;
            when(dao.findById(id)).thenReturn(Optional.empty());

            // Act
            var result = service.delete(id);

            // Assert
            assertThat(result).isEmpty();
            verify(dao).findById(id);
            verify(dao, never()).save(any()); // no se guarda nada si no existe
            verifyNoMoreInteractions(dao);
        }
    }
}
