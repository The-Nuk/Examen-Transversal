package com.examplempresas.medi_express.Controller;

import com.examplempresas.medi_express.Model.Usuario;
import com.examplempresas.medi_express.Service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)// Indica que solo se cargue el controlador y no toda la aplicación
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos a JSON

    @Test // Test para obtener todos los usuarios
    public void testGetAllUsuarios() throws Exception {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Mario")
                .apellido("Soto")
                .email("mario@mail.com")
                .password("123456")
                .rol("USER")
                .build();

        when(usuarioService.findAll()).thenReturn(Arrays.asList(usuario));

        mockMvc.perform(get("/API/USUARIOS"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Mario"));
    }

    @Test // Test para obtener un usuario por ID existente
    public void testGetUsuarioById_existente() throws Exception {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Carla")
                .apellido("González")
                .email("carla@mail.com")
                .password("123456")
                .rol("ADMIN")
                .build();

        when(usuarioService.findById(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/API/USUARIOS/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carla"));
    }

    @Test // Test para obtener un usuario por ID no existente
    public void testGetUsuarioById_noExistente() throws Exception {
        when(usuarioService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/API/USUARIOS/999"))
                .andExpect(status().isNotFound());
    }

    @Test 
    public void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioService).deleteById(1L);

        mockMvc.perform(delete("/API/USUARIOS/1"))
                .andExpect(status().isNoContent());
    }

    
}
