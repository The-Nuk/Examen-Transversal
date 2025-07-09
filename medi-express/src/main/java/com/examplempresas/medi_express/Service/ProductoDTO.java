package com.examplempresas.medi_express.Service;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private String descripcion;
}
