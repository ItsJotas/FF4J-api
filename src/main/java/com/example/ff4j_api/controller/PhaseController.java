package com.example.ff4j_api.controller;

import com.example.ff4j_api.model.dto.input.PhaseCreateDTO;
import com.example.ff4j_api.model.dto.output.PhaseOutputDTO;
import com.example.ff4j_api.service.PhaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phase")
@RequiredArgsConstructor
public class PhaseController {

    private final PhaseService service;

    @PostMapping
    public ResponseEntity<Void> createPhase(@RequestBody @Valid PhaseCreateDTO phaseCreateDTO){
        service.createPhase(phaseCreateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhase(@PathVariable("id") Long id){
        service.deletePhase(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<PhaseOutputDTO>> getFeatureFlagPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String order
    ){
        Page<PhaseOutputDTO> phasePage = service.getPhasePage(name, pageNumber, pageSize, sort, order);
        return ResponseEntity.ok(phasePage);
    }
}
