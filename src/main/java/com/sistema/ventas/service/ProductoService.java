package com.sistema.ventas.service;

import com.sistema.ventas.dto.ImgPdtoDTO;
import com.sistema.ventas.dto.ResponseDTO;
import com.sistema.ventas.model.Producto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductoService {
    ResponseDTO save(Producto productoObj);

    ResponseDTO update(Integer id, Producto productoObj, String usuario);

    ResponseDTO activarInactivar(Integer id, String usuario);

    ResponseDTO getAll(int page, int pageSize, String sortField, boolean sortAsc,
                       Integer idProducto, String detallePdto, Integer cantidad, Integer precio, String estado);
}
