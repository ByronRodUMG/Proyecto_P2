package umg.edu.ui;

import umg.edu.dao.UsuariosDAO;
import umg.edu.entidad.UsuariosEntity;

import javax.swing.*;
import java.awt.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import umg.edu.interfaz.LoginListener;

public class Login {
    private JTabbedPane login;
    private JTextField tfUser;
    private JPasswordField pfPass;
    private JButton ingresarButton;
    private JTextField tfNewUser;
    private JPasswordField pfNewPass;
    private JButton registrarmeButton;
    private LoginListener loginListener;

    Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.openSession();

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public Container getContainer() {
        return login;
    }

    public Login() {
        ingresarButton.addActionListener(e -> {
            String usuario = tfUser.getText();
            String contrasena = new String(pfPass.getPassword());

            UsuariosDAO usuariosDAO = new UsuariosDAO(session);

            // Buscar al usuario en la base de datos por nombre de usuario
            UsuariosEntity usuarioBD = usuariosDAO.obtenerUsuarioPorNombre(usuario);

            if (usuarioBD != null) {
                // Obtener la contraseña encriptada del usuario almacenada en la BD
                String contrasenaEncriptadaBD = usuarioBD.getContrasenaHash();

                // Encriptar la contraseña ingresada en el campo y compararla con la de la BD
                String contrasenaEncriptada = UsuariosDAO.convertirASha256(contrasena);
                if (contrasenaEncriptada.equals(contrasenaEncriptadaBD)) {
                    JOptionPane.showMessageDialog(login, "Inicio de sesión exitoso");
                    loginListener.loginExitoso();
                } else {
                    // Contraseña incorrecta
                    JOptionPane.showMessageDialog(login, "Contraseña incorrecta");
                    pfPass.setText(""); // Limpiar el campo de contraseña
                }
            } else {
                // El usuario no existe en la base de datos
                JOptionPane.showMessageDialog(login, "Usuario no encontrado");
                tfUser.setText(""); // Limpiar el campo de usuario
                pfPass.setText("");
            }
        });

        registrarmeButton.addActionListener(e -> {
            String nuevoUsuario = tfNewUser.getText();
            String nuevaContrasena = new String(pfNewPass.getPassword());

            UsuariosDAO usuariosDAO = new UsuariosDAO(session);

            UsuariosEntity nuevoUsuarioEntity = new UsuariosEntity();
            nuevoUsuarioEntity.setNombreUsuario(nuevoUsuario);

            // Guardar el nuevo usuario en la base de datos
            usuariosDAO.guardarUsuario(nuevoUsuarioEntity, nuevaContrasena);

            JOptionPane.showMessageDialog(login, "Nuevo usuario registrado");
        });
    }
}
