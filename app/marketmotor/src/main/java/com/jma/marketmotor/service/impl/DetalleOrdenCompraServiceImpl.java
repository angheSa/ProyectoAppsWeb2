package com.jma.marketmotor.service.impl;

import com.jma.marketmotor.api.DetalleOrdenCompraResponse;
import com.jma.marketmotor.dto.DetalleOrdenCompraDto;
import com.jma.marketmotor.entity.*;
import com.jma.marketmotor.mapping.*;
import com.jma.marketmotor.repository.DetalleOrdenCompraRepository;
import com.jma.marketmotor.repository.OrdenCompraRepository;
import com.jma.marketmotor.repository.ProductoRepository;
import com.jma.marketmotor.service.DetalleOrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenCompraServiceImpl implements DetalleOrdenCompraService<DetalleOrdenCompraDto> {

    private final DetalleOrdenCompraRepository detalleOrdenCompraRepository;
    private final OrdenCompraRepository ordenCompraRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public DetalleOrdenCompraServiceImpl(DetalleOrdenCompraRepository detalleOrdenCompraRepository,
                                         OrdenCompraRepository ordenCompraRepository,
                                         ProductoRepository productoRepository){
        this.detalleOrdenCompraRepository = detalleOrdenCompraRepository;
        this.ordenCompraRepository = ordenCompraRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public DetalleOrdenCompraDto guardar(DetalleOrdenCompraDto object) {

        OrdenCompraEntity ordenCompraEntity = ordenCompraRepository.findById(object.getOrdenCompra().getId()).orElse(null);
        ProductoEntity productoEntity = productoRepository.findById(object.getProducto().getId()).orElse(null);

        DetalleOrdenCompraEntity detalleOrdenCompraEntity = DetalleOrdenCompraMapper.mapToEntity(object);

        if(object.getId()!= null){
            detalleOrdenCompraEntity.setId(object.getId());
        }

        detalleOrdenCompraEntity.setOrdenCompra(ordenCompraEntity);
        detalleOrdenCompraEntity.setProducto(productoEntity);

        DetalleOrdenCompraEntity detalleEntityGuardado = detalleOrdenCompraRepository.save(detalleOrdenCompraEntity);
        DetalleOrdenCompraDto detalleDtoGuardado = DetalleOrdenCompraMapper.mapToDto(detalleEntityGuardado);

        detalleDtoGuardado.setProducto(ProductoMapper.mapToDto(detalleOrdenCompraEntity.getProducto()));
        detalleDtoGuardado.setOrdenCompra(OrdenCompraMapper.mapToDto(detalleOrdenCompraEntity.getOrdenCompra()));

        return detalleDtoGuardado;
    }

    @Override
    public List<DetalleOrdenCompraDto> obtenerTodos() {

        List<DetalleOrdenCompraEntity> detallesOrdenEntity = detalleOrdenCompraRepository.findAll();
        List<DetalleOrdenCompraDto> detallesDtoMapped = detallesOrdenEntity.stream().map(DetalleOrdenCompraMapper::mapToDto).toList();

        for (int i = 0; i < detallesDtoMapped.size(); i++) {
            detallesDtoMapped.get(i).setOrdenCompra(OrdenCompraMapper.mapToDto(detallesOrdenEntity.get(i).getOrdenCompra()));
            detallesDtoMapped.get(i).setProducto(ProductoMapper.mapToDto(detallesOrdenEntity.get(i).getProducto()));
        }

        return detallesDtoMapped;
    }

    @Override
    public String eliminar(Object id) {
        try{
            detalleOrdenCompraRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public DetalleOrdenCompraDto obtenerPorId(Object id) {
        DetalleOrdenCompraEntity detalleOrdenCompraEntity = detalleOrdenCompraRepository.findById((Long)id).orElse(new DetalleOrdenCompraEntity());
        DetalleOrdenCompraDto detalleOrdenCompraDto = DetalleOrdenCompraMapper.mapToDto(detalleOrdenCompraEntity);
        detalleOrdenCompraDto.setProducto(ProductoMapper.mapToDto(detalleOrdenCompraEntity.getProducto()));
        detalleOrdenCompraDto.setOrdenCompra(OrdenCompraMapper.mapToDto(detalleOrdenCompraEntity.getOrdenCompra()));

        return detalleOrdenCompraDto;
    }

    @Override
    public DetalleOrdenCompraDto actualizar(DetalleOrdenCompraDto object) {
        Optional<DetalleOrdenCompraEntity> opcional = detalleOrdenCompraRepository.findById(object.getId());
        DetalleOrdenCompraEntity detalleOrdenCompra;

        if(opcional.isPresent()){
            detalleOrdenCompra = opcional.get();
            detalleOrdenCompra.setPrecioUnitario(object.getPrecioUnitario());
            detalleOrdenCompra.setCantidad(object.getCantidad());
            return DetalleOrdenCompraMapper.mapToDto(detalleOrdenCompraRepository.save(detalleOrdenCompra));
        }

        return null;
    }

    @Override
    public DetalleOrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<DetalleOrdenCompraEntity> detalleOrdenPageable = detalleOrdenCompraRepository.findAll(pageable);

        List<DetalleOrdenCompraEntity> detallesOrden = detalleOrdenPageable.getContent();

        List<DetalleOrdenCompraDto> content = detallesMapeados(detallesOrden);

        DetalleOrdenCompraResponse detalleOrdenCompraResponse = new DetalleOrdenCompraResponse();

        detalleOrdenCompraResponse.setContent(content);
        detalleOrdenCompraResponse.setPageNo(detalleOrdenPageable.getNumber());
        detalleOrdenCompraResponse.setPageSize(detalleOrdenPageable.getSize());
        detalleOrdenCompraResponse.setTotalElements(detalleOrdenPageable.getTotalElements());
        detalleOrdenCompraResponse.setLast(detalleOrdenPageable.isLast());
        detalleOrdenCompraResponse.setTotalPages(detalleOrdenPageable.getTotalPages());
        return detalleOrdenCompraResponse;
    }

    private List<DetalleOrdenCompraDto> detallesMapeados(List<DetalleOrdenCompraEntity> detallesOrdenes) {
        List<DetalleOrdenCompraDto> content = detallesOrdenes.stream().map(DetalleOrdenCompraMapper::mapToDto).toList();

        for (int i = 0; i < content.size(); i++) {
            content.get(i).setProducto(ProductoMapper.mapToDto(detallesOrdenes.get(i).getProducto()));
            content.get(i).setOrdenCompra(OrdenCompraMapper.mapToDto(detallesOrdenes.get(i).getOrdenCompra()));
        }
        return content;
    }

}
