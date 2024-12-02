package com.example.ff4j_api.model.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

    private String applicationName;
    private String description;
    private Boolean isApplicationEnabled;
}