package com.com.er7.project_manag.api.v1.model.request;

import jakarta.validation.constraints.NotBlank;

public record ListaRequest(
    @NotBlank String nome,
    @NotBlank String descricao,
    @NotBlank String cor
) {}
