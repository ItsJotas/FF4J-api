package com.example.ff4j_api.model.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureFlagOutputDTO {

    private Long id;
    private String name;
    private boolean enabled;
    private String description;
    private PhaseOutputDTO phase;
    private ApplicationOutputDTO application;
}
