package org.example.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "alumno", schema = "Centro", catalog = "")
public class AlumnoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nia", nullable = false)
    private int nia;
    @Basic
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;
    @Basic
    @Column(name = "edad", nullable = false)
    private int edad;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "instituto_nif", nullable = true, length = 10)
    private String institutoNif;

    public String getNia() {
        return String.valueOf(nia);
    }

    public void setNia(int nia) {
        this.nia = nia;
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

    public String getInstitutoNif() {
        return institutoNif;
    }

    public void setInstitutoNif(String institutoNif) {
        this.institutoNif = institutoNif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoEntity that = (AlumnoEntity) o;
        return nia == that.nia && edad == that.edad && Objects.equals(direccion, that.direccion) && Objects.equals(email, that.email) && Objects.equals(institutoNif, that.institutoNif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nia, direccion, edad, email, institutoNif);
    }
}
