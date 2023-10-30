package umg.edu;

import umg.edu.interfaz.LoginListener;
import umg.edu.ui.Login;
import umg.edu.ui.Menu;

import javax.swing.*;

public class Main implements LoginListener {
    private JFrame frame;

    public Main() {
        frame = new JFrame("Inicio de Sesión");

        Login login = new Login();
        login.setLoginListener(this);

        frame.setContentPane(login.getContainer());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }

    @Override
    public void loginExitoso() {
        frame.dispose(); // Cierra el JFrame actual

        // Abre el nuevo JFrame de la clase Menu.java
        JFrame menuFrame = new JFrame("Menú Principal");
        menuFrame.setContentPane(new Menu().getContainer());
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(800, 600);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }
}
