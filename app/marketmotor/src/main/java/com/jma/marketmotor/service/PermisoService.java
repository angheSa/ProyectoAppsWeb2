package com.jma.marketmotor.service;


import com.jma.marketmotor.service.common.ICrudCommon;

import java.util.List;

public interface PermisoService<T> extends ICrudCommon<T> {
    List<T> buscarPermisosPorRolId(Long rolId);

    List<T> guardarTodos(List<T> list);

}
