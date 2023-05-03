package com.jma.marketmotor.service.impl;

import com.jma.marketmotor.dto.RolDto;
import com.jma.marketmotor.entity.RolEntity;
import com.jma.marketmotor.mapping.RolMapper;
import com.jma.marketmotor.repository.RolRepository;
import com.jma.marketmotor.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService<RolDto> {


    private final RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository){
        this.rolRepository = rolRepository;
    }


    @Override
    public RolDto guardar(RolDto object) {

        RolEntity rolEntity = RolMapper.mapToEntity(object);

        return RolMapper.mapToDto(rolRepository.save(rolEntity));
    }

    @Override
    public RolDto actualizar(RolDto object) {

        RolEntity rolEntity = RolMapper.mapToEntity(object);
        rolEntity.setId(object.getId());

        return RolMapper.mapToDto(rolRepository.save(rolEntity));
    }

    @Override
    public List<RolDto> obtenerTodos() {
        return rolRepository.findAll().stream().map(RolMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public String eliminar(Object id) {
        try{
            rolRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public RolDto obtenerPorId(Object id) {
        return rolRepository.findById((Long)id).map(RolMapper::mapToDto).orElse(null);
    }
}
