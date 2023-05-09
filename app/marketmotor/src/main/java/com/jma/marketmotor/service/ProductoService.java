package com.jma.marketmotor.service;

import com.jma.marketmotor.api.ProductoResponse;
import com.jma.marketmotor.service.common.ICrudCommon;

import java.util.List;

public interface ProductoService<T> extends ICrudCommon<T> {

    List<T> guardarTodos(List<T> list);

    ProductoResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);
}
