package com.jma.marketmotor.entity;

import com.jma.marketmotor.utils.EstadoD;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_rol")
@Data
@NoArgsConstructor
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @Column(name = "nombre_rol")
    private String nombre;

    @Column(name = "estado")
    private boolean estado;


    @ManyToMany
    @JoinTable(
            name = "tb_permiso_rol",
            joinColumns = {@JoinColumn(name = "id_rol")},
            inverseJoinColumns = {@JoinColumn(name = "id_permiso")}

    )
    private Set<PermisoEntity> permisos = new HashSet<>();


    public void agregarPermiso(PermisoEntity permisoEntity) {
        this.permisos.add(permisoEntity);
        permisoEntity.getRoles().add(this);
    }

    public void removerPermiso(Long permisoId) {
        PermisoEntity permiso = this.permisos.stream().filter(t -> Objects.equals(t.getId(), permisoId)).findFirst().orElse(null);
        if (permiso != null) {
            this.permisos.remove(permiso);
            permiso.getRoles().remove(this);
        }
    }


}
