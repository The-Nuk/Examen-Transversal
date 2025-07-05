package com.examplempresas.medi_express.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplempresas.medi_express.Model.Usuario;

@Repository// Indica que esta interfaz es un repositorio de Spring Data JPA
           // Permite realizar operaciones CRUD sobre la entidad Usuario
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Usuario findByEmail(String email);
    

}
