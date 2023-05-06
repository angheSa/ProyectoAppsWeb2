package com.jma.marketmotor.dto;

import com.jma.marketmotor.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RolDto {

    private Long id;
    private String nombre;
    private boolean estado;
    private Set<PermisoDto> permisos = new HashSet<>();


    public void declararDisponibilidad(EstadoD estadoD){
        switch (estadoD){
            case ACTIVO:
                setEstado(true);break;
            default:
                setEstado(false);break;
        }
    }

}
