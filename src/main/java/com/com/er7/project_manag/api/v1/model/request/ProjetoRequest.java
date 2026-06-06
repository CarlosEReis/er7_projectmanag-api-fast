package com.com.er7.project_manag.api.v1.model.request;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ProjetoRequest(
    String nome,
    String descricao,
    OffsetDateTime dataInicio,
    OffsetDateTime dataFim,
    UUID responsavel
) {}
