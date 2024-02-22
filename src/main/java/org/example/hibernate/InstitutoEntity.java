package org.example.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "instituto", schema = "centro", catalog = "")
public class InstitutoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Nif", nullable = false, length = 10)
    private String nif;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "ciudad", nullable = false, length = 50)
    private String ciudad;

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitutoEntity that = (InstitutoEntity) o;
        return Objects.equals(nif, that.nif) && Objects.equals(nombre, that.nombre) && Objects.equals(ciudad, that.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, nombre, ciudad);
    }
}
