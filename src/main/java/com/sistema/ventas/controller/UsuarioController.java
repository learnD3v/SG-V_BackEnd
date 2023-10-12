package com.sistema.ventas.controller;

import com.sistema.ventas.dto.ResponseDTO;
import com.sistema.ventas.model.Usuario;
import com.sistema.ventas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseDTO> save(
            @RequestBody Usuario usuarioObj) {
        return usuarioService.save(usuarioObj).build();
    }

    @PutMapping(value ="/update", consumes = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseDTO> modificarReferencia(
            @RequestParam Integer id,
            @RequestBody @Valid Usuario usuarioObj,
            @RequestParam String usuario) {
        return usuarioService.update(id, usuarioObj, usuario).build();
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "apellidoUsu") String sortField,
            @RequestParam(defaultValue = "true") boolean sortAsc,
            @RequestParam(required = false) Integer idUsuario,
            @RequestParam(required = false) String nombreUsu,
            @RequestParam(required = false) String apellidoUsu,
            @RequestParam(required = false) String correoUsu,
            @RequestParam(required = false) String estado
    ) {
        return usuarioService.getAll(page, pageSize, sortField, sortAsc, idUsuario, nombreUsu, apellidoUsu, correoUsu, estado).build();
    }


}
