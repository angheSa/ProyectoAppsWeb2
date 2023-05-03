package com.jma.marketmotor.mapping;


import com.jma.marketmotor.api.empleado.EmpleadoCommandInsert;
import com.jma.marketmotor.api.empleado.EmpleadoCommandUpdate;
import com.jma.marketmotor.dto.EmpleadoDto;
import com.jma.marketmotor.dto.UsuarioDto;
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
        empleadoEntity.setEstado(empleadoEntity.isEstado());
        return empleadoEntity;
    }

    public static EmpleadoDto mapToDto(EmpleadoEntity empleadoEntity){

        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setNombre(empleadoEntity.getNombre());
        empleadoDto.setCorreo(empleadoEntity.getCorreo());
        empleadoDto.setTelefono(empleadoEntity.getTelefono());
        empleadoDto.setApellidoMat(empleadoEntity.getApellidoMat());
        empleadoDto.setApellidoPat(empleadoEntity.getApellidoPat());
        empleadoDto.declararDisponibilidad(EstadoD.ACTIVO);
        return empleadoDto;
    }

    public static EmpleadoDto mapFromCommandInsertToDto(EmpleadoCommandInsert empleadoCommandInsert){

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(empleadoCommandInsert.getIdUsuario());

        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setNombre(empleadoCommandInsert.getNombre());
        empleadoDto.setApellidoPat(empleadoCommandInsert.getApellidoPat());
        empleadoDto.setApellidoMat(empleadoCommandInsert.getApellidoMat());
        empleadoDto.setCorreo(empleadoCommandInsert.getCorreo());
        empleadoDto.setTelefono(empleadoCommandInsert.getTelefono());
        empleadoDto.setUsuarioDto(usuarioDto);
        return empleadoDto;
    }


    public static EmpleadoDto mapFromCommandUpdateToDto(EmpleadoCommandUpdate empleadoCommandUpdate){


        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setId(empleadoCommandUpdate.getId());
        empleadoDto.setNombre(empleadoCommandUpdate.getNombre());
        empleadoDto.setApellidoPat(empleadoCommandUpdate.getApellidoPat());
        empleadoDto.setApellidoMat(empleadoCommandUpdate.getApellidoMat());
        empleadoDto.setCorreo(empleadoCommandUpdate.getCorreo());
        empleadoDto.setTelefono(empleadoCommandUpdate.getTelefono());
        return empleadoDto;
    }


}
