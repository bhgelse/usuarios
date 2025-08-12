package com.gelse.app.dto;

import jakarta.validation.constraints.Email;

public record DatosActualizarUsuario(
         String name,
         @Email String email,
         String password
) {
}
