package com.jma.marketmotor.repository;

import com.jma.marketmotor.entity.DetalleOrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompraEntity,Long> {
}
