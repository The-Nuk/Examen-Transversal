package com.examplempresas.producto_service.Controller;

import com.examplempresas.producto_service.Model.Estanteria;
import com.examplempresas.producto_service.Service.EstanteriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estanterias")
@Tag(name = "Estanterías", description = "Operaciones relacionadas con las estanterías")
public class EstanteriaController {

    @Autowired
    private EstanteriaService estanteriaService;

    @GetMapping
    @Operation(summary = "Obtener todas las estanterías", description = "Devuelve una lista de todas las estanterías registradas.")
    @ApiResponse(responseCode = "200", description = "Lista de estanterías obtenida correctamente")
    public List<Estanteria> obtenerTodas() {
        return estanteriaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener estantería por ID", description = "Devuelve una estantería específica por su ID.")
    @ApiResponse(responseCode = "200", description = "Estantería encontrada")
    @ApiResponse(responseCode = "404", description = "Estantería no encontrada")
    public ResponseEntity<Estanteria> obtenerPorId(@PathVariable Long id) {
        Optional<Estanteria> estanteria = estanteriaService.obtenerPorId(id);
        return estanteria.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nueva estantería", description = "Registra una nueva estantería en el sistema.")
    @ApiResponse(responseCode = "201", description = "Estantería creada correctamente")
    public Estanteria crear(@RequestBody Estanteria estanteria) {
        return estanteriaService.guardar(estanteria);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estantería", description = "Elimina una estantería del sistema por su ID.")
    @ApiResponse(responseCode = "204", description = "Estantería eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Estantería no encontrada")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estanteriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
