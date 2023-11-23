package com.sistema.ventas.service;

import com.sistema.ventas.dto.ResponseDTO;

public interface HistoricoProductoService {
    ResponseDTO getAll(int page, int pageSize, String sortField, boolean sortAsc,
                       Integer idHistPdto, String detallePdto, Integer cantidad, Integer precio, String estado);
}
