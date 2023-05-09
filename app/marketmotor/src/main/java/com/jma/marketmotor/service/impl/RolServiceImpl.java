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

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService<RolDto> {


    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository, PermisoRepository permisoRepository) {
        this.rolRepository = rolRepository;
        this.permisoRepository = permisoRepository;
    }


    @Override
    public RolDto guardar(RolDto object) {

        RolEntity rolEntity = RolMapper.mapToEntity(object);
        if (object.getId()!= null) {
            rolEntity.setId(object.getId());
        }

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

        List<RolEntity> rolEntities = rolRepository.findAll();

        return getRolDtos(rolEntities);
    }

    @Override
    public String eliminar(Object id) {
        try {
            rolRepository.deleteById((Long) id);
            return "Fue eliminado con éxito";
        } catch (Exception ex) {
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public RolDto obtenerPorId(Object id) {
        return rolRepository.findById((Long) id).map(RolMapper::mapToDto).orElse(null);
    }

    @Override
    public PermisoDto definirPermiso(Long idRol, Long idPermiso) {

        return rolRepository.findById(idRol).map(rolInMap -> {

            PermisoDto _permiso = PermisoMapper.mapToDto(permisoRepository.findById(idPermiso)
                    .orElseThrow(() -> new RuntimeException("No fue encontrado el permiso    = " + idPermiso)));
            PermisoEntity permisoEntityMapped = PermisoMapper.mapToEntity(_permiso);
            permisoEntityMapped.setId(_permiso.getId());
            rolInMap.agregarPermiso(permisoEntityMapped);
            rolRepository.save(rolInMap);
            return _permiso;

        }).orElseThrow(() -> new RuntimeException("No fue encontrado el rol = " + idRol));
    }


    @Override
    public RolDto removerPermiso(Long idRol, Long idPermiso) {
        RolEntity rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new RuntimeException("No fue encontrado el rol " + idRol));

        rol.removerPermiso(idPermiso);
        RolEntity rolsaved = rolRepository.save(rol);

        return RolMapper.mapToDto(rolsaved);
    }

    @Override
    public List<RolDto> buscarRolesPorPermisosId(Long permisoId) {

        List<RolEntity> rolEntities = rolRepository.findRolEntitiesByPermisoId(permisoId);

        return getRolDtos(rolEntities);
    }

    @Override
    public List<RolDto> guardarTodos(List<RolDto> list) {
        List<RolEntity> rolesEntities = list.stream().map(RolMapper::mapToEntity).toList();

        List<RolEntity> rolesGuardados = rolRepository.saveAll(rolesEntities);
        return rolesGuardados.stream().map(RolMapper::mapToDto).collect(Collectors.toList());
    }

    private List<RolDto> getRolDtos(List<RolEntity> rolEntities) {
        List<RolDto> rolDtos = rolEntities.stream().map(RolMapper::mapToDto).toList();

        for(int i=0;i<rolDtos.size();i++){
            rolDtos.get(i).setPermisos(rolEntities.get(i).getPermisos().stream().map(PermisoMapper::mapToDto).sorted(Comparator.comparing(PermisoDto::getId)).collect(Collectors.toCollection(LinkedHashSet::new)));
        }

        return rolDtos;
    }


}
