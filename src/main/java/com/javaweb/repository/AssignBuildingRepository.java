package com.javaweb.repository;

import com.javaweb.entity.AssignBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignBuildingRepository extends JpaRepository<AssignBuildingEntity,Long> {
    void deleteByBuildingEntity_Id(Long buildingEntityId);

    List<AssignBuildingEntity> findByBuildingEntity_Id(Long buildingId);
}
