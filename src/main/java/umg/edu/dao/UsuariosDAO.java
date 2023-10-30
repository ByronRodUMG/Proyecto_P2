package umg.edu.dao;

import org.hibernate.Session;
import umg.edu.entidad.UsuariosEntity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UsuariosDAO {
    private final Session session;

    public UsuariosDAO(Session session) {
        this.session = session;
    }

    public static String convertirASha256(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(texto.getBytes(StandardCharsets.UTF_8));

            // Convertir el hash a una representación hexadecimal
            StringBuilder hexHash = new StringBuilder();
            for (byte b : hash) {
                hexHash.append(String.format("%02x", b));
            }
            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void guardarUsuario(UsuariosEntity usuario, String contrasena) {
        String contrasenaEncriptada = convertirASha256(contrasena);
        usuario.setContrasenaHash(contrasenaEncriptada);

        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
    }

    public void actualizarContrasena(UsuariosEntity usuario, String nuevaContrasena) {
        String nuevaContrasenaEncriptada = convertirASha256(nuevaContrasena);
        usuario.setContrasenaHash(nuevaContrasenaEncriptada);

        session.beginTransaction();
        session.update(usuario);
        session.getTransaction().commit();
    }

    public void eliminarUsuario(UsuariosEntity usuario) {
        session.beginTransaction();
        session.delete(usuario);
        session.getTransaction().commit();
    }

    public UsuariosEntity obtenerUsuarioPorId(int id) {
        return session.find(UsuariosEntity.class, id);
    }

    public UsuariosEntity obtenerUsuarioPorNombre(String usuario) {
        return session.createQuery("FROM UsuariosEntity WHERE nombreUsuario = :usuario", UsuariosEntity.class)
                .setParameter("usuario", usuario)
                .uniqueResult();
    }

    public List<UsuariosEntity> obtenerTodosLosUsuarios() {
        return session.createQuery("FROM UsuariosEntity", UsuariosEntity.class).list();
    }


}
