package com.gelse.app.dto;

import com.gelse.app.model.Usuario;

public record DatosRespuestaUsuario(
        Long id,
        String name,
        String email
) {
    public DatosRespuestaUsuario(Usuario usuario){
        this(usuario.getId(),usuario.getName(), usuario.getEmail());
    }
}
