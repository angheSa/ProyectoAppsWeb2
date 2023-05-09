package com.jma.marketmotor.service;

import com.jma.marketmotor.api.DetalleOrdenCompraResponse;
import com.jma.marketmotor.service.common.ICrudCommon;

public interface DetalleOrdenCompraService<T> extends ICrudCommon<T> {

    DetalleOrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);
}
