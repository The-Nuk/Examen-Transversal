package com.examplempresas.medi_express.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examplempresas.medi_express.Model.Usuario;
import com.examplempresas.medi_express.Repository.UsuarioRepository;

@Service // Indica que esta clase es un servicio de Spring
public class UsuarioService {
    @Autowired // Inyecta automáticamente una instancia de UsuarioRepository
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return Optional.ofNullable(usuarioRepository.findByEmail(email));
    }

    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Unimplemented method 'guardarUsuario'");
    }

    // Asigna una lista de IDs de productos a un usuario
    public Optional<Usuario> asignarProductos(Long usuarioId, List<Long> productosIds) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setProductosIds(productosIds);
            usuarioRepository.save(usuario);
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    // Agrega un producto a la lista de productos del usuario
    public Optional<Usuario> agregarProducto(Long usuarioId, Long productoId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getProductosIds() == null) {
                usuario.setProductosIds(new ArrayList<>());
            }
            usuario.getProductosIds().add(productoId);
            usuarioRepository.save(usuario);
            return Optional.of(usuario);
        }
        return Optional.empty();
    }


    // Elimina un producto de la lista de productos del usuario
    public Optional<Usuario> eliminarProducto(Long usuarioId, Long productoId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getProductosIds() != null) {
                usuario.getProductosIds().remove(productoId);
                usuarioRepository.save(usuario);
            }
            return Optional.of(usuario);
        }
        return Optional.empty();
    }
}
