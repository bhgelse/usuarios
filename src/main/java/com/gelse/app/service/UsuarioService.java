package com.gelse.app.service;

import com.gelse.app.dto.DatosActualizarUsuario;
import com.gelse.app.dto.DatosRegistroUsuario;
import com.gelse.app.dto.DatosRespuestaUsuario;
import com.gelse.app.model.Usuario;
import com.gelse.app.repository.IUsuarioRepositoy;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private IUsuarioRepositoy usuarioRepositoy;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(DatosRegistroUsuario datos){
        Usuario usuario = new Usuario(datos);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepositoy.save(usuario);
    }

    public Page<DatosRespuestaUsuario> listarUsuario(Pageable pageable){
        return usuarioRepositoy.findByEstadoTrue(pageable).map(DatosRespuestaUsuario::new);
    }

    public Usuario actualizarUsuario(Long id, DatosActualizarUsuario datos){
        Usuario usuario = usuarioRepositoy.getReferenceById(id);
        if(usuario.getEstado() != true){
            throw new EntityNotFoundException("Este usuario no se encuentra disponible");
        }
        usuario.actualizarInformacion(datos);
        if(datos.password() != null && !datos.password().isBlank()){
            usuario.setPassword(passwordEncoder.encode(datos.password()));
        }
        return usuario;
    }

    public void eliminarUsuario(Long id){
        Usuario usuario = usuarioRepositoy.getReferenceById(id);
        usuario.desactivarUsuario();
    }
}
