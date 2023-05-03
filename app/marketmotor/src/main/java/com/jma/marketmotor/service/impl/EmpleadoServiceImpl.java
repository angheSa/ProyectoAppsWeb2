package com.jma.marketmotor.service.impl;

import com.jma.marketmotor.api.EmpleadoResponse;
import com.jma.marketmotor.dto.EmpleadoDto;
import com.jma.marketmotor.entity.EmpleadoEntity;
import com.jma.marketmotor.entity.RolEntity;
import com.jma.marketmotor.entity.UsuarioEntity;
import com.jma.marketmotor.mapping.EmpleadoMapper;
import com.jma.marketmotor.mapping.RolMapper;
import com.jma.marketmotor.repository.EmpleadoRepository;
import com.jma.marketmotor.repository.UsuarioRepository;
import com.jma.marketmotor.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService<EmpleadoDto> {

    private final EmpleadoRepository empleadoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,
                               UsuarioRepository usuarioRepository){
        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
    }



    @Override
    public EmpleadoResponse obtenerTodosP(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<EmpleadoEntity> empleadospageable = empleadoRepository.findAll(pageable);

        List<EmpleadoEntity> empleados = empleadospageable.getContent();

        List<EmpleadoDto> content = empleados.stream().map(EmpleadoMapper::mapToDto).toList();

        EmpleadoResponse empleadoResponse = new EmpleadoResponse();

        empleadoResponse.setContent(content);
        empleadoResponse.setPageNo(empleadospageable.getNumber());
        empleadoResponse.setPageSize(empleadospageable.getSize());
        empleadoResponse.setTotalElements(empleadospageable.getTotalElements());
        empleadoResponse.setTotalPages(empleadospageable.getTotalPages());
        empleadoResponse.setLast(empleadospageable.isLast());
        return empleadoResponse;
    }

    @Override
    public List<EmpleadoDto> guardarTodos(List<EmpleadoDto> list) {
        return empleadoRepository.saveAll(list.stream().map(EmpleadoMapper::mapToEntity).collect(Collectors.toList())).stream().map(EmpleadoMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EmpleadoDto guardar(EmpleadoDto object) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(object.getUsuarioDto().getId()).orElse(null);
        //validación para cortar flujo
        EmpleadoEntity empleadoEntityObt = empleadoRepository.save(EmpleadoMapper.mapToEntity(object));
        empleadoEntityObt.setUsuario(usuarioEntity);

        return EmpleadoMapper.mapToDto(empleadoEntityObt);
    }

    @Override
    public List<EmpleadoDto> obtenerTodos() {
        return empleadoRepository.findAll().stream().map(EmpleadoMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public String eliminar(Object id) {
        try{
            empleadoRepository.deleteById((Long)id);
            return "Fue eliminado con éxito";
        }catch (Exception ex){
            return "No se pudo eliminar, se encontró un error";
        }
    }

    @Override
    public EmpleadoDto obtenerPorId(Object id) {
        return empleadoRepository.findById((Long)id).map(EmpleadoMapper::mapToDto).orElse(new EmpleadoDto());
    }

    @Override
    public EmpleadoDto actualizar(EmpleadoDto object) {
        EmpleadoEntity empleadoEntity = EmpleadoMapper.mapToEntity(object);
        empleadoEntity.setId(object.getId());

        return EmpleadoMapper.mapToDto(empleadoRepository.save(empleadoEntity));
    }
}
