package com.jma.marketmotor.api.ordencompra;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleOrdenCompraCommandUpdate {

    private Long id;

    private int cantidad;

    private double precioUnitario;


}
