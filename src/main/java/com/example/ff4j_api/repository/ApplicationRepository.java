package com.example.ff4j_api.repository;

import com.example.ff4j_api.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "SELECT * FROM application WHERE application.APPLICATION_NAME = :applicationName", nativeQuery = true)
    Application findByName(@Param("applicationName") String applicationName);

    @Query(value = "SELECT * FROM application " +
            "    WHERE (:applicationName IS NULL OR LOWER(APPLICATION_NAME) LIKE LOWER(CONCAT('%', :applicationName, '%'))) " +
            "      AND (:online IS NULL OR APPLICATION_ONLINE = :online) ", nativeQuery = true)
    Page<Application> findAllPage(Pageable paging,
                                     @Param("applicationName") String applicationName,
                                     @Param("online") Boolean online);
}
