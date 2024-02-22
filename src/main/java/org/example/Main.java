package org.example;

import org.example.hibernate.AlumnoEntity;
import org.example.model.Alumno;
import org.example.model.Asignatura;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        LeerCSV csvReader = new LeerCSV();
        List<Alumno> alumnos = csvReader.leerAlumnosDesdeCSV("listado_alumnos.csv");
        List<Asignatura> asignaturas = csvReader.LeerAsignaturas("listado_alumnos.csv");

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            transaction.commit(); // Comprometer la transacción después de las inserciones

            session.beginTransaction();

            List<AlumnoEntity> alumnosRecogidos = session.createQuery("from AlumnoEntity", AlumnoEntity.class).list();
            for (AlumnoEntity alumno : alumnosRecogidos) {
                System.out.println(alumno); // O cualquier operación que necesites realizar con los alumnos
            }


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        sessionFactory.close();
    }
}
