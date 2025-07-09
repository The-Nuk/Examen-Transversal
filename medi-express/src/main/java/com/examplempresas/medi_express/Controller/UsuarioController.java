package com.examplempresas.medi_express.Controller;

import com.examplempresas.medi_express.UsuarioCreateDTO;
import com.examplempresas.medi_express.Model.Usuario;
import com.examplempresas.medi_express.Service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/API/USUARIOS")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // GET /API/USUARIOS
    // Obtener todos los usuarios
    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    // GET /API/USUARIOS{id}
    // Obtener usuario por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario específico por su ID.")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<Usuario> getUsuarioById(
        @Parameter(description = "ID del usuario a buscar")
        @PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /API/USUARIOS
    // Crear nuevo usuario
    @PostMapping
    @Operation(summary = "Crear nuevo usuario", description = "Registra un nuevo usuario en el sistema.")
    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente")
    public Usuario createUsuario(
        @RequestBody 
        @Valid UsuarioCreateDTO usuarioDto) {
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
    @Operation(summary = "Actualizar usuario", description = "Actualiza los detalles de un usuario existente.")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<Usuario> updateUsuario(
        @Parameter(description = "ID del usuario a actualizar")
        @PathVariable Long id,
        @Valid
        @RequestBody Usuario usuarioDetails) {
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
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario del sistema por su ID.")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<Void> deleteUsuario(
        @Parameter(description = "ID del usuario a eliminar")
        @PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /API/USUARIOS/{id}/productos
    // Asignar productos a un usuario
    @PutMapping("/{id}/productos")
    @Operation(summary = "Asignar productos a un usuario", description = "Asigna una lista de IDs de productos a un usuario.")
    @ApiResponse(responseCode = "200", description = "Productos asignados correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<Usuario> asignarProductos(
        @Parameter(description = "ID del usuario") @PathVariable Long id,
        @RequestBody List<Long> productosIds) {
        Optional<Usuario> usuarioOpt = usuarioService.asignarProductos(id, productosIds);
        return usuarioOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET /API/USUARIOS/{id}/productos
    // Obtener los IDs de productos de un usuario
    @GetMapping("/{id}/productos")
    @Operation(summary = "Obtener productos de un usuario", description = "Devuelve la lista de IDs de productos asociados a un usuario.")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<List<Long>> getProductosIdsDeUsuario(
        @Parameter(description = "ID del usuario") @PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(id);
        return usuarioOpt.map(usuario -> ResponseEntity.ok(usuario.getProductosIds()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

