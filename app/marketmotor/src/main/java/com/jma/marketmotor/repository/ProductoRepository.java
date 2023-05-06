package com.jma.marketmotor.repository;

import com.jma.marketmotor.entity.EmpleadoEntity;
import com.jma.marketmotor.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity,Long> {
}
