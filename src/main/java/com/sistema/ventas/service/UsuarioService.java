package com.sistema.ventas.service;

import com.sistema.ventas.dto.ResponseDTO;
import com.sistema.ventas.model.Usuario;

public interface UsuarioService {

    ResponseDTO save(Usuario usuarioObj);

    ResponseDTO update(Integer id, Usuario usuarioObj, String usuario);

    ResponseDTO activarInactivar(Integer id, String usuario);

    ResponseDTO getAll(int page, int pageSize, String sortField, boolean sortAsc,
                       Integer idUsuario, String nombre, String apellido, String correo, String estado);
}
