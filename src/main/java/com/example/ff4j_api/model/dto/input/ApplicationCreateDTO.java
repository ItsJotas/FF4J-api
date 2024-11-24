package com.example.ff4j_api.model.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationCreateDTO {

    @NotNull(message = "Field Application Name cannot be empty.")
    @Size(message = "The field Application Name must not exceed 20 characters.", max = 20)
    private String applicationName;

    @NotNull(message = "Field Application Name cannot be empty.")
    private String url;

    @Size(message = "The field Description must not exceed 150 characters.", max = 150)
    private String description;
}
