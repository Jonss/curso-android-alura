package com.jonss.github.agenda.model;

public class Aluno {

    private Integer id;
    private String nome;
    private String endereco;
    private String site;
    private String telefone;
    private Double mNota;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
