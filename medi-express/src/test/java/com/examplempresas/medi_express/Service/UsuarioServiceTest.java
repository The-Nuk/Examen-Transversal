package com.examplempresas.medi_express.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.examplempresas.medi_express.Model.Usuario;
import com.examplempresas.medi_express.Repository.UsuarioRepository;

import jakarta.inject.Inject;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarUsuario_codificaPassword() {
        Usuario usuario = Usuario.builder()
            .id(1L)
            .nombre("Laura")
            .apellido("Riquelme")
            .email("laura@demo.cl")
            .password("123456")
            .rol("USER")
            .build();

        when(passwordEncoder.encode("123456")).thenReturn("encodedPass");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(i -> i.getArgument(0));

        Usuario resultado = usuarioService.save(usuario);

        assertNotNull(resultado);
        assertEquals("encodedPass", resultado.getPassword());
        verify(passwordEncoder).encode("123456");
        verify(usuarioRepository).save(usuario);
        
    }

    @Test
    public void testBuscarPorId_existente() {
        Usuario usuario = Usuario.builder().id(1L).nombre("Mario").build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));


        Optional<Usuario> resultado = usuarioService.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Mario", resultado.get().getNombre());
    }

    @Test
    public void testBuscarPorId_noExistente() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Usuario> resultado = usuarioService.findById(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    public void testBuscarTodos() {
        List<Usuario> usuarios = Arrays.asList(
            Usuario.builder()
                .id(1L)
                .nombre("Ana")
                .apellido("Perez")
                .email("ana@mail.com")
                .password("pass")
                .rol("USER")
                .build(),
            Usuario.builder()
                .id(2L)
                .nombre("Juan")
                .apellido("Gomez")
                .email("juan@mail.com")
                .password("pass")
                .rol("ADMIN")
                .build()
        );
        

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.findAll();

        assertEquals(2, resultado.size());
        assertEquals("Ana", resultado.get(0).getNombre());
    }

    @Test
    public void testBuscarPorEmail() {
        Usuario usuario = Usuario.builder()
            .id(1L)
            .nombre("Pedro")
            .apellido("Soto")
            .email("pedro@mail.com")
            .password("pass")
            .rol("USER")
            .build();

        when(usuarioRepository.findByEmail("pedro@mail.com")).thenReturn(usuario);

        Optional<Usuario> resultado = usuarioService.findByEmail("pedro@mail.com");

        assertTrue(resultado.isPresent());
        assertEquals("Pedro", resultado.get().getNombre());
    }

    @Test
    public void testEliminarPorId() {

        Long id = 1L;
        usuarioService.deleteById(id);
        verify(usuarioRepository).deleteById(id);

    }


}
