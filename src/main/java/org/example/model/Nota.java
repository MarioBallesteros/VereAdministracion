package org.example.model;

import org.example.hibernate.AlumnoEntity;

public class Nota {
    private Alumno alumno;
    private Asignatura asignatura;
    private String profesor;
    private double nota;

    public Nota() {
    }

    public Nota(Alumno alumno, Asignatura asignatura, String profesor, double nota) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.nota = nota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "alumno=" + alumno +
                ", asignatura=" + asignatura +
                ", profesor='" + profesor + '\'' +
                ", nota=" + nota +
                '}';
    }
}
