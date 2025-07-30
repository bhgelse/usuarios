package com.gelse.usuarios.repository;

import com.gelse.usuarios.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepositoy extends JpaRepository<Usuario, Long> {
    Page<Usuario> findByEstadoTrue(Pageable pageable);
}
