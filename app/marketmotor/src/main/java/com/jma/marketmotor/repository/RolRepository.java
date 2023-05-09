package com.jma.marketmotor.repository;

import com.jma.marketmotor.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<RolEntity,Long> {

    @Query(value = "SELECT t1 FROM RolEntity t1 join fetch t1.permisos t2 WHERE t2.id = ?1 ")
    List<RolEntity> findRolEntitiesByPermisoId(Long permisoId);

}
