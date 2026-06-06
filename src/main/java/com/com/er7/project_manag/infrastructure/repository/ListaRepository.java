package com.com.er7.project_manag.infrastructure.repository;

import com.com.er7.project_manag.domain.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ListaRepository extends JpaRepository<Lista, Long> {

    Optional<Lista> findByPublicId(UUID publicId);
}
