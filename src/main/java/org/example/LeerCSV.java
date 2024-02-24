package org.example;

import org.example.hibernate.AlumnoEntity;
import org.example.model.Alumno;
import org.example.model.Asignatura;
import org.example.model.Instituto;
import org.example.model.Nota;

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
            br.readLine(); // Salta la primera línea de encabezados
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");
                String asignaturaCodigo = valores[2];
                String profesor = valores[3];
                Asignatura asignaturaExistente = null;
                for (Asignatura a : asignaturas) {
                    if (a.getCodigo().equalsIgnoreCase(asignaturaCodigo)) {
                        asignaturaExistente = a;
                        break;
                    }
                }
                if (asignaturaExistente == null) {
                    List<String> profesores = new ArrayList<>();
                    profesores.add(profesor);
                    Asignatura nuevaAsignatura = new Asignatura(asignaturaCodigo, profesores);
                    asignaturas.add(nuevaAsignatura);
                } else {
                    if (!asignaturaExistente.getProfesores().contains(profesor)) {
                        asignaturaExistente.getProfesores().add(profesor);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return asignaturas;
    }


    public List<Nota> LeerNotas(String rutaCSV, List<Alumno> alumnos, List<Asignatura> asignaturas) {
        List<Nota> notas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            br.readLine(); // Saltar encabezado
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");
                // Asumiendo que tienes identificadores únicos o nombres para buscar
                String nia = valores[0];
                String nombreAsignatura = valores[2];
                String profesor = valores[3];
                double notaValor = Double.parseDouble(valores[4].replace(",", "."));

                Alumno alumnoEncontrado = alumnos.stream()
                        .filter(a -> a.getNia().equals(nia))
                        .findFirst()
                        .orElse(null);
                Asignatura asignaturaEncontrada = asignaturas.stream()
                        .filter(a -> a.getCodigo().equalsIgnoreCase(nombreAsignatura))
                        .findFirst()
                        .orElse(null);

                if (alumnoEncontrado != null && asignaturaEncontrada != null) {
                    Nota nota = new Nota(alumnoEncontrado, asignaturaEncontrada, profesor, notaValor);
                    notas.add(nota);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notas;
    }

}

