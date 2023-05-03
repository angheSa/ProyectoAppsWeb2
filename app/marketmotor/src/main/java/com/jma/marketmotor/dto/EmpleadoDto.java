package com.jma.marketmotor.dto;

import com.jma.marketmotor.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmpleadoDto implements Serializable {

    private Long id;

    private String nombre;

    private String apellidoPat;

    private String apellidoMat;

    private String telefono;

    private String correo;

    private boolean estado;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    private UsuarioDto usuarioDto;

    public void declararDisponibilidad(EstadoD estadoD){
        switch (estadoD){
            case ACTIVO:
                setEstado(true);break;
            default:
                setEstado(false);break;
        }
    }
}
