package com.gelse.usuarios.repository;

import com.gelse.usuarios.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepositoy extends JpaRepository<Usuario, Long> {
    Page<Usuario> findByEstadoTrue(Pageable pageable);
    //busca el usuarios en la base de datos
    UserDetails findByEmail(String login);
}
