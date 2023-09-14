package com.sistema.ventas.controller;

import com.sistema.ventas.dto.ResponseDTO;
import com.sistema.ventas.model.Producto;
import com.sistema.ventas.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> save(@RequestBody Producto productoObj) {
        return productoService.save(productoObj).build();
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> modificarReferencia(
            @RequestParam Integer id,
            @RequestBody @Valid Producto productoObj,
            @RequestParam String usuario) {
        return productoService.update(id, productoObj, usuario).build();
    }
    @PostMapping("/activar-inactivar")
    public ResponseEntity<ResponseDTO> activarInactivarPdto(@RequestParam Integer id, @RequestParam String usuario) {
        return productoService.activarInactivar(id, usuario).build();
    }
}
