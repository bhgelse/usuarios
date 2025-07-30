package com.gelse.usuarios.controller;

import com.gelse.usuarios.dto.DatosActualizarUsuario;
import com.gelse.usuarios.dto.DatosRegistroUsuario;
import com.gelse.usuarios.dto.DatosRespuestaUsuario;
import com.gelse.usuarios.model.Usuario;
import com.gelse.usuarios.repository.IUsuarioRepositoy;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    IUsuarioRepositoy usuarioRepositoy;

    @Transactional
    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepositoy.save(new Usuario(datos));
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario);
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> listar(@PageableDefault(size = 5) Pageable pageable) {
        Page<DatosRespuestaUsuario> respuestaUsuarios = usuarioRepositoy.findByEstadoTrue(pageable).map(DatosRespuestaUsuario::new);
        return ResponseEntity.ok(respuestaUsuarios);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarUsuario datos, @PathVariable Long id) {
        Usuario usuario = usuarioRepositoy.getReferenceById(id);
        if(usuario.getEstado() != true){
            throw new RuntimeException("Este usuario no se encuentra activo");
        }
        usuario.actualizarInformacion(datos);
        var datosRespuestaUsuario = new DatosRespuestaUsuario(usuario);
        return ResponseEntity.ok(datosRespuestaUsuario);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        Usuario usuario = usuarioRepositoy.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }


}
