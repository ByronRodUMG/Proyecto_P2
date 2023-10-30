package umg.edu.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import umg.edu.entidad.CursosEntity;

import java.util.List;

public class CursosDAO {
    private final Session session;

    public CursosDAO(Session session) {
        this.session = session;
    }

    public void guardarCurso(CursosEntity curso) {
        session.beginTransaction();
        session.save(curso);
        session.getTransaction().commit();
    }

    public CursosEntity obtenerCursoPorId(int id) {
        return session.get(CursosEntity.class, id);
    }

    public void actualizarCurso(CursosEntity curso) {
        session.beginTransaction();
        session.update(curso);
        session.getTransaction().commit();
    }

    public void eliminarCurso(CursosEntity curso) {
        session.beginTransaction();
        session.delete(curso);
        session.getTransaction().commit();
    }

    public List<CursosEntity> obtenerTodosLosCursos() {
        Query<CursosEntity> query = session.createQuery("FROM CursosEntity", CursosEntity.class);
        return query.list();
    }
}
