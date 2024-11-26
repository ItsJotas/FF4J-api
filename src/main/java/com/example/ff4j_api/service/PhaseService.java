package com.example.ff4j_api.service;

import com.example.ff4j_api.exception.customized.BadRequestException;
import com.example.ff4j_api.model.Phase;
import com.example.ff4j_api.model.dto.input.PhaseCreateDTO;
import com.example.ff4j_api.repository.PhaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PhaseService {

    private final PhaseRepository repository;
    private final ModelMapper mapper;

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
}
