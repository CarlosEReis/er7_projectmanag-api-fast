package com.com.er7.project_manag.infrastructure.repository;

import com.com.er7.project_manag.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByPublicId(UUID publicId);
}
