package com.sistema.ventas.service;

import com.sistema.ventas.dto.ResponseDTO;
import com.sistema.ventas.dto.TableDTO;
import com.sistema.ventas.model.Usuario;
import com.sistema.ventas.repository.UsuarioRepository;
import com.sistema.ventas.specification.GenericSpecification;
import com.sistema.ventas.util.View;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UsuarioServicelmpl extends GenericSpecification<Usuario> implements UsuarioService{
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ResponseDTO save(Usuario usuarioObj) {
        try {
            Usuario usuario = new Usuario();
            usuario.setNombreUsu(usuarioObj.getNombreUsu());
            usuario.setApellidoUsu(usuarioObj.getApellidoUsu());
            usuario.setCorreoUsu(usuarioObj.getCorreoUsu());
            usuario.setEstado("A");

            usuarioRepository.save(usuario);

            return new ResponseDTO("Empleado guardado con éxito", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO("Error desconocido al guardar empleado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseDTO update(Integer id, Usuario usuarioObj, String usuario) {
        try {
            Usuario usuarioUp = usuarioRepository.findById(id).orElse(null);

            usuarioUp.setNombreUsu(usuarioObj.getNombreUsu());
            usuarioUp.setApellidoUsu(usuarioObj.getApellidoUsu());
            usuarioUp.setCorreoUsu(usuarioObj.getCorreoUsu());
            usuarioUp.setEstado(usuarioObj.getEstado());
            usuarioRepository.save(usuarioUp);

            return new ResponseDTO("Empleado modificado con éxito", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO("Error desconocido al guardar empleado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseDTO activarInactivar(Integer id, String usuario) {
        return null;
    }

    @Override
    public ResponseDTO getAll(int page, int pageSize, String sortField, boolean sortAsc, Integer idUsuario, String nombreUsu, String apellidoUsu, String correoUsu, String estado) {
        try {

            nombreUsu = nombreUsu.equals("") ? null : nombreUsu;
            apellidoUsu = apellidoUsu.equals("") ? null : apellidoUsu;
            correoUsu = correoUsu.equals("") ? null : correoUsu;
            estado = estado.equals("") ? null : estado.toUpperCase();

            Sort sort = Sort.by(sortAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
            Pageable pageable = PageRequest.of(page, pageSize, sort);

            Page<Usuario> empleadoList = usuarioRepository.findAll(idUsuario, nombreUsu, apellidoUsu, correoUsu, estado, pageable);
            TableDTO<Usuario> tableDTO = new TableDTO<>(mapperViewEntityList(empleadoList.getContent(), Usuario.class,
                    View.UsuarioShort.class), (int) empleadoList.getTotalElements());

            return new ResponseDTO(new Date(), HttpStatus.OK, "Lista de empleado recuperada con éxito", tableDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO("Ocurrió un error inesperado al recuperar la lista de empleado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
