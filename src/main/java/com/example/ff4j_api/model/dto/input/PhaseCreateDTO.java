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
public class PhaseCreateDTO {

    @NotNull(message = "Field Name cannot be empty.")
    @Size(message = "Field Name must have more than 3 and less than 20 letters.", min = 3, max = 20)
    private String name;
}
