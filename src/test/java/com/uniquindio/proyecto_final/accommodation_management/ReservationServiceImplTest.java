package com.uniquindio.proyecto_final.accommodation_management;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.ReservationDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.impl.ReservationServiceImpl;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao.ReservationDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para ReservationServiceImpl.
 * Casos:
 *  - save: delega y retorna.
 *  - viewAccommodationReservations: delega y retorna lista.
 *  - makeReservations: stub → retorna null y no usa DAO.
 *  - cancel/accept/reject: 200 con body si DAO retorna DTO; 404 si DAO retorna null.
 *  - viewReservationHistory: delega y retorna lista.
 *  - viewReservationDetails: retorna DTO si existe; null si no.
 */
@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock ReservationDAO dao;
    @InjectMocks ReservationServiceImpl service;

    @Test
    @DisplayName("save: delega en DAO y retorna su resultado")
    void save_delegates_andReturns() {
        ReservationDTO in  = mock(ReservationDTO.class);
        ReservationDTO out = mock(ReservationDTO.class);
        when(dao.save(in)).thenReturn(out);

        ReservationDTO res = service.save(in);

        assertThat(res).isSameAs(out);
        verify(dao).save(in);
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("viewAccommodationReservations: delega en DAO y retorna lista")
    void viewAccommodationReservations_delegates() {
        int accId = 42;
        List<ReservationDTO> expected = List.of(mock(ReservationDTO.class));
        when(dao.viewAccommodationReservations(accId)).thenReturn(expected);

        var res = service.viewAccommodationReservations(accId);

        assertThat(res).isSameAs(expected);
        verify(dao).viewAccommodationReservations(accId);
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("makeReservations: stub → retorna null y NO usa DAO")
    void makeReservations_stub_returnsNull_noDaoCalls() {
        ResponseEntity<ReservationDTO> res =
                service.makeReservations(LocalDate.now(), LocalDate.now().plusDays(2));

        assertThat(res).isNull();
        verifyNoInteractions(dao);
    }

    @Nested
    class CancelBehavior {
        @Test
        @DisplayName("cancelReservations: 200 OK con body cuando DAO retorna DTO")
        void cancel_ok() {
            int id = 1;
            ReservationDTO dto = mock(ReservationDTO.class);
            when(dao.cancelReservations(id)).thenReturn(dto);

            var response = service.cancelReservations(id);

            assertThat(response.getStatusCode().value()).isEqualTo(200);
            assertThat(response.getBody()).isSameAs(dto);
            verify(dao).cancelReservations(id);
            verifyNoMoreInteractions(dao);
        }

        @Test
        @DisplayName("cancelReservations: 404 Not Found cuando DAO retorna null")
        void cancel_notFound() {
            int id = 1;
            when(dao.cancelReservations(id)).thenReturn(null);

            var response = service.cancelReservations(id);

            assertThat(response.getStatusCode().value()).isEqualTo(404);
            assertThat(response.getBody()).isNull();
            verify(dao).cancelReservations(id);
            verifyNoMoreInteractions(dao);
        }
    }

    @Test
    @DisplayName("viewReservationHistory: delega en DAO y retorna lista")
    void viewReservationHistory_delegates() {
        int userId = 7;
        List<ReservationDTO> expected = List.of(mock(ReservationDTO.class));
        when(dao.viewReservationHistory(userId)).thenReturn(expected);

        var res = service.viewReservationHistory(userId);

        assertThat(res).isSameAs(expected);
        verify(dao).viewReservationHistory(userId);
        verifyNoMoreInteractions(dao);
    }

    @Test
    @DisplayName("viewReservationDetails: retorna DTO si existe; null si no")
    void viewReservationDetails_returnsDto_orNull() {
        int id = 10;
        ReservationDTO dto = mock(ReservationDTO.class);
        when(dao.findById(id)).thenReturn(Optional.of(dto));
        when(dao.findById(999)).thenReturn(Optional.empty());

        assertThat(service.viewReservationDetails(id)).isSameAs(dto);
        assertThat(service.viewReservationDetails(999)).isNull();

        verify(dao).findById(id);
        verify(dao).findById(999);
        verifyNoMoreInteractions(dao);
    }

    @Nested
    class AcceptRejectBehavior {
        @Test
        @DisplayName("acceptReservationRequests: 200 OK con body cuando DAO retorna DTO")
        void accept_ok() {
            int id = 3;
            ReservationDTO dto = mock(ReservationDTO.class);
            when(dao.acceptReservationRequests(id)).thenReturn(dto);

            var response = service.acceptReservationRequests(id);

            assertThat(response.getStatusCode().value()).isEqualTo(200);
            assertThat(response.getBody()).isSameAs(dto);
            verify(dao).acceptReservationRequests(id);
            verifyNoMoreInteractions(dao);
        }

        @Test
        @DisplayName("acceptReservationRequests: 404 Not Found cuando DAO retorna null")
        void accept_notFound() {
            int id = 3;
            when(dao.acceptReservationRequests(id)).thenReturn(null);

            var response = service.acceptReservationRequests(id);

            assertThat(response.getStatusCode().value()).isEqualTo(404);
            assertThat(response.getBody()).isNull();
            verify(dao).acceptReservationRequests(id);
            verifyNoMoreInteractions(dao);
        }

        @Test
        @DisplayName("rejectReservationRequests: 200 OK con body cuando DAO retorna DTO")
        void reject_ok() {
            int id = 4;
            ReservationDTO dto = mock(ReservationDTO.class);
            when(dao.rejectReservationRequests(id)).thenReturn(dto);

            var response = service.rejectReservationRequests(id);

            assertThat(response.getStatusCode().value()).isEqualTo(200);
            assertThat(response.getBody()).isSameAs(dto);
            verify(dao).rejectReservationRequests(id);
            verifyNoMoreInteractions(dao);
        }

        @Test
        @DisplayName("rejectReservationRequests: 404 Not Found cuando DAO retorna null")
        void reject_notFound() {
            int id = 4;
            when(dao.rejectReservationRequests(id)).thenReturn(null);

            var response = service.rejectReservationRequests(id);

            assertThat(response.getStatusCode().value()).isEqualTo(404);
            assertThat(response.getBody()).isNull();
            verify(dao).rejectReservationRequests(id);
            verifyNoMoreInteractions(dao);
        }
    }
}
