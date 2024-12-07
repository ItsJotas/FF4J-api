package com.example.ff4j_api.repository;

import com.example.ff4j_api.model.Phase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {


    @Query(value = "SELECT * FROM phase WHERE phase.NAME = :phaseName", nativeQuery = true)
    Phase findByName(@Param("phaseName") String phaseName);

    @Query(value = "SELECT * FROM feature_flag " +
            "    WHERE (:name IS NULL OR LOWER(NAME) LIKE LOWER(CONCAT('%', :name, '%'))) ", nativeQuery = true)
    Page<Phase> findAllPage(Pageable paging, @RequestParam("name") String name);
}
