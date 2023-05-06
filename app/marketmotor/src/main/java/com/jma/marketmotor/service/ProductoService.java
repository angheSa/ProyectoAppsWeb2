package com.jma.marketmotor.service;

import com.jma.marketmotor.api.EmpleadoResponse;
import com.jma.marketmotor.service.common.ICrudCommon;

import java.util.List;

public interface ProductoService<T> extends ICrudCommon<T> {

    List<T> guardarTodos(List<T> list);
}
