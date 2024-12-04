package com.example.ff4j_api.controller;

import com.example.ff4j_api.model.dto.input.ApplicationCreateDTO;
import com.example.ff4j_api.model.dto.output.ApplicationDTO;
import com.example.ff4j_api.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    @PostMapping
    public ResponseEntity<Void> createApplication(@RequestBody @Valid ApplicationCreateDTO applicationCreateDTO){
        service.createApplication(applicationCreateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@RequestParam("id") Long id){
        service.deleteApplication(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<ApplicationDTO>> getApplicationPage(
            @RequestParam(value = "applicationName", required = false) String applicationName,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String order){
        Page<ApplicationDTO> applicationDTOPage = service.getApplicationPage(applicationName, enabled, pageNumber,
                pageSize, sort, order);
        return ResponseEntity.ok().body(applicationDTOPage);
    }
}
