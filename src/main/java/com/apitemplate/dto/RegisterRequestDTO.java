package com.apitemplate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotNull(message = "O e-mail não deve ser nulo.")
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail deve ser válido.")
        @Size(max = 255, message = "O tamanho máximo do email deve ser 255 caracteres.")
        String email,

        @NotNull(message = "A senha não deve ser nula.")
        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 6, message = "A senha deve conter no minímo 6 caracteres.")
        String password,

        @NotNull(message = "O nome não deve ser nulo.")
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 255, message = "O tamanho máximo do nome deve ser 255 caracteres.")
        String name
) {
    public RegisterRequestDTO {
        email = email.trim();
        password = password.trim();
        name = name.trim();
    }
}
