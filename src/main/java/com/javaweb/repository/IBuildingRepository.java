package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBuildingRepository extends JpaRepository<BuildingEntity,Long > {
}
