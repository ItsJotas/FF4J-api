package com.example.ff4j_api.service;

import com.example.ff4j_api.exception.customized.BadRequestException;
import com.example.ff4j_api.model.Application;
import com.example.ff4j_api.model.dto.input.ApplicationCreateDTO;
import com.example.ff4j_api.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ModelMapper mapper;

    public Application findById(Long applicationId) {
        return repository.findById(applicationId)
                .orElseThrow(() -> new BadRequestException("Application not found with id: " + applicationId));
    }

    public void verifyIfNameExists(String applicationName){
        Application application = repository.findByName(applicationName);
        if(Objects.nonNull(application)){
            throw new BadRequestException("There is already an application with this name: '" + applicationName + "'");
        }
    }

    public void createApplication(ApplicationCreateDTO applicationCreateDTO) {
        verifyIfNameExists(applicationCreateDTO.getApplicationName());

        Application application = mapper.map(applicationCreateDTO, Application.class);

        application.setIsApplicationEnabled(Boolean.TRUE);
        repository.save(application);
    }
}
