package org.example;

import org.example.hibernate.AlumnoEntity;
import org.example.hibernate.HibernateUtil;
import org.example.hibernate.InstitutoEntity;
import org.example.model.Alumno;
import org.example.model.Asignatura;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        List<Alumno> alumnosCSV = new ArrayList<>();
        List<Asignatura> asignaturasCSV = new ArrayList<>();
        List<AlumnoEntity> alumnosSQL = new ArrayList<>();
        List<InstitutoEntity> institutosSQL = new ArrayList<>();

        LeerCSV csvReader = new LeerCSV();
        alumnosCSV = csvReader.leerAlumnosDesdeCSV("listado_alumnos.csv");
        asignaturasCSV = csvReader.LeerAsignaturas("listado_alumnos.csv");

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

    }
}
