package com.example.ff4j_api.service;

import com.example.ff4j_api.exception.customized.BadRequestException;
import com.example.ff4j_api.model.Application;
import com.example.ff4j_api.model.FeatureFlag;
import com.example.ff4j_api.model.Phase;
import com.example.ff4j_api.model.dto.input.FeatureFlagCreateDTO;
import com.example.ff4j_api.model.dto.output.FeatureFlagOutputDTO;
import com.example.ff4j_api.model.dto.update.FeatureFlagUpdateDTO;
import com.example.ff4j_api.repository.FeatureFlagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        verifyRepeatedFeatureKey(featureFlag.getFeatureKey());

        repository.save(featureFlag);
    }

    private void getFeatureKey(FeatureFlag featureFlag){
        String applicationName = featureFlag.getApplication().getApplicationName().toUpperCase();
        String phaseName = featureFlag.getPhase().getName().toUpperCase();
        String featureName = featureFlag.getName().toUpperCase();

        String featureKey = applicationName + "_" + phaseName + "_" + featureName + "_FEATURE";
        featureFlag.setFeatureKey(featureKey);
    }

    public void deleteFeatureFlag(Long featureId) {
        FeatureFlag featureFlag = findFeatureFlagById(featureId);
        repository.delete(featureFlag);
    }

    public FeatureFlag findFeatureFlagById(Long featureId){
        return repository.findById(featureId).orElseThrow(() -> new  BadRequestException("Feature Flag not found " +
                "with id: " + featureId));
    }

    public List<FeatureFlag> findAllFeaturesByPhaseId(Long phaseId) {
        return repository.findAllByPhaseId(phaseId);
    }

    public List<FeatureFlag> findAllFeaturesByApplicationId(Long applicationId) {
        return repository.findAllByApplicationId(applicationId);
    }

    public Page<FeatureFlagOutputDTO> getFeatureFlagPage(String name, Boolean enabled, Long phaseId, Long applicationId,
                                                         Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {

        Sort.Direction direction = orderBy.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        Page<FeatureFlag> featureFlagPage = repository.findAllPage(paging, name, enabled, phaseId, applicationId);

        return featureFlagPage.map(f -> mapper.map(f, FeatureFlagOutputDTO.class));
    }

    public void updateFeatureFlag(Long id, FeatureFlagUpdateDTO updateDTO) {
        FeatureFlag featureFlag = findFeatureFlagById(id);

        if(Objects.nonNull(updateDTO.getPhaseId())){
            Phase phase = phaseService.findById(updateDTO.getPhaseId());
            featureFlag.setPhase(phase);
        }

        if(Objects.nonNull(updateDTO.getApplicationId())){
            Application application = applicationService.findById(updateDTO.getApplicationId());
            featureFlag.setApplication(application);
        }

        mapper.map(updateDTO, featureFlag);
        getFeatureKey(featureFlag);
        verifyRepeatedFeatureKey(featureFlag.getFeatureKey());

        repository.save(featureFlag);
    }

    private void verifyRepeatedFeatureKey(String featureKey) {
        FeatureFlag repeatedFeatureKey = repository.getFeatureFlagByKey(featureKey);
        if(Objects.nonNull(repeatedFeatureKey)){
            throw new BadRequestException("A Feature Flag with the specified name, phase, and application already exists.");
        }
    }
}
