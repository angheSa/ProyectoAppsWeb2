package com.jma.marketmotor.mapping;


import com.jma.marketmotor.api.usuario.UsuarioCommandInsert;
import com.jma.marketmotor.api.usuario.UsuarioCommandUpdate;
import com.jma.marketmotor.dto.UsuarioDto;
import com.jma.marketmotor.entity.UsuarioEntity;

public class UsuarioMapper {

    public static UsuarioEntity mapToEntity(UsuarioDto usuarioDto){

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setAlias(usuarioDto.getAlias());

        return usuarioEntity;
    }

    public static UsuarioDto mapToDto(UsuarioEntity usuarioEntity){
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setId(usuarioEntity.getId());
        usuarioDto.setAlias(usuarioEntity.getAlias());
        usuarioDto.setCreadoEn(usuarioEntity.getCreadoEn());
        usuarioDto.setActualizadoEn(usuarioEntity.getActualizadoEn());
        return usuarioDto;
    }

    public static UsuarioDto mapFromCommandInsertToDto(UsuarioCommandInsert usuarioCommandInsert){
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setAlias(usuarioCommandInsert.getAlias());
        return usuarioDto;
    }

    public static UsuarioDto mapFromCommandUpdateToDto(UsuarioCommandUpdate usuarioCommandUpdate){
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuarioCommandUpdate.getId());
        usuarioDto.setAlias(usuarioCommandUpdate.getAlias());
        return usuarioDto;
    }


}
