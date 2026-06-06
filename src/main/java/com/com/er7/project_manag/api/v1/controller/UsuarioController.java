package com.com.er7.project_manag.api.v1.controller;

import com.com.er7.project_manag.api.v1.model.request.UsuarioRequest;
import com.com.er7.project_manag.api.v1.model.response.UsuarioResponse;
import com.com.er7.project_manag.application.UsuarioService;
import com.com.er7.project_manag.domain.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioSrvice) {
        this.usuarioService = usuarioSrvice;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> salvar(@RequestBody @Valid UsuarioRequest novoUsuario) {
        return ResponseEntity.ok(
            usuarioService.salvar(novoUsuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable UUID uuid) {
        return ResponseEntity.ok(usuarioService.buscar(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable UUID uuid, @RequestBody @Valid UsuarioRequest usuarioRequest) {
        return ResponseEntity.ok(usuarioService.atualizar(uuid, usuarioRequest)) ;
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable UUID uuid) {
        usuarioService.deletar(uuid);
        return ResponseEntity.noContent().build();
    }

}
