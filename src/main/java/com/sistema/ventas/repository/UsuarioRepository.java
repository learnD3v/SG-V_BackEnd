package com.sistema.ventas.repository;

import com.sistema.ventas.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepository  extends PagingAndSortingRepository<Usuario, Integer> {
    @Query(value = "select u from Usuario as u " +
            "where (u.idUsuario = ?1 or ?1 is null)" +
            "and (upper(u.nombre) = upper(?2) or ?2 is null) " +
            "and (upper(u.apellido) = upper(?3) or ?3 is null ) " +
            "and (upper(u.correo) = upper(?4) or ?4 is null)"+
            "and (u.estado = ?5 or ?5 is null)",
            countQuery = "select count(u) from Usuario as u " +
                    "where (upper(u.nombre) = upper(?1) or ?1 is null) " +
                    "and (u.idUsuario = ?2 or ?2 is null)"+
                    "and (u.apellido = ?3 or ?3 is null ) " +
                    "and (u.correo = ?4 or ?4 is null)"+
                    "and (u.estado = ?5 or ?5 is null)")
    Page<Usuario> findAll(Integer idUsuario, String nombre, String apellido, String correo, String estado,
                           Pageable pageable);
}
