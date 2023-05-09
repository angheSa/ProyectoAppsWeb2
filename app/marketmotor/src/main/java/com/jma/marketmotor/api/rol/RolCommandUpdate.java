package com.jma.marketmotor.api.rol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolCommandUpdate {
    private Long id;
    private String nombre;
    private boolean estado;
}
