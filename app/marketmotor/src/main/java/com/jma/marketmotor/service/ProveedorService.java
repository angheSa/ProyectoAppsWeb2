package com.jma.marketmotor.service;

import com.jma.marketmotor.api.ProveedorResponse;
import com.jma.marketmotor.service.common.ICrudCommon;

import java.util.List;

public interface ProveedorService<T> extends ICrudCommon<T> {

    ProveedorResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);
    List<T> guardarTodos(List<T> list);
}

