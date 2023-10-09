package com.sistema.ventas.repository;

import com.sistema.ventas.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends PagingAndSortingRepository<Producto, Integer> {
    @Query(value = "select p from Producto as p " +
            "where (p.idProducto = ?1 or ?1 is null)" +
            "and (upper(p.detallePdto) = upper(?2) or ?2 is null) " +
            "and (p.cantidad = ?3 or ?3 is null ) " +
            "and (p.precio = ?4 or ?4 is null)"+
            "and (p.estado = ?5 or ?5 is null)",
            countQuery = "select count(p) from Producto as p " +
                    "where (upper(p.detallePdto) = upper(?1) or ?1 is null) " +
                    "and (p.idProducto = ?2 or ?2 is null)"+
                    "and (p.cantidad = ?3 or ?3 is null ) " +
                    "and (p.precio = ?4 or ?4 is null)"+
                    "and (p.estado = ?5 or ?5 is null)")
    Page<Producto> findAll(Integer idProducto, String detallePdto, Integer cantidad, Integer precio, String estado,
                           Pageable pageable);
}
