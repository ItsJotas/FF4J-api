package com.example.ff4j_api.repository;

import com.example.ff4j_api.model.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {
}
