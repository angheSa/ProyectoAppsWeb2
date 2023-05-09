package com.jma.marketmotor.service;

import com.jma.marketmotor.dto.PermisoDto;
import com.jma.marketmotor.dto.RolDto;
import com.jma.marketmotor.service.common.ICrudCommon;

import java.util.List;

public interface RolService<T> extends ICrudCommon<T> {
    PermisoDto definirPermiso(Long idRol, Long idPermiso);
    RolDto removerPermiso(Long idRol, Long idPermiso);

    List<T> buscarRolesPorPermisosId(Long permisoId);


    List<T> guardarTodos(List<T> list);
}
