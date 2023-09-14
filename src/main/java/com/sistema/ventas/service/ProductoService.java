package com.sistema.ventas.service;

import com.sistema.ventas.dto.ResponseDTO;
import com.sistema.ventas.model.Producto;

public interface ProductoService {
    ResponseDTO save(Producto productoObj);

    ResponseDTO update(Integer id, Producto productoObj, String usuario);

    ResponseDTO activarInactivar(Integer id, String usuario);
}
