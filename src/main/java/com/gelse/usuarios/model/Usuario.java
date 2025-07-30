package com.gelse.usuarios.model;


import com.gelse.usuarios.dto.DatosActualizarUsuario;
import com.gelse.usuarios.dto.DatosRegistroUsuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean estado;
    @CreationTimestamp
    private Instant fechaCreacion;
    @UpdateTimestamp
    private Instant fechaModificacion;

    public Usuario(@Valid DatosRegistroUsuario datos) {
        this.name = datos.name();
        this.email = datos.email();
        this.password = datos.password();
        this.estado = true;
    }

    public void actualizarInformacion(@Valid DatosActualizarUsuario datos) {
        if (datos.name() != null) {
            this.name = datos.name();
        }

        if (datos.email() != null) {
            this.email = datos.email();
        }

        if (datos.password() != null) {
            this.password = datos.password();
        }
    }

    public void desactivarUsuario() {
        this.estado = false;
    }
}
