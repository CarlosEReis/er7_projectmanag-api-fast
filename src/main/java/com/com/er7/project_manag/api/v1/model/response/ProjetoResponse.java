package com.com.er7.project_manag.api.v1.model.response;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ProjetoResponse(
        UUID uuid,
        String nome,
        String descricao,
        OffsetDateTime dataInicio,
        OffsetDateTime dataFim,
        UsuarioResponse responsavel

) {
}
