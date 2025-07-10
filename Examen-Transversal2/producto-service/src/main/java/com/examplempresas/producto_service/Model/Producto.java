package com.examplempresas.producto_service.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modelo de Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del producto", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nombre del producto", example = "Producto 1")
    private String nombre;

    @Column(nullable = false)
    @Schema(description = "Precio del producto", example = "100.0")
    private Double precio;

    @Column(length = 255)
    @Schema(description = "Descripción del producto", example = "Descripción del Producto 1")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "estanteria_id")
    @Schema(description = "Estantería a la que pertenece el producto")
    private Estanteria estanteria;

}
