package com.jma.marketmotor.repository;

import com.jma.marketmotor.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<RolEntity,Long> {

    List<RolEntity> findRolEntitiesByPermisosId(Long permisoId);

}
