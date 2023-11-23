package com.sistema.ventas.controller;

import com.sistema.ventas.dto.ResponseDTO;
import com.sistema.ventas.service.HistoricoProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/historicoProducto")
@RequiredArgsConstructor
public class HistoricoPdtoController {
    private final HistoricoProductoService productoService;
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "detallePdto") String sortField,
            @RequestParam(defaultValue = "true") boolean sortAsc,
            @RequestParam(required = false) Integer idHistPdto,
            @RequestParam(required = false) String detallePdto,
            @RequestParam(required = false) Integer cantidad,
            @RequestParam(required = false) Integer precio,
            @RequestParam(required = false) String estado
    ) {
        return productoService.getAll(page, pageSize, sortField, sortAsc, idHistPdto, detallePdto, cantidad, precio, estado).build();
    }
}
