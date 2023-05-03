package com.jma.marketmotor.controller;

import com.jma.marketmotor.api.rol.RolCommandInsert;
import com.jma.marketmotor.api.rol.RolCommandUpdate;
import com.jma.marketmotor.dto.RolDto;
import com.jma.marketmotor.dto.UsuarioDto;
import com.jma.marketmotor.mapping.RolMapper;
import com.jma.marketmotor.service.RolService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/roles")
public class RolController {

    RolService<RolDto> rolService;

    @Autowired
    public RolController(RolService<RolDto> rolService){
        this.rolService = rolService;
    }
    @PostMapping
    public ResponseEntity<RolDto> guardar(@RequestBody RolCommandInsert rolCommandInsert){

        RolDto rolDtoObt = rolService.guardar(RolMapper.mapFromCommandInsertToDto(rolCommandInsert));
        return ResponseEntity.ok(rolDtoObt);

    }

    @GetMapping
    public ResponseEntity<RolDto> obtenerPorId(@PathVariable Long id){

        RolDto rolDtoObt = rolService.obtenerPorId(id);

        return ResponseEntity.ok(rolDtoObt);
    }

    @PutMapping
    public ResponseEntity<RolDto> actualizar(@RequestBody RolCommandUpdate rolCommandUpdate) {

        RolDto rolDtoObt = rolService.actualizar(RolMapper.mapFromCommandUpdateToDto(rolCommandUpdate));

        return ResponseEntity.ok(rolDtoObt);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        String respuesta = rolService.eliminar(id);
        return ResponseEntity.ok(respuesta);
    }


}
