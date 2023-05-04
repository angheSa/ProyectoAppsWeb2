package com.jma.marketmotor.mapping;

import com.jma.marketmotor.api.permiso.PermisoCommandInsert;
import com.jma.marketmotor.api.permiso.PermisoCommandUpdate;
import com.jma.marketmotor.api.rol.RolCommandInsert;
import com.jma.marketmotor.api.rol.RolCommandUpdate;
import com.jma.marketmotor.dto.PermisoDto;
import com.jma.marketmotor.dto.RolDto;
import com.jma.marketmotor.entity.PermisoEntity;
import com.jma.marketmotor.entity.RolEntity;
import com.jma.marketmotor.utils.EstadoD;

public class PermisoMapper {

    public static PermisoDto mapToDto(PermisoEntity permisoEntity){
        PermisoDto permisoDto = new PermisoDto();
        permisoDto.setId(permisoEntity.getId());
        permisoDto.setTipo(permisoEntity.getTipo());
        permisoDto.setEstado(permisoEntity.isEstado());
        return permisoDto;
    }

    public static PermisoEntity mapToEntity(PermisoDto permisoDto){
        PermisoEntity permisoEntity = new PermisoEntity();
        permisoEntity.setTipo(permisoDto.getTipo());
        permisoEntity.setEstado(permisoDto.isEstado());
        return permisoEntity;
    }


    public static PermisoDto mapFromCommandInsertToDto(PermisoCommandInsert permisoCommandInsert){
        PermisoDto permisoDto = new PermisoDto();
        permisoDto.setTipo(permisoCommandInsert.getTipo());
        permisoDto.declararDisponibilidad(EstadoD.ACTIVO);
        return permisoDto;
    }

    public static PermisoDto mapFromCommandUpdateToDto(PermisoCommandUpdate permisoCommandUpdate){
        PermisoDto permisoDto = new PermisoDto();
        permisoDto.setTipo(permisoCommandUpdate.getTipo());
        permisoDto.setEstado(permisoCommandUpdate.isEstado());
        return permisoDto;
    }

}
