package com.com.er7.project_manag.api.v1.mapper;

import com.com.er7.project_manag.api.v1.model.request.ListaRequest;
import com.com.er7.project_manag.api.v1.model.response.ListaResponse;
import com.com.er7.project_manag.domain.Lista;

public class ListaMapper {

    public static Lista toEntity(ListaRequest listaRequest) {
        var lista = new Lista();
        lista.setNome(listaRequest.nome());
        lista.setDescricao(listaRequest.descricao());
        lista.setCor(listaRequest.cor());
        return lista;
    }

    public static ListaResponse toResponse(Lista lista) {
        return new ListaResponse(
            lista.getPublicId(),
            lista.getNome(),
            lista.getDescricao(),
            lista.getCor()
        );
    }
}
