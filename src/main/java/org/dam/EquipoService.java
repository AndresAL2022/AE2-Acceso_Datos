package org.dam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class EquipoService {

    private static SessionFactory sessionFactory;

    static {

        sessionFactory = new Configuration().configure().buildSessionFactory();

    }

    public void crearEquipo(String nombre, String ciudad, int ligaId) {

        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setCiudad(ciudad);
        equipo.setIdLiga(ligaId);
        saveEquipo(equipo);

    }

    public void saveEquipo(Equipo equipo) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(equipo);

        transaction.commit();
        session.close();

    }

    public void modificarEquipo(int id, String nombre, int ligaId) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Equipo equipo = session.get(Equipo.class, id);
        equipo.setNombre(nombre);
        equipo.setIdLiga(ligaId);

        session.update(equipo);

        transaction.commit();
        session.close();

    }

    public void eliminarEquipo(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Equipo equipo = session.get(Equipo.class, id);
        if (equipo != null) {

            PartidoService partidoService = new PartidoService();
            partidoService.deletePartidosByEquipoId(id);

            session.delete(equipo);
        }

        transaction.commit();
        session.close();

    }

    public static List<Equipo> listarEquipos() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Equipo> equipo = session.createQuery("from Equipo", Equipo.class).list();

        transaction.commit();
        session.close();

        return equipo;

    }

    public void deleteEquiposByLigaId(int ligaId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Equipo where id_liga = :ligaId");
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
}
