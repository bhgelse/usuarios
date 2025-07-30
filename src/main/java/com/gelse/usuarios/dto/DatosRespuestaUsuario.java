package com.gelse.usuarios.dto;

import com.gelse.usuarios.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaUsuario(
        Long id,
        String name,
        String email
) {
    public DatosRespuestaUsuario(Usuario usuario){
        this(usuario.getId(),usuario.getName(), usuario.getEmail());
    }
}
