package com.jma.marketmotor.service.impl;

import com.jma.marketmotor.dto.PermisoDto;
import com.jma.marketmotor.dto.RolDto;
import com.jma.marketmotor.entity.PermisoEntity;
import com.jma.marketmotor.entity.RolEntity;
import com.jma.marketmotor.mapping.PermisoMapper;
import com.jma.marketmotor.mapping.RolMapper;
import com.jma.marketmotor.repository.PermisoRepository;
import com.jma.marketmotor.repository.RolRepository;
import com.jma.marketmotor.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService<RolDto> {


    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository,PermisoRepository permisoRepository){
        this.rolRepository = rolRepository;
        this.permisoRepository = permisoRepository;
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

    @Override
    public PermisoDto definirPermiso(Long id, PermisoDto permiso){

        return rolRepository.findById(id).map(rol -> {
            long permisoId = permiso.getId();

            // tag is existed
            if (permisoId != 0L) {
                PermisoDto _permiso = PermisoMapper.mapToDto(permisoRepository.findById(permisoId)
                        .orElseThrow(() -> new RuntimeException("Not found Tag with id = " + permisoId)));
                rol.agregarPermiso(PermisoMapper.mapToEntity(_permiso));
                rolRepository.save(rol);
                return _permiso;
            }

            // add and create new Tag
            rol.agregarPermiso(PermisoMapper.mapToEntity(permiso));
            PermisoEntity permisoEntity = permisoRepository.save(PermisoMapper.mapToEntity(permiso));

            return PermisoMapper.mapToDto(permisoEntity);
        }).orElseThrow(() -> new RuntimeException("Not found Tutorial with id = " + id));
    }


    @Override
    public RolDto removerPermiso(Long idRol,Long idPermiso){
        RolEntity rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Not found Tutorial with id = " + idRol));

        rol.removerPermiso(idPermiso);
        RolEntity rolsaved = rolRepository.save(rol);

        return RolMapper.mapToDto(rolsaved);
    }

    @Override
    public List<RolDto> buscarRolesPorPermisosId(Long permisoId) {

        List<RolEntity> rolEntities = rolRepository.findRolEntitiesByPermisosId(permisoId);

        return rolEntities.stream().map(RolMapper::mapToDto).collect(Collectors.toList());
    }

}
