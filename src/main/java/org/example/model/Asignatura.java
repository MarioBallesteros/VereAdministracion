package org.example.model;

import java.util.List;

public class Asignatura {
    private String codigo;
    private List<String> profesores;

    public Asignatura() {
    }

    public Asignatura(String codigo, List<String> profesores) {
        this.codigo = codigo;
        this.profesores = profesores;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public List<String> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<String> profesores) {
        this.profesores = profesores;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "codigo='" + codigo + '\'' +
                ", profesores=" + profesores +
                '}';
    }

}
