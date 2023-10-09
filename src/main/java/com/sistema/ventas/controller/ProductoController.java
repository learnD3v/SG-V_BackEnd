package com.sistema.ventas.controller;

import com.sistema.ventas.dto.ImgPdtoDTO;
import com.sistema.ventas.dto.ResponseDTO;
import com.sistema.ventas.model.Producto;
import com.sistema.ventas.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponseDTO> save(
            @RequestPart ("producto")Producto productoObj,
            @RequestPart(value="imagen", required = false) MultipartFile imgPdtoDTO) {
        return productoService.save(productoObj, imgPdtoDTO).build();
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

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "detallePdto") String sortField,
            @RequestParam(defaultValue = "true") boolean sortAsc,
            @RequestParam(required = false) Integer idProducto,
            @RequestParam(required = false) String detallePdto,
            @RequestParam(required = false) Integer cantidad,
            @RequestParam(required = false) Integer precio,
            @RequestParam(required = false) String estado
    ) {
        return productoService.getAll(page, pageSize, sortField, sortAsc, idProducto, detallePdto, cantidad, precio, estado).build();
    }
}
