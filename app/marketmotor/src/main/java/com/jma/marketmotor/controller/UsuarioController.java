package com.jma.marketmotor.controller;

import com.jma.marketmotor.dto.UsuarioDto;
import com.jma.marketmotor.entity.UsuarioEntity;
import com.jma.marketmotor.mapping.UsuarioMapper;
import com.jma.marketmotor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService<UsuarioEntity> usuarioService;

    @Autowired
    public UsuarioController(UsuarioService<UsuarioEntity> usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> obtenerTodos(){
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @PostMapping()
    public ResponseEntity<UsuarioEntity> guardar(@RequestBody UsuarioDto usuarioDto){

        UsuarioEntity usuarioGuardado = usuarioService.guardar(UsuarioMapper.mapToEntity(usuarioDto));
        return ResponseEntity.ok(usuarioGuardado);

    }

    @PostMapping("/guardarTodos")
    public ResponseEntity<List<UsuarioEntity>> guardarTodos(@RequestBody List<UsuarioDto> usuariosDto){

        List<UsuarioEntity> usuariosMappeados = usuariosDto.stream().map(UsuarioMapper::mapToEntity).toList();
        List<UsuarioEntity> usuariosGuardados = usuarioService.guardarTodos(usuariosMappeados);

        return ResponseEntity.ok(usuariosGuardados);

    }



}
