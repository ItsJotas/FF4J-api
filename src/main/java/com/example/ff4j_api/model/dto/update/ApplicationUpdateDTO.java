package com.example.ff4j_api.model.dto.update;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUpdateDTO {

    private String applicationName;
    private String description;
    private String url;
}
