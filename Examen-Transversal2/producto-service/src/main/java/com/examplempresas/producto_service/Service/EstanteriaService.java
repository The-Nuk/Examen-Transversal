package com.examplempresas.producto_service.Service;

import com.examplempresas.producto_service.Model.Estanteria;
import com.examplempresas.producto_service.Repository.EstanteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstanteriaService {

    @Autowired
    private EstanteriaRepository estanteriaRepository;

    public List<Estanteria> obtenerTodas() {
        return estanteriaRepository.findAll();
    }

    public Optional<Estanteria> obtenerPorId(Long id) {
        return estanteriaRepository.findById(id);
    }

    public Estanteria guardar(Estanteria estanteria) {
        return estanteriaRepository.save(estanteria);
    }

    public void eliminar(Long id) {
        estanteriaRepository.deleteById(id);
    }
}
