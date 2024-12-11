package com.example.ff4j_api.repository;

import com.example.ff4j_api.model.FeatureFlag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "SELECT * FROM feature_flag " +
            "    WHERE (:name IS NULL OR LOWER(NAME) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "      AND (:enabled IS NULL OR ENABLED = :enabled) " +
            "      AND (:phaseId IS NULL OR ID_PHASE = :phaseId) " +
            "      AND (:applicationId IS NULL OR ID_APPLICATION = :applicationId)", nativeQuery = true)
    Page<FeatureFlag> findAllPage(Pageable paging,
                                  @Param("name") String name,
                                  @Param("enabled") Boolean enabled,
                                  @Param("phaseId") Long phaseId,
                                  @Param("applicationId") Long applicationId);

    @Query(value = "SELECT * FROM feature_flag WHERE FEATURE_KEY = :featureKey", nativeQuery = true)
    FeatureFlag getFeatureFlagByKey(@Param("featureKey") String featureKey);
}
