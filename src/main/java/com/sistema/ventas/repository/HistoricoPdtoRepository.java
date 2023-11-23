package com.sistema.ventas.repository;

import com.sistema.ventas.model.HistoricoPdto;
import com.sistema.ventas.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoPdtoRepository extends PagingAndSortingRepository<HistoricoPdto, Integer> {
    @Query(value = "select h from HistoricoPdto as h " +
            "where (h.idHistPdto = ?1 or ?1 is null)" +
            "and (upper(h.detallePdto) = upper(?2) or ?2 is null) " +
            "and (h.cantidad = ?3 or ?3 is null ) " +
            "and (h.precio = ?4 or ?4 is null)"+
            "and (h.estado = ?5 or ?5 is null)",
            countQuery = "select count(h) from HistoricoPdto as h " +
                    "where (upper(h.detallePdto) = upper(?1) or ?1 is null) " +
                    "and (h.idHistPdto = ?2 or ?2 is null)"+
                    "and (h.cantidad = ?3 or ?3 is null ) " +
                    "and (h.precio = ?4 or ?4 is null)"+
                    "and (h.estado = ?5 or ?5 is null)")
    Page<HistoricoPdto> findAll(Integer idProducto, String detallePdto, Integer cantidad, Integer precio, String estado,
                           Pageable pageable);
}

