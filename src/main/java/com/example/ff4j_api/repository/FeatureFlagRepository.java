package com.example.ff4j_api.repository;

import com.example.ff4j_api.model.FeatureFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureFlagRepository extends JpaRepository<FeatureFlag, Long> {
}
