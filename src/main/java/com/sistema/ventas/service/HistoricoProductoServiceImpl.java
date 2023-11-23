package com.sistema.ventas.service;

import com.sistema.ventas.dto.ResponseDTO;
import com.sistema.ventas.dto.TableDTO;
import com.sistema.ventas.model.HistoricoPdto;
import com.sistema.ventas.model.Usuario;
import com.sistema.ventas.repository.HistoricoPdtoRepository;
import com.sistema.ventas.specification.GenericSpecification;
import com.sistema.ventas.util.View;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class HistoricoProductoServiceImpl extends GenericSpecification<HistoricoPdto> implements HistoricoProductoService {
    private final HistoricoPdtoRepository historicoPdtoRepository;

    @Override
    public ResponseDTO getAll(int page, int pageSize, String sortField, boolean sortAsc,
                              Integer idHistPdto, String detallePdto, Integer cantidad, Integer precio, String estado) {

        try {

            detallePdto = detallePdto.equals("") ? null : detallePdto;
            estado = estado.equals("") ? null : estado.toUpperCase();

            Sort sort = Sort.by(sortAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
            Pageable pageable = PageRequest.of(page, pageSize, sort);

            Page<HistoricoPdto> historicoPdtos = historicoPdtoRepository.findAll(idHistPdto, detallePdto, cantidad, precio, estado, pageable);
            TableDTO<HistoricoPdto> tableDTO = new TableDTO<>(mapperViewEntityList(historicoPdtos.getContent(), HistoricoPdto.class,
                    View.HistoricoProductoShort.class), (int) historicoPdtos.getTotalElements());

            return new ResponseDTO(new Date(), HttpStatus.OK, "Lista de empleado recuperada con éxito", tableDTO);


        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO("Ocurrió un error inesperado al recuperar la lista de productos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
