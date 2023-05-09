package com.jma.marketmotor.dto;


import com.jma.marketmotor.utils.EstadoC;
import com.jma.marketmotor.utils.EstadoD;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class OrdenCompraDto implements Serializable {

    private Long id;

    private boolean confirmado;

    private int numero;

    private LocalDateTime fecha;

    private double valorTotal;

    private LocalDateTime actualizadoEn;

    private LocalDateTime creadoEn;

    private boolean estado;

    private EmpleadoDto empleado;

    private ProveedorDto proveedor;
    public void declararDisponibilidad(EstadoD estadoD){
        setEstado(Objects.requireNonNull(estadoD) == EstadoD.ACTIVO);
    }

    public void establecerConfirmacion(EstadoC estadoC){
        setConfirmado(Objects.requireNonNull(estadoC) == EstadoC.SI);
    }

}
