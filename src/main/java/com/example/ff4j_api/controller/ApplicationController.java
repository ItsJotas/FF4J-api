package com.example.ff4j_api.controller;

import com.example.ff4j_api.model.dto.input.ApplicationCreateDTO;
import com.example.ff4j_api.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    @PostMapping
    public ResponseEntity<?> createApplication(@RequestBody @Valid ApplicationCreateDTO applicationCreateDTO){
        service.createApplication(applicationCreateDTO);
        return ResponseEntity.ok().build();
    }
}