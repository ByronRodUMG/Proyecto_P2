package umg.edu.entidad;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "estudiantes", schema = "public", catalog = "proyecto")
public class EstudiantesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_estudiante")
    private int idEstudiante;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "apellido")
    private String apellido;
    @Basic
    @Column(name = "email")
    private String email;

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstudiantesEntity that = (EstudiantesEntity) o;
        return idEstudiante == that.idEstudiante && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstudiante, nombre, apellido, email);
    }

    @Override
    public String toString() {
        return "Estudiante ID: " + idEstudiante +
                ", Nombre: " + nombre +
                ", Apellido: " + apellido +
                ", Email: " + email;
    }
}
