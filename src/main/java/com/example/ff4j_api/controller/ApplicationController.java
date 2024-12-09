package com.example.ff4j_api.controller;

import com.example.ff4j_api.model.dto.input.ApplicationCreateDTO;
import com.example.ff4j_api.model.dto.output.ApplicationOutputDTO;
import com.example.ff4j_api.model.dto.update.ApplicationUpdateDTO;
import com.example.ff4j_api.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<ApplicationOutputDTO>> getApplicationPage(
            @RequestParam(value = "applicationName", required = false) String applicationName,
            @RequestParam(value = "online", required = false) Boolean online,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String order){
        Page<ApplicationOutputDTO> applicationDTOPage = service.getApplicationPage(applicationName, online, pageNumber,
                pageSize, sort, order);
        return ResponseEntity.ok().body(applicationDTOPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateApplication(@PathVariable("id") Long id,
                                                  @RequestBody ApplicationUpdateDTO applicationUpdateDTO){
        service.updateApplication(id, applicationUpdateDTO);
        return ResponseEntity.ok().build();
    }
}
