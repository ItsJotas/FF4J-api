package com.example.ff4j_api.service;

import com.example.ff4j_api.exception.customized.BadRequestException;
import com.example.ff4j_api.model.Application;
import com.example.ff4j_api.model.FeatureFlag;
import com.example.ff4j_api.model.dto.input.ApplicationCreateDTO;
import com.example.ff4j_api.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ModelMapper mapper;
    private FeatureFlagService featureFlagService;

    @Autowired
    public void setFeatureFlagService(@Lazy FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

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

        repository.save(application);
    }

    public void deleteApplication(Long applicationId) {
        Application application = findById(applicationId);
        List<FeatureFlag> featureFlags = featureFlagService.findAllFeaturesByApplicationId(applicationId);

        if(!featureFlags.isEmpty()){
            throw new BadRequestException("Cannot delete the application because it is associated with one or more " +
                    "feature flags.");
        }
        repository.delete(application);
    }
}
