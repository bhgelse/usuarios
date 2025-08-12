package com.gelse.app.model;


import com.gelse.app.dto.DatosActualizarUsuario;
import com.gelse.app.dto.DatosRegistroUsuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
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
    // 5
    //para que se conozca a cual es su usuario y contraseña
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
