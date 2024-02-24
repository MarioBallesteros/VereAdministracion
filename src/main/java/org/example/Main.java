package org.example;

import com.mongodb.client.*;
import org.bson.Document;
import org.example.hibernate.AlumnoEntity;
import org.example.hibernate.HibernateUtil;
import org.example.hibernate.InstitutoEntity;
import org.example.model.Alumno;
import org.example.model.Asignatura;
import org.example.model.Nota;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        List<Alumno> alumnosCSV = new ArrayList<>();
        List<Asignatura> asignaturasCSV = new ArrayList<>();
        List<Nota> notasCSV = new ArrayList<>();

        List<AlumnoEntity> alumnosSQL = new ArrayList<>();
        List<InstitutoEntity> institutosSQL = new ArrayList<>();
        List<Alumno> alumnosMongo = new ArrayList<>();

        String rutaCSV = "listado_alumnos.csv";

        LeerCSV csvReader = new LeerCSV();
        alumnosCSV = csvReader.leerAlumnosDesdeCSV(rutaCSV);
        asignaturasCSV = csvReader.LeerAsignaturas(rutaCSV);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            transaction.commit();
            session.beginTransaction();
            alumnosSQL = session.createQuery("from AlumnoEntity", AlumnoEntity.class).stream().toList();
            institutosSQL = session.createQuery("from InstitutoEntity", InstitutoEntity.class).stream().toList();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        sessionFactory.close();


        //Lista asignaturas completa
        for (AlumnoEntity alumnoSQL : alumnosSQL) {
            Alumno alumnoMongo = new Alumno();
            alumnoMongo.setNia(alumnoSQL.getNia());
            alumnoMongo.setDireccion(alumnoSQL.getDireccion());
            alumnoMongo.setEdad(alumnoSQL.getEdad());
            alumnoMongo.setEmail(alumnoSQL.getEmail());
            alumnoMongo.setInstituto(encontrarInstitutoPorNif(alumnoSQL.getInstitutoNif(), institutosSQL));
            for (Alumno alumnoCSV : alumnosCSV) {
                if (alumnoSQL.getNia().equals(alumnoCSV.getNia())) {
                    alumnoMongo.setNombre(alumnoCSV.getNombre());
                    break;
                }
            }
            alumnosMongo.add(alumnoMongo);
        }
        //Lista alumnos,asignaturas completas

        notasCSV = csvReader.LeerNotas(rutaCSV, alumnosMongo, asignaturasCSV);
        //Lista alumnos,asignaturas, notas , insituto completas

        guardarListasEnMongo(alumnosMongo, asignaturasCSV, notasCSV, institutosSQL);
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("Centro");
        MongoCollection<Document> coleccionAlumnos = database.getCollection("alumnos");

        //Número de alumnos por instituto
        MongoCursor<Document> cursor = coleccionAlumnos.find().iterator();
        Map<String, Integer> contadorPorInstituto = new HashMap<>();

        try {
            while (cursor.hasNext()) {
                Document alumno = cursor.next();
                Document instituto = (Document) alumno.get("instituto");
                String nifInstituto = instituto.getString("nif");
                contadorPorInstituto.put(nifInstituto, contadorPorInstituto.getOrDefault(nifInstituto, 0) + 1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        guardarRespuesta(" Número de alumnos por instituto", String.valueOf(contadorPorInstituto));

        // Promedio general de cada estudiante
        MongoCollection<Document> coleccionNotas = database.getCollection("notas");
        cursor = coleccionNotas.find().iterator();
        Map<String, List<Double>> notasPorEstudiante = new HashMap<>();

        try {
            while (cursor.hasNext()) {
                Document nota = cursor.next();
                String niaAlumno = nota.get("alumno", Document.class).getString("nia");
                Double valorNota = nota.getDouble("nota");
                notasPorEstudiante.computeIfAbsent(niaAlumno, k -> new ArrayList<>()).add(valorNota);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Map<String, Double> promedioPorEstudiante = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : notasPorEstudiante.entrySet()) {
            List<Double> notas = entry.getValue();
            double promedio = notas.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            promedioPorEstudiante.put(entry.getKey(), promedio);
        }

        guardarRespuesta(" Promedio general de cada estudiante", String.valueOf(promedioPorEstudiante));

        // Promedio por asignatura
        cursor = coleccionNotas.find().iterator();
        Map<String, List<Double>> notasPorAsignatura = new HashMap<>();

        try {
            while (cursor.hasNext()) {
                Document nota = cursor.next();
                String codigoAsignatura = nota.get("asignatura", Document.class).getString("codigo");
                Double valorNota = nota.getDouble("nota");
                notasPorAsignatura.computeIfAbsent(codigoAsignatura, k -> new ArrayList<>()).add(valorNota);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Map<String, Double> promedioPorAsignatura = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : notasPorAsignatura.entrySet()) {
            List<Double> notas = entry.getValue();
            double promedio = notas.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            promedioPorAsignatura.put(entry.getKey(), promedio);
        }

        guardarRespuesta(" Promedio por asignatura", String.valueOf(promedioPorAsignatura));

        //Estudiantes con mejor rendimiento (nota media más alta)
        double maxPromedio = promedioPorEstudiante.values().stream().max(Double::compare).orElse(Double.MIN_VALUE);
        List<String> estudiantesMejorRendimiento = new ArrayList<>();

        //Estudiantes con bajo rendimiento (nota media más baja)
        double minPromedio = promedioPorEstudiante.values().stream().min(Double::compare).orElse(Double.MAX_VALUE);
        List<String> estudiantesBajoRendimiento = new ArrayList<>();

        promedioPorEstudiante.forEach((nia, promedio) -> {
            if (promedio == maxPromedio) {
                estudiantesMejorRendimiento.add("NIA: " + nia + ", Promedio: " + promedio);
            }
            if (promedio == minPromedio) {
                estudiantesBajoRendimiento.add("NIA: " + nia + ", Promedio: " + promedio);
            }
        });
        guardarRespuesta("Estudiantes con mejor rendimiento", estudiantesMejorRendimiento.toString());
        guardarRespuesta("Estudiantes con bajo rendimiento", estudiantesBajoRendimiento.toString());

        //Instituto con nota media más alta
        Map<String, Double> sumaNotasPorInstituto = new HashMap<>();
        Map<String, Integer> conteoNotasPorInstituto = new HashMap<>();

        FindIterable<Document> alumnos = coleccionAlumnos.find();
        for (Document alumno : alumnos) {
            Document instituto = alumno.get("instituto", Document.class);
            String nifInstituto = instituto.getString("nif");
            String nia = alumno.getString("nia");

            FindIterable<Document> notas = coleccionNotas.find(eq("alumno.nia", nia));
            for (Document nota : notas) {
                Double valorNota = nota.getDouble("nota");
                sumaNotasPorInstituto.merge(nifInstituto, valorNota, Double::sum);
                conteoNotasPorInstituto.merge(nifInstituto, 1, Integer::sum);
            }
        }
        Map<String, Double> promedioPorInstituto = new HashMap<>();
        sumaNotasPorInstituto.forEach((nif, suma) -> {
            double promedio = suma / conteoNotasPorInstituto.get(nif);
            promedioPorInstituto.put(nif, promedio);
        });

        String institutoConMejorPromedio = promedioPorInstituto.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
        Double promedioMaximo = promedioPorInstituto.get(institutoConMejorPromedio);

        guardarRespuesta("Instituto con la nota media más alta", institutoConMejorPromedio + ", promedio: " + promedioMaximo);

        // Instituto con mayor número de suspendidos en AD
        Map<String, Integer> suspendidosPorInstituto = new HashMap<>();
        MongoCursor<Document> cursorNotas = coleccionNotas.find(and(eq("asignatura.codigo", "AD"), lt("nota", 5.0))).iterator();
        try {
            while (cursorNotas.hasNext()) {
                Document nota = cursorNotas.next();
                Document alumno = nota.get("alumno", Document.class);
                String niaAlumno = alumno.getString("nia");

                Document alumnoDoc = coleccionAlumnos.find(eq("nia", niaAlumno)).first();
                if (alumnoDoc != null) {
                    Document instituto = alumnoDoc.get("instituto", Document.class);
                    if (instituto != null) {
                        String nifInstituto = instituto.getString("nif");
                        suspendidosPorInstituto.merge(nifInstituto, 1, Integer::sum);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String institutoConMasSuspendidos = suspendidosPorInstituto.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        guardarRespuesta("Instituto con el mayor número de suspendidos en AD", institutoConMasSuspendidos);

        //Instituto con mayor número de aprobados en BBDD
        Map<String, Integer> aprobadosPorInstituto = new HashMap<>();

        coleccionNotas.find(and(eq("asignatura.codigo", "BBDD"), gte("nota", 5.0))).forEach(nota -> {
            Document alumno = coleccionAlumnos.find(eq("nia", nota.get("alumno", Document.class).getString("nia"))).first();
            if (alumno != null) {
                Document instituto = alumno.get("instituto", Document.class);
                String nifInstituto = instituto.getString("nif");
                aprobadosPorInstituto.merge(nifInstituto, 1, Integer::sum);
            }
        });

        String institutoConMasAprobados = aprobadosPorInstituto.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
        guardarRespuesta("Instituto con el mayor número de aprobados en BBDD", institutoConMasAprobados);

        //¿Cuál es el profesor que mejores notas ha puesto?
        Map<String, Double> sumaNotasPorProfesor = new HashMap<>();
        Map<String, Integer> conteoNotasPorProfesor = new HashMap<>();

        for (Document nota : coleccionNotas.find()) {
            String profesor = nota.getString("profesor");
            Double valorNota = nota.getDouble("nota");
            sumaNotasPorProfesor.merge(profesor, valorNota, Double::sum);
            conteoNotasPorProfesor.merge(profesor, 1, Integer::sum);
        }

        Map<String, Double> promedioNotasPorProfesor = new HashMap<>();
        sumaNotasPorProfesor.forEach((profesor, sumaNotas) ->
                promedioNotasPorProfesor.put(profesor, sumaNotas / conteoNotasPorProfesor.get(profesor)));

        String mejorProfesor = promedioNotasPorProfesor.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        String peorProfesor = promedioNotasPorProfesor.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        guardarRespuesta("¿Cuál es el profesor que mejores notas ha puesto?" , mejorProfesor);
        guardarRespuesta("¿Cuál es el profesor que peores notas ha puesto?" , peorProfesor);

      //Asignatura con mayor porcentaje de aprobados
        // esta no la consigo hacer antonio
    }

    private static void guardarListasEnMongo(List<Alumno> alumnosMongo, List<Asignatura> asignaturasCSV, List<Nota> notasCSV, List<InstitutoEntity> institutosSQL) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("Centro");

            MongoCollection<org.bson.Document> coleccionAlumnos = database.getCollection("alumnos");
            MongoCollection<org.bson.Document> coleccionAsignaturas = database.getCollection("asignaturas");
            MongoCollection<Document> coleccionNotas = database.getCollection("notas");
            MongoCollection<Document> coleccionInstitutos = database.getCollection("institutos");

            for (Alumno alumno : alumnosMongo) {
                Document docAlumno = new Document("nia", alumno.getNia())
                        .append("nombre", alumno.getNombre())
                        .append("direccion", alumno.getDireccion())
                        .append("edad", alumno.getEdad())
                        .append("email", alumno.getEmail())
                        .append("instituto", new Document("nif", alumno.getInstituto().getNif())
                                .append("nombre", alumno.getInstituto().getNombre())
                                .append("ciudad", alumno.getInstituto().getCiudad()));
                coleccionAlumnos.insertOne(docAlumno);
            }

            for (Asignatura asignatura : asignaturasCSV) {
                Document docAsignatura = new Document("codigo", asignatura.getCodigo())
                        .append("profesores", asignatura.getProfesores());
                coleccionAsignaturas.insertOne(docAsignatura);
            }

            for (Nota nota : notasCSV) {
                Document docNota = new Document("alumno", new Document("nia", nota.getAlumno().getNia())
                        .append("nombre", nota.getAlumno().getNombre()))
                        .append("asignatura", new Document("codigo", nota.getAsignatura().getCodigo()))
                        .append("profesor", nota.getProfesor())
                        .append("nota", nota.getNota());
                coleccionNotas.insertOne(docNota);
            }

            for (InstitutoEntity instituto : institutosSQL) {
                Document docInstituto = new Document("nif", instituto.getNif())
                        .append("nombre", instituto.getNombre())
                        .append("ciudad", instituto.getCiudad());
                coleccionInstitutos.insertOne(docInstituto);
            }
        }
    }

    public static void guardarRespuesta(String pregunta, String respuesta) {
        String archivo = "respuestas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(pregunta + ": \n");
            writer.write(respuesta + "\n");
            writer.write("--------------------------------------------------\n");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static InstitutoEntity encontrarInstitutoPorNif(String nif, List<InstitutoEntity> institutos) {
        for (InstitutoEntity instituto : institutos) {
            if (instituto.getNif().equalsIgnoreCase(nif)) {
                return instituto;
            }
        }
        return null;
    }

}
