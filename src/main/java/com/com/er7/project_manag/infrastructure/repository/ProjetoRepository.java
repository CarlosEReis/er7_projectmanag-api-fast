package com.com.er7.project_manag.infrastructure.repository;

import com.com.er7.project_manag.domain.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    Optional<Projeto> findByPublicId(UUID publicId);
}
