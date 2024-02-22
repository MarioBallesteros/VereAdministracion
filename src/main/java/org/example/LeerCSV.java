package org.example;

import org.example.model.Alumno;
import org.example.model.Asignatura;
import org.example.model.Instituto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerCSV {

    public List<Alumno> leerAlumnosDesdeCSV(String rutaArchivo) {
        List<Alumno> alumnos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");
                //NIA;Nombre;Asignatura;Profesor;Nota
                String nia = valores[0];
                String nombre = valores[1];
                String asignatura = valores[2];
                String profesor = valores[3];
                String nota = valores[4];
                Alumno alumno = null;
                for (Alumno a : alumnos) {
                    if (a.getNia().equalsIgnoreCase(nia)) {
                        alumno = a;
                        break;
                    }
                }
                if (alumno == null) {
                    alumno = new Alumno(nia,nombre,null,0,null,null);
                    alumnos.add(alumno);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alumnos;
    }
    public List<Asignatura> LeerAsignaturas(String rutaArchivo) {
        List<Asignatura> asignaturas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");
                //NIA;Nombre;Asignatura;Profesor;Nota
                String nia = valores[0];
                String nombre = valores[1];
                String asignaturaCodigo = valores[2];
                String profesor = valores[3];
                String nota = valores[4];
                Asignatura asignatura;
                boolean existeAsignatura = false;
                for (Asignatura a : asignaturas) {
                    if (a.getCodigo().equalsIgnoreCase(asignaturaCodigo)) {
                        existeAsignatura = true;
                        break;
                    }
                }
                if (!existeAsignatura) {
                    asignatura = new Asignatura(asignaturaCodigo, "1ero", 0, new ArrayList<>());
                    asignaturas.add(asignatura);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return asignaturas;
    }
}

