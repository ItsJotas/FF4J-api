package com.example.ff4j_api.controller;

import com.example.ff4j_api.model.dto.FeatureFlagCreateDTO;
import com.example.ff4j_api.service.FeatureFlagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature-flag")
@RequiredArgsConstructor
public class FeatureFlagController {

    private final FeatureFlagService service;

    @PostMapping
    public ResponseEntity<?> createFeatureFlag(@RequestBody @Valid FeatureFlagCreateDTO featureFlagCreateDTO){
        service.createFeatureFlag(featureFlagCreateDTO);
        return ResponseEntity.ok().build();
    }
}
