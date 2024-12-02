package com.example.ff4j_api.model.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureFlagGetDTO {

    private String name;
    private Boolean enabled;
    private Long phaseId;
    private Long applicationId;
}
