package com.jma.marketmotor.service;

import com.jma.marketmotor.api.OrdenCompraResponse;
import com.jma.marketmotor.service.common.ICrudCommon;

public interface OrdenCompraService<T> extends ICrudCommon<T> {

    OrdenCompraResponse obtenerTodosPaginados(int pageNo, int pageSize, String sortBy, String sortDir);

}
