package umg.edu.entidad;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usuarios", schema = "public", catalog = "proyecto")
public class UsuariosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic
    @Column(name = "contrasena_hash")
    private String contrasenaHash;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuariosEntity that = (UsuariosEntity) o;
        return idUsuario == that.idUsuario && Objects.equals(nombreUsuario, that.nombreUsuario) && Objects.equals(contrasenaHash, that.contrasenaHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombreUsuario, contrasenaHash);
    }

    @Override
    public String toString() {
        return "Usuario ID: " + idUsuario +
                ", Nombre de Usuario: " + nombreUsuario +
                ", Contraseña (SHA-256): " + contrasenaHash;
    }
}
