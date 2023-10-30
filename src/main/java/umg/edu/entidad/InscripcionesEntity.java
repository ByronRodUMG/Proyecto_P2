package umg.edu.entidad;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "inscripciones", schema = "public", catalog = "proyecto")
public class InscripcionesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_inscripcion")
    private int idInscripcion;
    @Basic
    @Column(name = "id_estudiante")
    private Integer idEstudiante;
    @Basic
    @Column(name = "id_curso")
    private Integer idCurso;
    @Basic
    @Column(name = "fecha_inscripcion")
    private Date fechaInscripcion;

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionesEntity that = (InscripcionesEntity) o;
        return idInscripcion == that.idInscripcion && Objects.equals(idEstudiante, that.idEstudiante) && Objects.equals(idCurso, that.idCurso) && Objects.equals(fechaInscripcion, that.fechaInscripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInscripcion, idEstudiante, idCurso, fechaInscripcion);
    }

    @Override
    public String toString() {
        return "Inscripción ID: " + idInscripcion +
                ", ID del Estudiante: " + idEstudiante +
                ", ID del Curso: " + idCurso +
                ", Fecha de Inscripción: " + fechaInscripcion;
    }
}
