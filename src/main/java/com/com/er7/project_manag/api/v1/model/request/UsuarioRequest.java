package com.com.er7.project_manag.api.v1.model.request;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
        @NotBlank String nome,
        @NotBlank String email,
        String foto,
        @NotBlank String senha) { }
