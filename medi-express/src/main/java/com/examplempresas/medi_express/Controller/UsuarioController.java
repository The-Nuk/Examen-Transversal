package com.examplempresas.medi_express.Controller;

import com.examplempresas.medi_express.UsuarioCreateDTO;
import com.examplempresas.medi_express.Model.Usuario;
import com.examplempresas.medi_express.Service.UsuarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/USUARIOS")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // GET /API/USUARIOS
    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    // GET /API/USUARIOS{id}
    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /API/USUARIOS
    // Crear nuevo usuario
    @PostMapping
    public Usuario createUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioDto) {
        Usuario usuario = Usuario.builder()
                .nombre(usuarioDto.getNombre())
                .apellido(usuarioDto.getApellido())
                .email(usuarioDto.getEmail())
                .password(usuarioDto.getPassword())
                .rol(usuarioDto.getRol())
                .build();
        return usuarioService.save(usuario);
    }

    // PUT /API/USUARIOS{id}
    // Actualizar usuario existente (incluye contraseña)
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellido(usuarioDetails.getApellido());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setPassword(usuarioDetails.getPassword());
        usuario.setRol(usuarioDetails.getRol());

        Usuario updatedUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    // DELETE /API/USUARIOS{id}
    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
