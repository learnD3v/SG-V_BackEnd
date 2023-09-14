package com.sistema.ventas.repository;

import com.sistema.ventas.model.HistoricoPdto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoPdtoRepository extends PagingAndSortingRepository<HistoricoPdto, Integer> {
}

