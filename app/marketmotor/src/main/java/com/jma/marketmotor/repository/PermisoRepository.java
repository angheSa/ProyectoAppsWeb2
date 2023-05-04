package com.jma.marketmotor.repository;

import com.jma.marketmotor.entity.PermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermisoRepository extends JpaRepository<PermisoEntity,Long> {

    List<PermisoEntity> findPermisoEntitiesByRolesId(Long rolId);
}
