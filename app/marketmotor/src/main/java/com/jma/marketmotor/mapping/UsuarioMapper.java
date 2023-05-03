package com.jma.marketmotor.mapping;


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

        usuarioDto.setAlias(usuarioEntity.getAlias());
        return usuarioDto;
    }


}
