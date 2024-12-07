package com.example.ff4j_api.service;

import com.example.ff4j_api.exception.customized.BadRequestException;
import com.example.ff4j_api.model.FeatureFlag;
import com.example.ff4j_api.model.Phase;
import com.example.ff4j_api.model.dto.input.PhaseCreateDTO;
import com.example.ff4j_api.model.dto.output.PhaseOutputDTO;
import com.example.ff4j_api.repository.PhaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PhaseService {

    private final PhaseRepository repository;
    private final ModelMapper mapper;
    private FeatureFlagService featureFlagService;

    @Autowired
    public void setFeatureFlagService(@Lazy FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    public Phase findById(Long phaseId) {
        return repository.findById(phaseId)
                .orElseThrow(() -> new BadRequestException("Phase not found with id: " + phaseId));
    }

    public void verifyIfNameExists(String phaseName){
        Phase phase = repository.findByName(phaseName);
        if(Objects.nonNull(phase)){
            throw new BadRequestException("There is already an phase with this name: '" + phaseName + "'");
        }
    }

    public void createPhase(PhaseCreateDTO phaseCreateDTO) {
        verifyIfNameExists(phaseCreateDTO.getName());
        Phase phase = mapper.map(phaseCreateDTO, Phase.class);
        repository.save(phase);
    }

    public void deletePhase(Long phaseId) {
        Phase phase = findById(phaseId);
        List<FeatureFlag> featureFlags = featureFlagService.findAllFeaturesByPhaseId(phaseId);

        if(!featureFlags.isEmpty()){
            throw new BadRequestException("Cannot delete the phase because it is associated with one or more " +
                    "feature flags.");
        }
        repository.delete(phase);
    }

    public Page<PhaseOutputDTO> getPhasePage(String name, Integer pageNumber, Integer pageSize, String sortBy,
                                             String orderBy) {

        Sort.Direction direction = orderBy.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        Page<Phase> phasePage = repository.findAllPage(paging, name);

        return phasePage.map(p -> mapper.map(p, PhaseOutputDTO.class));
    }
}
