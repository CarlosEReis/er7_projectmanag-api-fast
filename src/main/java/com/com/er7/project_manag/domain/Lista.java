package com.com.er7.project_manag.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "lista")
@SoftDelete(columnName = "deleted")
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public UUID publicId;
    private String nome;
    private String cor;
    private String descricao;
    private int ordem;

    //private List<Ticket> tickets;

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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    @PrePersist
    private void geraUuid() {
        setPublicId(UUID.randomUUID());
    }
}
