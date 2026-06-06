package com.com.er7.project_manag.application;

import com.com.er7.project_manag.api.v1.mapper.ProjetoMapper;
import com.com.er7.project_manag.api.v1.mapper.UsuarioMapper;
import com.com.er7.project_manag.api.v1.model.request.ProjetoRequest;
import com.com.er7.project_manag.api.v1.model.response.ProjetoResponse;
import com.com.er7.project_manag.api.v1.model.response.UsuarioResponse;
import com.com.er7.project_manag.domain.Projeto;
import com.com.er7.project_manag.infrastructure.repository.ProjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProjetoService {

    private ProjetoRepository projetoRepository;
    private UsuarioService usuarioService;

    public ProjetoService(ProjetoRepository projetoRepository, UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.projetoRepository = projetoRepository;
    }

    @Transactional
    public ProjetoResponse salvar(ProjetoRequest projetoRequest) {
        var usuario = usuarioService.buscarOuFalhar(projetoRequest.responsavel());

        var projeto = ProjetoMapper.toEntity(projetoRequest);
        projeto.setResponsavel(usuario);

        return ProjetoMapper.toResponse(
            projetoRepository.save(
                projeto
            ));
    }

    public List<ProjetoResponse> listar() {
        return projetoRepository.findAll()
            .stream()
            .map(ProjetoMapper::toResponse)
            .toList();
    }

    public ProjetoResponse buscar(UUID uuid) {
        return ProjetoMapper.toResponse(buscarOuFalhar(uuid));
    }

    @Transactional
    public ProjetoResponse atualizar(UUID uuid, ProjetoRequest projetoRequest) {
        var projeto = buscarOuFalhar(uuid);

        if (projetoRequest.responsavel() != null && !projetoRequest.responsavel().equals(projeto.getResponsavel().getPublicId())) {
            var usuario = usuarioService.buscarOuFalhar(projetoRequest.responsavel());
            projeto.setResponsavel(usuario);
        }

        BeanUtils.copyProperties(projetoRequest, projeto, "id", "publicId");
        return ProjetoMapper.toResponse(projeto);
    }

    @Transactional
    public void desativar(UUID uuid) {
        projetoRepository.delete(buscarOuFalhar(uuid));
    }

    private Projeto buscarOuFalhar(UUID uuid) {
        return projetoRepository
            .findByPublicId(uuid)
                .orElseThrow(
                () -> new EntityNotFoundException("Projeto " + uuid + " não encontrado."));

    }

    @Transactional
    public List<UsuarioResponse> adicionarMembro(UUID uuid, @Valid UUID idMembro) {
        var projeto = buscarOuFalhar(uuid);
        var membro = usuarioService.buscarOuFalhar(idMembro);
        projeto.adicionarMembro(membro);
        return projeto.getMembros()
            .stream()
            .map(UsuarioMapper::toResponse).toList();
    }

    public List<UsuarioResponse> listarMembros(UUID uuid) {
        var projeto = buscarOuFalhar(uuid);
        return projeto.getMembros()
            .stream()
            .map(UsuarioMapper::toResponse).toList();
    }

    @Transactional
    public void removerMembro(UUID uuid, @Valid UUID idMembro) {
        var projeto = buscarOuFalhar(uuid);
        var membro = usuarioService.buscarOuFalhar(idMembro);
        projeto.removerMembro(membro);
    }
}
