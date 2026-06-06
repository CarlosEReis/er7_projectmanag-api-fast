package com.com.er7.project_manag.application;

import com.com.er7.project_manag.api.v1.mapper.ListaMapper;
import com.com.er7.project_manag.api.v1.model.request.ListaRequest;
import com.com.er7.project_manag.api.v1.model.response.ListaResponse;
import com.com.er7.project_manag.domain.Lista;
import com.com.er7.project_manag.infrastructure.repository.ListaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ListaService {

    private final ListaRepository listaRepository;

    public ListaService(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    @Transactional
    public ListaResponse criar(ListaRequest listaRequest) {
        var lista = ListaMapper.toEntity(listaRequest);
        return ListaMapper.toResponse(
            listaRepository.save(lista)
        );
    }

    public List<ListaResponse> listar() {
        return listaRepository.findAll()
            .stream()
            .map(ListaMapper::toResponse)
            .toList();
    }

    public ListaResponse buscar(UUID uuid) {
        return ListaMapper.toResponse(buscarOuFalhar(uuid));
    }

    @Transactional
    public ListaResponse atualizar(UUID uuid, ListaRequest listaRequest) {
        var listaDb = buscarOuFalhar(uuid);
        BeanUtils.copyProperties(listaRequest, listaDb, "id", "publicId");
        return ListaMapper.toResponse(listaDb);
    }

    @Transactional
    public void deletar(UUID uuid) {
        listaRepository.delete(buscarOuFalhar(uuid));
    }


    private Lista buscarOuFalhar(UUID uuid) {
        return listaRepository
            .findByPublicId(uuid)
            .orElseThrow(
                () -> new EntityNotFoundException("Lista "+ uuid + " não encontrada."));
    }
}
