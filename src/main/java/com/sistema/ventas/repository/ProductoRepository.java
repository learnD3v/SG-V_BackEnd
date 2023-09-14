package com.sistema.ventas.repository;

import com.sistema.ventas.model.Producto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends PagingAndSortingRepository<Producto, Integer> {
}
