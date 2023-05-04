package com.jma.marketmotor.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_permiso")
@Data
@NoArgsConstructor
public class PermisoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Long id;

    @Column(name = "tipo_permiso")
    private String tipo;

    @Column(name = "estaod")
    private boolean estado;

    @ManyToMany(mappedBy = "permisos")
    private Set<RolEntity> roles = new HashSet<>();


}
