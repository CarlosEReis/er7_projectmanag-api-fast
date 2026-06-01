package com.com.er7.project_manag.api.v1.mapper;

import com.com.er7.project_manag.api.v1.model.request.UsuarioRequest;
import com.com.er7.project_manag.api.v1.model.response.UsuarioResponse;
import com.com.er7.project_manag.domain.Usuario;

public class UsuarioMapper {

    public static UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
            usuario.getPublicId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getFoto());
    }

    public static Usuario toEntity(UsuarioRequest usuarioRequest) {
        var usuario = new Usuario();
        usuario.setNome(usuarioRequest.nome());
        usuario.setEmail(usuarioRequest.email());
        usuario.setSenha(usuarioRequest.senha());
        usuario.setFoto(usuarioRequest.foto());
        return usuario;
    }
}
