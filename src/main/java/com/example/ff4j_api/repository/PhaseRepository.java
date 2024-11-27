package com.example.ff4j_api.repository;

import com.example.ff4j_api.model.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {


    @Query(value = "SELECT * FROM phase WHERE phase.NAME = :phaseName", nativeQuery = true)
    Phase findByName(@Param("phaseName") String phaseName);
}
