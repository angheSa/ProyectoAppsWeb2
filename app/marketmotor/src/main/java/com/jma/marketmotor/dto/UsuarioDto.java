package com.jma.marketmotor.dto;

import com.jma.marketmotor.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioDto implements Serializable {

    private Long id;

    private String alias;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    private boolean estado;

    public void declararDisponibilidad(EstadoD estadoD){
        switch (estadoD){
            case ACTIVO:
                setEstado(true);break;
            default:
                setEstado(false);break;
        }
    }
}
