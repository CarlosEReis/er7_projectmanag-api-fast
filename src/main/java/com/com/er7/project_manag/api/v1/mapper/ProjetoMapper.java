package com.com.er7.project_manag.api.v1.mapper;

import com.com.er7.project_manag.api.v1.model.request.ProjetoRequest;
import com.com.er7.project_manag.api.v1.model.response.ProjetoResponse;
import com.com.er7.project_manag.domain.Projeto;
import com.com.er7.project_manag.domain.Usuario;

public class ProjetoMapper {

    public static ProjetoResponse toResponse(Projeto projeto) {
        return new ProjetoResponse(
            projeto.getPublicId(),
            projeto.getNome(),
            projeto.getDescricao(),
            projeto.getDataInicio(),
            projeto.getDataFim(),
            UsuarioMapper.toResponse(projeto.getResponsavel()));
    }

    public static Projeto toEntity(ProjetoRequest projetoRequest) {
        var projeto = new Projeto();
        projeto.setNome(projetoRequest.nome());
        projeto.setDescricao(projetoRequest.descricao());
        projeto.setDataInicio(projetoRequest.dataInicio());
        projeto.setDataFim(projetoRequest.dataFim());

        var usuario = new Usuario();
        usuario.setPublicId(projetoRequest.responsavel());

        projeto.setResponsavel(usuario);
        return projeto;
    }
}
