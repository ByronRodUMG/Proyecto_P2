package umg.edu.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import umg.edu.entidad.EstudiantesEntity;

import java.util.List;

public class EstudiantesDAO {
    private final Session session;

    public EstudiantesDAO(Session session) {
        this.session = session;
    }

    public void guardarEstudiante(EstudiantesEntity estudiante) {
        session.beginTransaction();
        session.save(estudiante);
        session.getTransaction().commit();
    }

    public EstudiantesEntity obtenerEstudiantePorId(int id) {
        return session.get(EstudiantesEntity.class, id);
    }

    public void actualizarEstudiante(EstudiantesEntity estudiante) {
        session.beginTransaction();
        session.update(estudiante);
        session.getTransaction().commit();
    }

    public void eliminarEstudiante(EstudiantesEntity estudiante) {
        session.beginTransaction();
        session.delete(estudiante);
        session.getTransaction().commit();
    }

    public List<EstudiantesEntity> obtenerTodosLosEstudiantes() {
        Query<EstudiantesEntity> query = session.createQuery("FROM EstudiantesEntity", EstudiantesEntity.class);
        return query.list();
    }
}
