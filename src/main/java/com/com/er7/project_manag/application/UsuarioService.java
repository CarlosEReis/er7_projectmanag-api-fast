package com.com.er7.project_manag.application;

import com.com.er7.project_manag.api.v1.mapper.UsuarioMapper;
import com.com.er7.project_manag.api.v1.model.request.UsuarioRequest;
import com.com.er7.project_manag.api.v1.model.response.UsuarioResponse;
import com.com.er7.project_manag.domain.Usuario;
import com.com.er7.project_manag.infrastructure.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponse salvar(UsuarioRequest usuarioRequest) {
         return UsuarioMapper.toResponse(
             usuarioRepository.save(
                 UsuarioMapper.toEntity(usuarioRequest)));
    }

    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll()
            .stream()
            .map(UsuarioMapper::toResponse)
            .toList();
    }

    public UsuarioResponse buscar(UUID uuid) {
        return UsuarioMapper.toResponse(buscarOuFalhar(uuid));
    }

    @Transactional
    public UsuarioResponse atualizar(UUID uuid, UsuarioRequest usuarioRequest) {
        var usuario = buscarOuFalhar(uuid);
        BeanUtils.copyProperties(usuarioRequest, usuario, "id", "publicId");
        return UsuarioMapper.toResponse(usuario);
    }

    @Transactional
    public void deletar(UUID uuid) {
        var usuario = buscarOuFalhar(uuid);
        usuarioRepository.delete(usuario);
    }

    public Usuario buscarOuFalhar(UUID uuid) {
        return usuarioRepository
            .findByPublicId(uuid)
            .orElseThrow( () -> new EntityNotFoundException("Usuário " + uuid + " não encontrado"));
    }

}
