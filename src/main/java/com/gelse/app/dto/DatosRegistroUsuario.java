package com.gelse.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
