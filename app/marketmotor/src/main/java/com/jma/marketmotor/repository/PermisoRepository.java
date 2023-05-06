package com.jma.marketmotor.repository;

import com.jma.marketmotor.entity.PermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermisoRepository extends JpaRepository<PermisoEntity,Long> {

    @Query(value = "SELECT t1 FROM PermisoEntity t1 join fetch t1.roles t2 WHERE t2.id = ?1 ")
    List<PermisoEntity> findPermisoEntitiesByRolesId(Long rolId);

}
