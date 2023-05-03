package com.jma.marketmotor.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolDto {

    private Long id;
    private String nombre;
    private Boolean estado;

}
