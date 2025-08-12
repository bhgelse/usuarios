package com.gelse.app.controller;

import com.gelse.app.dto.DatosActualizarUsuario;
import com.gelse.app.dto.DatosRegistroUsuario;
import com.gelse.app.dto.DatosRespuestaUsuario;
import com.gelse.app.model.Usuario;
import com.gelse.app.service.UsuarioService;
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
    private UsuarioService usuarioService;

    @Transactional
    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {
        //Usuario usuario = usuarioRepositoy.save(new Usuario(datos));
        Usuario usuario = usuarioService.registrarUsuario(datos);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario);
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> listar(@PageableDefault(size = 5) Pageable pageable) {
        Page<DatosRespuestaUsuario> listaUsuarios = usuarioService.listarUsuario(pageable);
        return ResponseEntity.ok(listaUsuarios);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarUsuario datos, @PathVariable Long id) {
        Usuario usuario = usuarioService.actualizarUsuario(id, datos);
        var datosRespuestaUsuario = new DatosRespuestaUsuario(usuario);
        return ResponseEntity.ok(datosRespuestaUsuario);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }


}
