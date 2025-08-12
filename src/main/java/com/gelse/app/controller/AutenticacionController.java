package com.gelse.app.controller;

import com.gelse.app.dto.DatosAutenticacion;
import com.gelse.app.dto.DatosTokenJWT;
import com.gelse.app.model.Usuario;
import com.gelse.app.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    // 3
    //se obtienen los datos de inicio y se  valida y autentican esos datos
    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        Authentication autenticacion = manager.authenticate(authenticationToken);

        var tokekJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokekJWT));
    }
}
