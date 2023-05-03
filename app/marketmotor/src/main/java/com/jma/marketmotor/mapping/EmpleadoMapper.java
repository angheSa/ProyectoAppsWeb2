package com.jma.marketmotor.mapping;


import com.jma.marketmotor.dto.EmpleadoDto;
import com.jma.marketmotor.entity.EmpleadoEntity;
import com.jma.marketmotor.utils.EstadoD;

public class EmpleadoMapper {


    public static EmpleadoEntity mapToEntity(EmpleadoDto empleadoDto){

        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        empleadoEntity.setNombre(empleadoDto.getNombre());
        empleadoEntity.setCorreo(empleadoDto.getCorreo());
        empleadoEntity.setTelefono(empleadoDto.getTelefono());
        empleadoEntity.setApellidoMat(empleadoDto.getApellidoMat());
        empleadoEntity.setApellidoPat(empleadoDto.getApellidoPat());
        empleadoEntity.declararDisponibilidad(EstadoD.ACTIVO);
        return empleadoEntity;
    }

    public static EmpleadoDto mapToDto(EmpleadoEntity empleadoEntity){

        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setNombre(empleadoEntity.getNombre());
        empleadoDto.setCorreo(empleadoEntity.getCorreo());
        empleadoDto.setTelefono(empleadoEntity.getTelefono());
        empleadoDto.setApellidoMat(empleadoEntity.getApellidoMat());
        empleadoDto.setApellidoPat(empleadoEntity.getApellidoPat());
        empleadoDto.setIdUsuario(empleadoEntity.getUsuario().getId());
        return empleadoDto;
    }






}
