package com.jma.marketmotor.api.proveedor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorCommandInsert {

    private Long idProveedor;

    private String razonSocial;

    private String nombreComercial;

    private String numeroRuc;

    private String correo;

    private String direccion;

    private String departamento;

    private String telefonoProveedor;
}
