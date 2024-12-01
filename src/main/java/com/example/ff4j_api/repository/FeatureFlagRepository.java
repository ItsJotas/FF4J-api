package com.example.ff4j_api.repository;

import com.example.ff4j_api.model.FeatureFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureFlagRepository extends JpaRepository<FeatureFlag, Long> {

    @Query(value = "SELECT * FROM feature_flag WHERE ID_PHASE = :phaseId", nativeQuery = true)
    List<FeatureFlag> findAllByPhaseId(@Param("phaseId") Long phaseId);

    @Query(value = "SELECT * FROM feature_flag WHERE ID_APPLICATION = :applicationId", nativeQuery = true)
    List<FeatureFlag> findAllByApplicationId(@Param(("applicationId")) Long applicationId);
}
