package com.examplempresas.producto_service.Controller;

import com.examplempresas.producto_service.Model.Estanteria;
import com.examplempresas.producto_service.Service.EstanteriaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EstanteriaControllerTest {

    @Mock
    private EstanteriaService estanteriaService;

    @InjectMocks
    private EstanteriaController estanteriaController;

    public EstanteriaControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodas() {
        Estanteria e1 = Estanteria.builder().id(1L).nombre("Estantería 1").fila("A").build();
        Estanteria e2 = Estanteria.builder().id(2L).nombre("Estantería 2").fila("B").build();
        when(estanteriaService.obtenerTodas()).thenReturn(Arrays.asList(e1, e2));

        List<Estanteria> result = estanteriaController.obtenerTodas();

        assertEquals(2, result.size());
    }

    @Test
    void testObtenerPorId_Found() {
        Estanteria e1 = Estanteria.builder().id(1L).nombre("Estantería 1").fila("A").build();
        when(estanteriaService.obtenerPorId(1L)).thenReturn(Optional.of(e1));

        ResponseEntity<Estanteria> response = estanteriaController.obtenerPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Estantería 1", response.getBody().getNombre());
    }

    @Test
    void testObtenerPorId_NotFound() {
        when(estanteriaService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Estanteria> response = estanteriaController.obtenerPorId(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCrear() {
        Estanteria e1 = Estanteria.builder().nombre("Estantería 1").fila("A").build();
        when(estanteriaService.guardar(e1)).thenReturn(e1);

        Estanteria result = estanteriaController.crear(e1);

        assertEquals("Estantería 1", result.getNombre());
    }

    @Test
    void testEliminar() {
        doNothing().when(estanteriaService).eliminar(1L);

        ResponseEntity<Void> response = estanteriaController.eliminar(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(estanteriaService, times(1)).eliminar(1L);
    }

}
