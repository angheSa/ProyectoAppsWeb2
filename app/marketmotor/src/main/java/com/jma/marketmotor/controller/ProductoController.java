package com.jma.marketmotor.controller;


import com.jma.marketmotor.api.ProductoResponse;
import com.jma.marketmotor.api.producto.ProductoCommandInsert;
import com.jma.marketmotor.api.producto.ProductoCommandUpdate;
import com.jma.marketmotor.dto.PermisoDto;
import com.jma.marketmotor.dto.ProductoDto;
import com.jma.marketmotor.mapping.ProductoMapper;
import com.jma.marketmotor.service.ProductoService;
import com.jma.marketmotor.utils.ConstantsService;
import com.jma.marketmotor.utils.EstadoD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService<ProductoDto> productoService;

    @Autowired
    public ProductoController(ProductoService<ProductoDto> productoService){
        this.productoService = productoService;
    }


    @PostMapping
    public ResponseEntity<ProductoDto> guardar(@RequestBody ProductoCommandInsert productoCommandInsert){

        ProductoDto productoDtoObt = productoService.guardar(ProductoMapper.mapFromCommandInsertToDto(productoCommandInsert));

        return ResponseEntity.ok(productoDtoObt);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerTodos(){

        List<ProductoDto> productoDtos = productoService.obtenerTodos();

        return ResponseEntity.ok(productoDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerPorId(@PathVariable Long id){

        ProductoDto productoDtoObt = productoService.obtenerPorId(id);

        return ResponseEntity.ok(productoDtoObt);
    }

    @PutMapping
    public ResponseEntity<ProductoDto> actualizar(@RequestBody ProductoCommandUpdate productoCommandUpdate) {

        ProductoDto productoDtoObt = productoService.actualizar(ProductoMapper.mapFromCommandUpdateToDto(productoCommandUpdate));

        return ResponseEntity.ok(productoDtoObt);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable Long id){
        try {
            ProductoDto producto = productoService.obtenerPorId(id);
            if (producto == null)
                return ResponseEntity.notFound().build();

            producto.declararDisponibilidad(EstadoD.INACTIVO);
            productoService.guardar(producto);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar (@PathVariable Long id) {
        String respuesta = productoService.eliminar(id);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/pagination")
    public ResponseEntity<ProductoResponse> obtenerTodosPaginados(
            @RequestParam(value = "pageNo", defaultValue = ConstantsService.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ConstantsService.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ConstantsService.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ConstantsService.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(productoService.obtenerTodosPaginados(pageNo, pageSize, sortBy, sortDir));
    }

}
