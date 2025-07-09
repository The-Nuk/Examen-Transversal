package com.examplempresas.medi_express.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductoClienteService {

    @Autowired
    private RestTemplate restTemplate;

    public ProductoDTO obtenerProductoPorId(Long productoId) {
        String url = "http://localhost:8081/api/productos/" + productoId;
        return restTemplate.getForObject(url, ProductoDTO.class);
    }
}
