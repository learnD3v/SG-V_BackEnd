package com.sistema.ventas.service;

import com.sistema.ventas.dto.ResponseDTO;
import com.sistema.ventas.model.HistoricoPdto;
import com.sistema.ventas.model.Producto;
import com.sistema.ventas.repository.HistoricoPdtoRepository;
import com.sistema.ventas.repository.ProductoRepository;
import com.sistema.ventas.specification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl extends GenericSpecification<Producto> implements ProductoService{
    private final ProductoRepository productoRepository;
    private final HistoricoPdtoRepository historicoPdtoRepository;
    @Override
    @Transactional
    public ResponseDTO save(Producto productoObj) {
        try {
            Producto producto = new Producto();
            producto.setDetallePdto(productoObj.getDetallePdto());
            producto.setCantidad(productoObj.getCantidad());
            producto.setPrecio(productoObj.getPrecio());
            producto.setEstado("A");
            producto.setUsuAlta(productoObj.getUsuAlta());
            producto.setFechaAlta(new Date());
            productoRepository.save(producto);

            producto = productoRepository.findById(producto.getIdProducto()).orElse(null);

            if (producto != null) {
                HistoricoPdto historicoPdto = new HistoricoPdto();
                historicoPdto.setUsuAlta(productoObj.getUsuAlta());
                historicoPdto.setFechaAlta(new Date());
                historicoPdto.setIdPdto(producto);
                historicoPdto.setDetallePdto(producto.getDetallePdto());
                historicoPdto.setCantidad(producto.getCantidad());
                historicoPdto.setPrecio(producto.getPrecio());
                historicoPdto.setEstado(producto.getEstado());
                historicoPdtoRepository.save(historicoPdto);
            }
            return new ResponseDTO("Producto guardado con éxito", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO("Error desconocido al guardar producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseDTO update(Integer id, Producto productoObj, String usuario) {

        try {
            Producto producto = productoRepository.findById(id).orElse(null);

            producto.setDetallePdto(productoObj.getDetallePdto());
            producto.setCantidad(productoObj.getCantidad());
            producto.setPrecio(productoObj.getPrecio());
            producto.setEstado(productoObj.getEstado());
            productoRepository.save(producto);

            producto = productoRepository.findById(producto.getIdProducto()).orElse(null);

            if (producto != null) {
            HistoricoPdto historicoPdto = new HistoricoPdto();
            historicoPdto.setUsuAlta(usuario);
            historicoPdto.setFechaAlta(new Date());
            historicoPdto.setIdPdto(producto);
            historicoPdto.setDetallePdto(producto.getDetallePdto());
            historicoPdto.setCantidad(producto.getCantidad());
            historicoPdto.setPrecio(producto.getPrecio());
            historicoPdto.setEstado(producto.getEstado());
            historicoPdtoRepository.save(historicoPdto);

            }
            return new ResponseDTO("Producto modificado con éxito", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO("Error desconocido al guardar producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseDTO activarInactivar(Integer id, String usuario) {
        AtomicReference<String> mensaje = new AtomicReference<>("");
        Producto producto = productoRepository.findById(id).orElse(null);

        if (producto == null) {
            return new ResponseDTO("Producto inexistente", HttpStatus.BAD_REQUEST);
        }

        String estado = producto.getEstado();
        if (estado != null) {
            if (estado.equals("I")) {
                mensaje.set("El producto fue dado de alta con éxito");
                producto.setEstado("A");
            } else {
                mensaje.set("El producto fue dado de baja con éxito");
                producto.setEstado("I");
            }

            if (producto != null) {
                HistoricoPdto historicoPdto = new HistoricoPdto();
                historicoPdto.setUsuAlta(usuario);
                historicoPdto.setFechaAlta(new Date());
                historicoPdto.setIdPdto(producto);
                historicoPdto.setDetallePdto(producto.getDetallePdto());
                historicoPdto.setCantidad(producto.getCantidad());
                historicoPdto.setPrecio(producto.getPrecio());
                historicoPdto.setEstado(producto.getEstado());
                historicoPdtoRepository.save(historicoPdto);
            }
            return new ResponseDTO(mensaje.get(), HttpStatus.OK);
        }
        return new ResponseDTO("Estado de producto nulo", HttpStatus.BAD_REQUEST);
    }
}
