package com.example.ff4j_api.service;

import com.example.ff4j_api.model.Application;
import com.example.ff4j_api.model.FeatureFlag;
import com.example.ff4j_api.model.Phase;
import com.example.ff4j_api.model.dto.input.FeatureFlagCreateDTO;
import com.example.ff4j_api.repository.FeatureFlagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureFlagService {

    private final FeatureFlagRepository repository;
    private final ApplicationService applicationService;
    private final PhaseService phaseService;
    private final ModelMapper mapper;

    public void createFeatureFlag(FeatureFlagCreateDTO featureFlagCreateDTO) {
        FeatureFlag featureFlag = mapper.map(featureFlagCreateDTO, FeatureFlag.class);

        Phase phase = phaseService.findById(featureFlagCreateDTO.getPhaseId());
        Application application = applicationService.findById(featureFlagCreateDTO.getApplicationId());

        featureFlag.setPhase(phase);
        featureFlag.setApplication(application);

        getFeatureKey(featureFlag);

        repository.save(featureFlag);
    }

    private void getFeatureKey(FeatureFlag featureFlag){
        String applicationName = featureFlag.getApplication().getApplicationName().toUpperCase();
        String phaseName = featureFlag.getPhase().getName().toUpperCase();
        String featureName = featureFlag.getName().toUpperCase();

        String featureKey = applicationName + "_" + phaseName + "_" + featureName + "_FEATURE";
        featureFlag.setFeatureKey(featureKey);
    }
}
