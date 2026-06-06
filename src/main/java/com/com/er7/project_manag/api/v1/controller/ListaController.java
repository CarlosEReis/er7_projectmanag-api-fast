package com.com.er7.project_manag.api.v1.controller;

import com.com.er7.project_manag.api.v1.model.request.ListaRequest;
import com.com.er7.project_manag.api.v1.model.request.UsuarioRequest;
import com.com.er7.project_manag.api.v1.model.response.ListaResponse;
import com.com.er7.project_manag.application.ListaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/listas")
public class ListaController {

    // POST
    // GET
    // PUT
    // DELETE

    private final ListaService listaService;

    public ListaController(ListaService usuarioSrvice) {
        this.listaService = usuarioSrvice;
    }

    @PostMapping
    public ResponseEntity<ListaResponse> salvar(@RequestBody @Valid ListaRequest novaLista) {
        return ResponseEntity.ok(
            listaService.criar(novaLista));
    }

    @GetMapping
    public ResponseEntity<List<ListaResponse>> listar() {
        return ResponseEntity.ok(listaService.listar());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ListaResponse> buscar(@PathVariable UUID uuid) {
        return ResponseEntity.ok(listaService.buscar(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ListaResponse> atualizar(@PathVariable UUID uuid, @RequestBody @Valid ListaRequest listaRequest) {
        return ResponseEntity.ok(listaService.atualizar(uuid, listaRequest)) ;
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable UUID uuid) {
        listaService.deletar(uuid);
        return ResponseEntity.noContent().build();
    }
}
