package com.example.ff4j_api.model.dto.update;

import com.example.ff4j_api.model.dto.output.ApplicationOutputDTO;
import com.example.ff4j_api.model.dto.output.PhaseOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureFlagUpdateDTO {

    private String name;
    private String description;
    private Long phaseId;
    private Long applicationId;
}
