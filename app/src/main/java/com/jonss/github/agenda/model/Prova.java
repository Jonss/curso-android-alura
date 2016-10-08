package com.jonss.github.agenda.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by neuromancer on 07/10/16.
 */
public class Prova implements Serializable {

    private String materia;
    private String data;
    private List<String> assuntos;


    public Prova(String materia, String data, List<String> assuntos) {
        this.materia = materia;
        this.data = data;
        this.assuntos = assuntos;
    }

    public String getMateria() {
        return materia;
    }

    public String getData() {
        return data;
    }

    public List<String> getAssuntos() {
        return assuntos;
    }

    @Override
    public String toString() {
        return this.materia;
    }
}
