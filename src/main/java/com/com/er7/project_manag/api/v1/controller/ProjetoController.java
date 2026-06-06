package com.com.er7.project_manag.api.v1.controller;

import com.com.er7.project_manag.api.v1.model.request.ProjetoRequest;
import com.com.er7.project_manag.api.v1.model.response.ProjetoResponse;
import com.com.er7.project_manag.api.v1.model.response.UsuarioResponse;
import com.com.er7.project_manag.application.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/projetos")
public class ProjetoController {

    private ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    /*
    OK POST    /projetos
    OK GET     /projetos
    OK GET     /projetos/{id}
    Ok PUT     /projetos/{id}
    Ok DELETE  /projetos/{id}

    POST    /projetos/{id}/membros
    GET     /projetos/{id}/membros
    DELETE  /projetos/{id}/membros/{idMembro}
     */

    @PostMapping
    public ResponseEntity<ProjetoResponse> criar(@RequestBody @Valid ProjetoRequest projetoRequest) {
        return ResponseEntity.ok(
            projetoService.salvar(projetoRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProjetoResponse>> listar() {
        return ResponseEntity.ok(
            projetoService.listar());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProjetoResponse> buscar(@PathVariable UUID uuid) {
        return ResponseEntity.ok(
            projetoService.buscar(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProjetoResponse> atualizar(@PathVariable UUID uuid, @RequestBody @Valid ProjetoRequest projetoRequest) {
        return ResponseEntity.ok(
            projetoService.atualizar(uuid, projetoRequest)
        );
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> desativar(@PathVariable UUID uuid) {
        projetoService.desativar(uuid);
        return ResponseEntity.noContent().build();
    }

    /*
    POST    /projetos/{id}/membros
    GET     /projetos/{id}/membros
    DELETE  /projetos/{id}/membros/{idMembro}*/

    @PostMapping("/{idProjeto}/membros/{idMembro}")
    public ResponseEntity<List<UsuarioResponse>> adicionarMembro(@PathVariable UUID idProjeto, @PathVariable UUID idMembro) {
        return ResponseEntity.ok(
            projetoService.adicionarMembro(idProjeto, idMembro));
    }

    @GetMapping("/{uuid}/membros")
    public ResponseEntity<List<UsuarioResponse>> listaMembros(@PathVariable UUID uuid) {
        return ResponseEntity.ok(
            projetoService.listarMembros(uuid)
        );
    }

    @DeleteMapping("/{idProjeto}/membros/{idMembro}")
    public ResponseEntity<Void> removerMembro(@PathVariable UUID idProjeto, @PathVariable UUID idMembro) {
        projetoService.removerMembro(idProjeto, idMembro);
        return ResponseEntity.noContent().build();
    }
}
