package com.gelse.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
         String name,
         @Email String email,
         String password
) {
}
