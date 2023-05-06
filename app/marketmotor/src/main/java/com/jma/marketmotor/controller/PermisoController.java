package com.jma.marketmotor.controller;

import com.jma.marketmotor.api.permiso.PermisoCommandInsert;
import com.jma.marketmotor.api.permiso.PermisoCommandUpdate;
import com.jma.marketmotor.dto.PermisoDto;
import com.jma.marketmotor.mapping.PermisoMapper;
import com.jma.marketmotor.service.PermisoService;
import com.jma.marketmotor.utils.EstadoD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permisos")
public class PermisoController {

    private final PermisoService<PermisoDto> permisoService;

    @Autowired
    public PermisoController(PermisoService<PermisoDto> permisoService){
        this.permisoService = permisoService;
    }


    @PostMapping
    public ResponseEntity<PermisoDto> guardar(@RequestBody PermisoCommandInsert permisoCommandInsert){

        PermisoDto permisoDto = permisoService.guardar(PermisoMapper.mapFromCommandInsertToDto(permisoCommandInsert));

        return ResponseEntity.ok(permisoDto);
    }

    @GetMapping
    public ResponseEntity<List<PermisoDto>> obtenerTodos(){

        List<PermisoDto> permisoDtos = permisoService.obtenerTodos();

        return ResponseEntity.ok(permisoDtos);
    }


    @PutMapping
    public ResponseEntity<PermisoDto> actualizar(@RequestBody PermisoCommandUpdate permisoCommandUpdate){

        PermisoDto permisoDto = permisoService.actualizar(PermisoMapper.mapFromCommandUpdateToDto(permisoCommandUpdate));

        return ResponseEntity.ok(permisoDto);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable Long id){

        try{
            PermisoDto permiso = permisoService.obtenerPorId(id);
            if(permiso == null)
                return ResponseEntity.notFound().build();

            permiso.declararDisponibilidad(EstadoD.INACTIVO);
            permisoService.guardar(permiso);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){

        String respuesta = permisoService.eliminar(id);

        return ResponseEntity.ok(respuesta);
    }


}
