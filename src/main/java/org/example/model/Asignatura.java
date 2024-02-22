package org.example.model;

import java.util.List;

public class Asignatura {
    private String codigo;
    private String curso;
    private int creditos;
    private List<String> profesores;

    public Asignatura() {
    }

    public Asignatura(String codigo, String curso, int creditos, List<String> profesores) {
        this.codigo = codigo;
        this.curso = curso;
        this.creditos = creditos;
        this.profesores = profesores;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
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
                ", curso='" + curso + '\'' +
                ", creditos=" + creditos +
                ", profesores=" + profesores +
                '}';
    }
}
