package org.dam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

public class PartidoService {

    public void crearPartido(Date fechaPartido, int golesEquipoLocal, int golesEquipoVisitante, int idEquipoLocal, int idEquipoVisitante, int idLiga) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Partido partido = new Partido();

        partido.setFechaPartido(fechaPartido);
        partido.setGolesEquipoLocal(golesEquipoLocal);
        partido.setGolesEquipoVisitante(golesEquipoVisitante);
        partido.setIdEquipoLocal(session.get(Equipo.class, idEquipoLocal));
        partido.setIdEquipoVisitante(session.get(Equipo.class, idEquipoVisitante));
        partido.setIdLiga(session.get(Liga.class, idLiga));

        session.save(partido);

        transaction.commit();
        session.close();
    }

    public void modificarPartido(int id, Date fechaPartido, int golesEquipoLocal, int golesEquipoVisitante, int idEquipoLocal, int idEquipoVisitante, int idLiga) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Partido partido = session.get(Partido.class, id);

        partido.setFechaPartido(fechaPartido);
        partido.setGolesEquipoLocal(golesEquipoLocal);
        partido.setGolesEquipoVisitante(golesEquipoVisitante);
        partido.setIdEquipoLocal(session.get(Equipo.class, idEquipoLocal));
        partido.setIdEquipoVisitante(session.get(Equipo.class, idEquipoVisitante));
        partido.setIdLiga(session.get(Liga.class, idLiga));

        session.update(partido);

        transaction.commit();
        session.close();
    }

    public void eliminarPartido(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Partido partido = session.get(Partido.class, id);

        if (partido != null) {

            session.delete(partido);

            transaction.commit();

        } else {

            System.out.println("| No existe ningún partido con " + id + ".");

            try {

                Interface.menu_partidos();

            } catch (InterruptedException e) {

                throw new RuntimeException(e);

            }
        }

        session.close();
    }

    public void deletePartidosByLigaId(int ligaId) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Partido where id_liga = :ligaId");
            query.setParameter("ligaId", ligaId);

            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();

            }

            e.printStackTrace();

        }
    }

    public void deletePartidosByEquipoId(int equipoId) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from Partido where id_equipo_local = :equipoId or id_equipo_visitante = :equipoId");
        query.setParameter("equipoId", equipoId);

        query.executeUpdate();

        transaction.commit();
        session.close();

    }

    private static SessionFactory sessionFactory;

    static {

        sessionFactory = new Configuration().configure().buildSessionFactory();

    }

    public static List<Partido> listarPartidos() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Partido> partidos = session.createQuery("from Partido", Partido.class).list();

        transaction.commit();
        session.close();

        return partidos;

    }

    public List<Partido> listarPartidosPorLiga(int ligaId) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Liga liga = session.get(Liga.class, ligaId);
        Query query = session.createQuery("from Partido where idLiga = :liga", Partido.class);
        query.setParameter("liga", liga);

        List<Partido> partidos = query.getResultList();

        transaction.commit();
        session.close();

        return partidos;
    }
}