package org.example.model;

import org.bson.types.ObjectId;

public class Alumno {
    private String nia;
    private String nombre;
    private String direccion;
    private int edad;
    private String email;
    private Instituto instituto;

    public Alumno() {
    }

    public Alumno(String nia, String nombre, String direccion, int edad, String email, Instituto instituto) {
        this.nia = nia;
        this.nombre = nombre;
        this.direccion = direccion;
        this.edad = edad;
        this.email = email;
        this.instituto = instituto;
    }


    public String getNia() {
        return nia;
    }

    public void setNia(String nia) {
        this.nia = nia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nia=" + nia +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", edad=" + edad +
                ", email='" + email + '\'' +
                ", instituto=" + instituto +
                '}';
    }
}
