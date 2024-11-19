package com.example.ff4j_api.repository;

import com.example.ff4j_api.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
