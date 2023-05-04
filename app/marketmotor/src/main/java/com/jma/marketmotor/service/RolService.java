package com.jma.marketmotor.service;

import com.jma.marketmotor.dto.PermisoDto;
import com.jma.marketmotor.dto.RolDto;
import com.jma.marketmotor.service.common.ICrudCommon;

import java.util.List;

public interface RolService<T> extends ICrudCommon<T> {
    PermisoDto definirPermiso(Long id, PermisoDto permiso);
    RolDto removerPermiso(Long idRol,Long idPermiso);

    List<T> buscarRolesPorPermisosId(Long permisoId);
}
