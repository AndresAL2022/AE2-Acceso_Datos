package org.dam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

public class LigaService {

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void crearLiga(String nombre, Date fechaInicio, Date fechaFin) {

        Liga liga = new Liga();
        liga.setNombre(nombre);
        liga.setFechaInicio(fechaInicio);
        liga.setFechaFin(fechaFin);
        saveLiga(liga);

    }

    public void saveLiga(Liga liga) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(liga);

        transaction.commit();
        session.close();

    }

    public void modificarLiga(int id, String nombre, Date fechaInicio, Date fechaFin) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Liga liga = session.get(Liga.class, id);
        liga.setNombre(nombre);
        liga.setFechaInicio(fechaInicio);
        liga.setFechaFin(fechaFin);

        session.update(liga);

        transaction.commit();
        session.close();

    }

    public void eliminarLiga(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        PartidoService partidoService = new PartidoService();
        partidoService.deletePartidosByLigaId(id);

        EquipoService equipoService = new EquipoService();
        equipoService.deleteEquiposByLigaId(id);

        Liga liga = session.get(Liga.class, id);
        if (liga != null) {
            session.delete(liga);
        }

        transaction.commit();
        session.close();

    }

    public static List<Liga> listarLigas() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Liga> ligas = session.createQuery("from Liga", Liga.class).list();

        transaction.commit();
        session.close();

        return ligas;

    }

    public Liga getLigaById(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Liga liga = session.get(Liga.class, id);

        transaction.commit();
        session.close();

        return liga;

    }
}