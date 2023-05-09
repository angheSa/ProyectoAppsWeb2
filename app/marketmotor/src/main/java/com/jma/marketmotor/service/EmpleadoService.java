package com.jma.marketmotor.service;

import com.jma.marketmotor.api.EmpleadoResponse;
import com.jma.marketmotor.service.common.ICrudCommon;

import java.util.List;

public interface EmpleadoService<T> extends ICrudCommon<T> {

    EmpleadoResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

    List<T> guardarTodos(List<T> list);

}
