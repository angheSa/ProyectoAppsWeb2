package com.jma.marketmotor.controller;

import com.jma.marketmotor.api.usuario.UsuarioCommandInsert;
import com.jma.marketmotor.api.usuario.UsuarioCommandUpdate;
import com.jma.marketmotor.dto.UsuarioDto;
import com.jma.marketmotor.mapping.UsuarioMapper;
import com.jma.marketmotor.service.UsuarioService;
import com.jma.marketmotor.utils.EstadoD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService<UsuarioDto> usuarioService;

    @Autowired
    public UsuarioController(UsuarioService<UsuarioDto> usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> obtenerTodos(){
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> guardar(@RequestBody UsuarioCommandInsert usuarioCommandInsert){

        UsuarioDto usuarioGuardado = usuarioService.guardar(UsuarioMapper.mapFromCommandInsertToDto(usuarioCommandInsert));
        return ResponseEntity.ok(usuarioGuardado);

    }

    @PostMapping("/guardarTodos")
    public ResponseEntity<List<UsuarioDto>> guardarTodos(@RequestBody List<UsuarioCommandInsert> usuariosCommand){

        List<UsuarioDto> usuariosMappeados = usuariosCommand.stream().map(UsuarioMapper::mapFromCommandInsertToDto).toList();
        List<UsuarioDto> usuariosGuardados = usuarioService.guardarTodos(usuariosMappeados);

        return ResponseEntity.ok(usuariosGuardados);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> obtenerPorId(@PathVariable Long id){

        UsuarioDto usuarioDto = usuarioService.obtenerPorId(id);

        return ResponseEntity.ok(usuarioDto);
    }


    @PutMapping
    public ResponseEntity<UsuarioDto> actualizar(@RequestBody UsuarioCommandUpdate usuarioCommandUpdate){

        UsuarioDto usuarioDto = usuarioService.actualizar(UsuarioMapper.mapFromCommandUpdateToDto(usuarioCommandUpdate));

        return ResponseEntity.ok(usuarioDto);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable Long id){

        try{
            UsuarioDto usuario = usuarioService.obtenerPorId(id);
            if(usuario == null)
                return ResponseEntity.notFound().build();

            usuario.declararDisponibilidad(EstadoD.INACTIVO);
            usuarioService.actualizar(usuario);
            return ResponseEntity.ok("Se desactivó correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){

        String respuesta = usuarioService.eliminar(id);

        return ResponseEntity.ok(respuesta);
    }

}
