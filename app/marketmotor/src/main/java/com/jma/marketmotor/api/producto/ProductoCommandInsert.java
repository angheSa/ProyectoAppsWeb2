package com.jma.marketmotor.api.producto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoCommandInsert {

    private String tipo;

    private String marca;

    private String serial;

    private String descripcion;

}
