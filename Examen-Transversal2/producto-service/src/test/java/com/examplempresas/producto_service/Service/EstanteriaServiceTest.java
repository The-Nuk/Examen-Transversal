package com.examplempresas.producto_service.Service;

import com.examplempresas.producto_service.Model.Estanteria;
import com.examplempresas.producto_service.Repository.EstanteriaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EstanteriaServiceTest {

    @Mock
    private EstanteriaRepository estanteriaRepository;

    @InjectMocks
    private EstanteriaService estanteriaService;

    public EstanteriaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodas() {
        Estanteria e1 = Estanteria.builder().id(1L).nombre("Estantería 1").fila("A").build();
        Estanteria e2 = Estanteria.builder().id(2L).nombre("Estantería 2").fila("B").build();
        when(estanteriaRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Estanteria> result = estanteriaService.obtenerTodas();

        assertEquals(2, result.size());
        assertEquals("Estantería 1", result.get(0).getNombre());
    }

    @Test
    void testObtenerPorId() {
        Estanteria e1 = Estanteria.builder().id(1L).nombre("Estantería 1").fila("A").build();
        when(estanteriaRepository.findById(1L)).thenReturn(Optional.of(e1));

        Optional<Estanteria> result = estanteriaService.obtenerPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("Estantería 1", result.get().getNombre());
    }

    @Test
    void testGuardar() {
        Estanteria e1 = Estanteria.builder().nombre("Estantería 1").fila("A").build();
        when(estanteriaRepository.save(e1)).thenReturn(e1);

        Estanteria result = estanteriaService.guardar(e1);

        assertEquals("Estantería 1", result.getNombre());
    }

    @Test
    void testEliminar() {
        doNothing().when(estanteriaRepository).deleteById(1L);
        estanteriaService.eliminar(1L);
        verify(estanteriaRepository, times(1)).deleteById(1L);
    }
}
