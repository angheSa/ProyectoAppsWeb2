package com.jma.marketmotor.service.impl;

import com.jma.marketmotor.dto.UsuarioDto;
import com.jma.marketmotor.entity.RolEntity;
import com.jma.marketmotor.entity.UsuarioEntity;
import com.jma.marketmotor.mapping.RolMapper;
import com.jma.marketmotor.mapping.UsuarioMapper;
import com.jma.marketmotor.repository.RolRepository;
import com.jma.marketmotor.repository.UsuarioRepository;
import com.jma.marketmotor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService<UsuarioDto> {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }


    @Override
    public List<UsuarioDto> guardarTodos(List<UsuarioDto> list) {
        List<RolEntity> rolesEntities = list.stream().map( e -> rolRepository.findById(e.getRol().getId()).orElse(null)).toList();
        List<UsuarioEntity> usuarioEntities = list.stream().map(UsuarioMapper::mapToEntity).toList();

        for (int i = 0; i < usuarioEntities.size(); i++) {
            usuarioEntities.get(i).setRol(rolesEntities.get(i));
        }
        List<UsuarioEntity> usuariossGuardados = usuarioRepository.saveAll(usuarioEntities);
        return usuariosMapeados(usuariossGuardados);
    }

    @Override
    public UsuarioDto guardar(UsuarioDto object) {

        RolEntity rolEntity = rolRepository.findById(object.getRol().getId()).orElse(null);

        UsuarioEntity usuarioEntityObt = UsuarioMapper.mapToEntity(object);
        if(object.getId()!= null){
            usuarioEntityObt.setId(object.getId());
        }

        usuarioEntityObt.setRol(rolEntity);

        UsuarioEntity usuarioEntitySaved = usuarioRepository.save(usuarioEntityObt);

        return UsuarioMapper.mapToDto(usuarioEntitySaved);
    }

    @Override
    public List<UsuarioDto> obtenerTodos() {

        List<UsuarioEntity> usuarioEntities = usuarioRepository.findAll();

        return usuariosMapeados(usuarioEntities);
    }

    @Override
    public String eliminar(Object id) {
        try{
            usuarioRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public UsuarioDto obtenerPorId(Object id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById((Long)id).orElse(new UsuarioEntity());
        UsuarioDto usuarioDto = UsuarioMapper.mapToDto(usuarioEntity);
        usuarioDto.setRol(RolMapper.mapToDto(usuarioEntity.getRol()));

        return usuarioDto;
    }

    @Override
    public UsuarioDto actualizar(UsuarioDto object) {
        Optional<UsuarioEntity> opcional = usuarioRepository.findById(object.getId());
        UsuarioEntity usuario;

        if(opcional.isPresent()){
            usuario = opcional.get();
            usuario.setAlias(object.getAlias());
            usuario.setContrasena(object.getContrasena());
            return UsuarioMapper.mapToDto(usuarioRepository.save(usuario));
        }

        return null;
    }

    private List<UsuarioDto> usuariosMapeados(List<UsuarioEntity> usuarios) {
        List<UsuarioDto> content = usuarios.stream().map(UsuarioMapper::mapToDto).toList();

        for (int i = 0; i < content.size(); i++) {
            content.get(i).setRol(RolMapper.mapToDto(usuarios.get(i).getRol()));
        }
        return content;
    }
}
