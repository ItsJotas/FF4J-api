package com.example.ff4j_api.service;

import com.example.ff4j_api.model.Application;
import com.example.ff4j_api.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ModelMapper mapper;

    public Application findById(Long applicationId) {
        return repository.findById(applicationId).get();
    }
}
