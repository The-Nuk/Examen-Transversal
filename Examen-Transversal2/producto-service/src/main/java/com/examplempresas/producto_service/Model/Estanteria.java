package com.examplempresas.producto_service.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estanterias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modelo de Estantería")
public class Estanteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la estantería", example = "1")
    private Long id;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre de la estantería", example = "Estantería 1")
    private String nombre; // Ejemplo: "Estantería 1"

    @Column(length = 10)
    @Schema(description = "Fila de la estantería", example = "A")
    private String fila; // Ejemplo: "A"
}
