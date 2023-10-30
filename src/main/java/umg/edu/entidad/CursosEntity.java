package umg.edu.entidad;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cursos", schema = "public", catalog = "proyecto")
public class CursosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_curso")
    private int idCurso;
    @Basic
    @Column(name = "nombre_curso")
    private String nombreCurso;
    @Basic
    @Column(name = "profesor")
    private String profesor;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursosEntity that = (CursosEntity) o;
        return idCurso == that.idCurso && Objects.equals(nombreCurso, that.nombreCurso) && Objects.equals(profesor, that.profesor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurso, nombreCurso, profesor);
    }

    @Override
    public String toString() {
        return "Curso ID: " + idCurso +
                ", Nombre del Curso: " + nombreCurso +
                ", Profesor: " + profesor;
    }
}
