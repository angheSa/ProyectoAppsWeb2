package com.jma.marketmotor.dto;

import com.jma.marketmotor.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolDto {

    private Long id;
    private String nombre;
    private Boolean estado;

    public void declararDisponibilidad(EstadoD estadoD){
        switch (estadoD){
            case ACTIVO:
                setEstado(true);break;
            default:
                setEstado(false);break;
        }
    }

}
