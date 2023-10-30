package umg.edu.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import umg.edu.entidad.InscripcionesEntity;

import java.util.List;

public class InscripcionesDAO {
    private final Session session;

    public InscripcionesDAO(Session session) {
        this.session = session;
    }

    public void guardarInscripcion(InscripcionesEntity inscripcion) {
        session.beginTransaction();
        session.save(inscripcion);
        session.getTransaction().commit();
    }

    public InscripcionesEntity obtenerInscripcionPorId(int id) {
        return session.get(InscripcionesEntity.class, id);
    }

    public void actualizarInscripcion(InscripcionesEntity inscripcion) {
        session.beginTransaction();
        session.update(inscripcion);
        session.getTransaction().commit();
    }

    public void eliminarInscripcion(InscripcionesEntity inscripcion) {
        session.beginTransaction();
        session.delete(inscripcion);
        session.getTransaction().commit();
    }

    public List<InscripcionesEntity> obtenerTodasLasInscripciones() {
        Query<InscripcionesEntity> query = session.createQuery("FROM InscripcionesEntity", InscripcionesEntity.class);
        return query.list();
    }
}

