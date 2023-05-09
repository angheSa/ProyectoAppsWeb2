package com.jma.marketmotor.repository;

import com.jma.marketmotor.entity.OrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompraEntity,Long> {
}
