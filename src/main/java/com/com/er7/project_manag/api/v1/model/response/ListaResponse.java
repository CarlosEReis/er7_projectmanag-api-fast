package com.com.er7.project_manag.api.v1.model.response;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ListaResponse(
    UUID publicId,
    String nome,
    String descricao,
    String cor
) {}
