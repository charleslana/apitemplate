package com.apitemplate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FieldErrorDTO {
    private String field;
    private String message;

    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
