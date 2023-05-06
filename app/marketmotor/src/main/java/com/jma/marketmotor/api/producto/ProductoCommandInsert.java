package com.jma.marketmotor.api.producto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoCommandInsert {

    private Long id;

    private String tipo;

    private String marca;

    private String serial;

    private String descripcion;

}
