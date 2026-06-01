package com.com.er7.project_manag.api.v1.model.response;

import java.util.UUID;

public record UsuarioResponse(
        UUID uuid,
        String nome,
        String email,
        String foto
) {
}
