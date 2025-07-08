package com.examplempresas.medi_express.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examplempresas.medi_express.Model.Usuario;
import com.examplempresas.medi_express.Repository.UsuarioRepository;

@Service                      // Indica que esta clase es un servicio de Spring
public class UsuarioService {
    @Autowired                // Inyecta automáticamente una instancia de UsuarioRepository
                              // para poder realizar operaciones sobre la entidad Usuario
    private UsuarioRepository usuarioRepository;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarUsuario'");
    }

}
