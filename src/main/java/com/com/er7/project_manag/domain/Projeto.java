package com.com.er7.project_manag.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "projeto")
@SoftDelete(columnName = "deleted")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID publicId;
    private String nome;
    private String descricao;
    private StatusProjeto status = StatusProjeto.ATIVO;

    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataInicio;
    private OffsetDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    @ManyToMany
    private Set<Usuario> membros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public OffsetDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(OffsetDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public OffsetDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(OffsetDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsaval) {
        this.responsavel = responsaval;
    }

    public Set<Usuario> getMembros() {
        return membros;
    }

    public void adicionarMembro(Usuario membro) {
        membros.add(membro);
    }

    public void removerMembro(Usuario membro) {
        membros.remove(membro);
    }

    @PrePersist
    private void geraUuid() {
        setPublicId(UUID.randomUUID());
    }
}
