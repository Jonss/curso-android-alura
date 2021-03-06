package com.jonss.github.agenda.model;

import java.io.Serializable;

public class Aluno implements Serializable{

    private Long id;
    private String nome;
    private String endereco;
    private String site;
    private String telefone;
    private Double mNota;
    private String caminhoFoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setNota(Double nota) {
        mNota = nota;
    }

    public Double getNota() {
        return mNota;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
