package com.examplempresas.producto_service.Repository;

import com.examplempresas.producto_service.Model.Estanteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstanteriaRepository extends JpaRepository<Estanteria, Long> {
}
