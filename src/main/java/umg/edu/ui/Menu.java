package umg.edu.ui;

import umg.edu.dao.*;
import umg.edu.entidad.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Menu {
    private JTabbedPane menu;
    private JPanel jPanelInscripciones;
    private JPanel jPanelEstudiantes;
    private JPanel jPanelCursos;
    private JTabbedPane tpInscripciones;
    private JTabbedPane tpEstudiantes;
    private JTabbedPane tpCursos;

    private JTextField tfLeerInscripciones;
    private JButton btnLeerInscripcionesID;
    private JTextArea taLeerInscripcionesAll;
    private JTextArea taLeerInscripcionesID;
    private JButton btnLeerInscripcionesAll;
    private JList listEstudiantes;
    private JList listCursos;
    private JButton btnCrearInscripcion;
    private JList listInscripciones;
    private JList listEstudiantes2;
    private JList listCursos2;
    private JButton btnActualizarInscripcion;
    private JList listInscripciones2;
    private JButton btnBorrarInscripcion;

    private JTextField tfLeerEstudiantes;
    private JButton btnLeerEstudiantesID;
    private JTextArea taLeerEstudiantesID;
    private JTextArea taLeerEstudiantesAll;
    private JButton btnLeerEstudiantesAll;
    private JTextField tfNombreEstudiante;
    private JTextField tfApellido;
    private JTextField tfEmail;
    private JButton btnCrearEstudiante;
    private JList listEstudiantes3;
    private JTextField tfNuevoNombreEstudiante;
    private JTextField tfNuevoApellido;
    private JTextField tfNuevoEmail;
    private JButton btnActualizarEstudiante;
    private JList listEstudiantes4;
    private JButton btnBorrarEstudiante;

    private JTextField tfLeerCursos;
    private JButton btnLeerCursosID;
    private JTextArea taLeerCursosID;
    private JTextArea taLeerCursosAll;
    private JButton btnLeerCursosAll;
    private JButton btnCrearCurso;
    private JTextField tfNombreCurso;
    private JTextField tfNombreProfesor;
    private JList listCursos3;
    private JTextField tfNuevoNombreCurso;
    private JTextField tfNuevoNombreProfesor;
    private JButton btnActualizarCurso;
    private JList listCursos4;
    private JButton btnBorrarCurso;

    Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.openSession();

    public Container getContainer() {
        return menu;
    }

    public Menu() {

        // Bloque de Estudiantes

        btnCrearEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = tfNombreEstudiante.getText();
                String apellido = tfApellido.getText();
                String email = tfEmail.getText();

                // Verifica si algún campo está vacío
                if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(menu, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Si todos los campos están completos, guarda el estudiante
                    EstudiantesEntity nuevoEstudiante = new EstudiantesEntity();
                    nuevoEstudiante.setNombre(nombre);
                    nuevoEstudiante.setApellido(apellido);
                    nuevoEstudiante.setEmail(email);

                    EstudiantesDAO estudiantesDAO = new EstudiantesDAO(session);
                    estudiantesDAO.guardarEstudiante(nuevoEstudiante);

                    // Mensaje de éxito
                    JOptionPane.showMessageDialog(menu, "Estudiante creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnLeerEstudiantesID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = tfLeerEstudiantes.getText();

                // Verifica si el campo de texto no está vacío y si contiene un número válido
                if (idText.isEmpty() || !idText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(menu, "Por favor, ingrese un ID válido.", "ID inválida", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id = Integer.parseInt(idText);

                    EstudiantesDAO estudiantesDAO = new EstudiantesDAO(session);
                    EstudiantesEntity estudiante = estudiantesDAO.obtenerEstudiantePorId(id);

                    if (estudiante != null) {
                        // Mostrar la información del estudiante en taLeerEstudiantesID
                        taLeerEstudiantesID.setText("ID: " + estudiante.getIdEstudiante() + "\n" +
                                "Nombre: " + estudiante.getNombre() + "\n" +
                                "Apellido: " + estudiante.getApellido() + "\n" +
                                "Email: " + estudiante.getEmail());
                    } else {
                        // Mostrar mensaje de error si no se encuentra el estudiante
                        JOptionPane.showMessageDialog(menu, "No se encontró un estudiante con el ID proporcionado.", "Estudiante no encontrado", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnLeerEstudiantesAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudiantesDAO estudiantesDAO = new EstudiantesDAO(session);
                List<EstudiantesEntity> estudiantes = estudiantesDAO.obtenerTodosLosEstudiantes();

                if (estudiantes != null && !estudiantes.isEmpty()) {
                    StringBuilder estudiantesInfo = new StringBuilder();
                    for (EstudiantesEntity estudiante : estudiantes) {
                        estudiantesInfo.append("ID: ").append(estudiante.getIdEstudiante()).append("\n")
                                .append("Nombre: ").append(estudiante.getNombre()).append("\n")
                                .append("Apellido: ").append(estudiante.getApellido()).append("\n")
                                .append("Email: ").append(estudiante.getEmail()).append("\n\n");
                    }
                    taLeerEstudiantesAll.setText(estudiantesInfo.toString());
                } else {
                    // Mostrar mensaje si no hay estudiantes en la tabla
                    taLeerEstudiantesAll.setText("No se encontraron estudiantes en la base de datos.");
                }
            }
        });

        listEstudiantes3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarEstudiantes(listEstudiantes3);
                }
            }
        });

        listEstudiantes4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarEstudiantes(listEstudiantes4);
                }
            }
        });

        listEstudiantes3.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                EstudiantesEntity selectedStudent = (EstudiantesEntity) listEstudiantes3.getSelectedValue();
            }
        });

        listEstudiantes4.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                EstudiantesEntity selectedStudent = (EstudiantesEntity) listEstudiantes3.getSelectedValue();
            }
        });

        btnActualizarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listEstudiantes3.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(menu, "Por favor, seleccione un estudiante.", "Estudiante no seleccionado", JOptionPane.WARNING_MESSAGE);
                } else {
                    String nuevoNombre = tfNuevoNombreEstudiante.getText();
                    String nuevoApellido = tfNuevoApellido.getText();
                    String nuevoEmail = tfNuevoEmail.getText();

                    if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoEmail.isEmpty()) {
                        JOptionPane.showMessageDialog(menu, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                    } else {
                        EstudiantesEntity estudianteSeleccionado = (EstudiantesEntity) listEstudiantes3.getSelectedValue();
                        estudianteSeleccionado.setNombre(nuevoNombre);
                        estudianteSeleccionado.setApellido(nuevoApellido);
                        estudianteSeleccionado.setEmail(nuevoEmail);

                        EstudiantesDAO estudiantesDAO = new EstudiantesDAO(session);
                        estudiantesDAO.actualizarEstudiante(estudianteSeleccionado);

                        // Mensaje de éxito
                        JOptionPane.showMessageDialog(menu, "Estudiante actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        mostrarEstudiantes(listEstudiantes3);
                    }
                }
            }
        });

        btnBorrarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listEstudiantes4.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(menu, "Por favor, seleccione un estudiante.", "Estudiante no seleccionado", JOptionPane.WARNING_MESSAGE);
                } else {
                    EstudiantesEntity estudianteSeleccionado = (EstudiantesEntity) listEstudiantes4.getSelectedValue();

                    EstudiantesDAO estudiantesDAO = new EstudiantesDAO(session);
                    estudiantesDAO.eliminarEstudiante(estudianteSeleccionado);

                    // Mensaje de éxito
                    JOptionPane.showMessageDialog(menu, "Estudiante eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    mostrarEstudiantes(listEstudiantes4);
                }
            }
        });

        // Bloque de Cursos

        btnCrearCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = tfNombreCurso.getText();
                String profesor = tfNombreProfesor.getText();

                // Verifica si algún campo está vacío
                if (nombre.isEmpty() || profesor.isEmpty()) {
                    JOptionPane.showMessageDialog(menu, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Si todos los campos están completos, guarda el curso
                    CursosEntity nuevoCurso = new CursosEntity();
                    nuevoCurso.setNombreCurso(nombre);
                    nuevoCurso.setProfesor(profesor);

                    CursosDAO cursosDAO = new CursosDAO(session);
                    cursosDAO.guardarCurso(nuevoCurso);

                    // Mensaje de éxito
                    JOptionPane.showMessageDialog(menu, "Curso creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnLeerCursosID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = tfLeerCursos.getText();

                // Verifica si el campo de texto no está vacío y si contiene un número válido
                if (idText.isEmpty() || !idText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(menu, "Por favor, ingrese un ID válido.", "ID inválida", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id = Integer.parseInt(idText);

                    CursosDAO cursosDAO = new CursosDAO(session);
                    CursosEntity curso = cursosDAO.obtenerCursoPorId(id);

                    if (curso != null) {
                        // Mostrar la información del curso en taLeerCursosID
                        taLeerCursosID.setText("ID: " + curso.getIdCurso() + "\n" +
                                "Nombre: " + curso.getNombreCurso() + "\n" +
                                "Profesor: " + curso.getProfesor());
                    } else {
                        // Mostrar mensaje de error si no se encuentra el curso
                        JOptionPane.showMessageDialog(menu, "No se encontró un curso con el ID proporcionado.", "Curso no encontrado", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnLeerCursosAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursosDAO cursosDAO = new CursosDAO(session);
                List<CursosEntity> cursos = cursosDAO.obtenerTodosLosCursos();

                if (cursos != null && !cursos.isEmpty()) {
                    StringBuilder cursosInfo = new StringBuilder();
                    for (CursosEntity curso : cursos) {
                        cursosInfo.append("ID: ").append(curso.getIdCurso()).append("\n")
                                .append("Nombre: ").append(curso.getNombreCurso()).append("\n")
                                .append("Profesor: ").append(curso.getProfesor()).append("\n\n");
                    }
                    taLeerCursosAll.setText(cursosInfo.toString());
                } else {
                    // Mostrar mensaje si no hay cursos en la tabla
                    taLeerCursosAll.setText("No se encontraron cursos en la base de datos.");
                }
            }
        });

        listCursos3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarCursos(listCursos3);
                }
            }
        });

        listCursos4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarCursos(listCursos4);
                }
            }
        });

        listCursos3.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                CursosEntity selectedCourse = (CursosEntity) listCursos3.getSelectedValue();
            }
        });

        listCursos4.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                CursosEntity selectedCourse = (CursosEntity) listCursos4.getSelectedValue();
            }
        });

        btnActualizarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listCursos3.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(menu, "Por favor, seleccione un curso.", "Curso no seleccionado", JOptionPane.WARNING_MESSAGE);
                } else {
                    String nuevoNombre = tfNuevoNombreCurso.getText();
                    String nuevoProfesor = tfNuevoNombreProfesor.getText();

                    if (nuevoNombre.isEmpty() || nuevoProfesor.isEmpty()) {
                        JOptionPane.showMessageDialog(menu, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                    } else {
                        CursosEntity cursoSeleccionado = (CursosEntity) listCursos3.getSelectedValue();
                        cursoSeleccionado.setNombreCurso(nuevoNombre);
                        cursoSeleccionado.setProfesor(nuevoProfesor);

                        CursosDAO cursosDAO = new CursosDAO(session);
                        cursosDAO.actualizarCurso(cursoSeleccionado);

                        // Mensaje de éxito
                        JOptionPane.showMessageDialog(menu, "Curso actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        mostrarCursos(listCursos3);
                    }
                }
            }
        });

        btnBorrarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listCursos4.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(menu, "Por favor, seleccione un curso.", "Curso no seleccionado", JOptionPane.WARNING_MESSAGE);
                } else {
                    CursosEntity cursoSeleccionado = (CursosEntity) listCursos4.getSelectedValue();

                    CursosDAO cursosDAO = new CursosDAO(session);
                    cursosDAO.eliminarCurso(cursoSeleccionado);

                    // Mensaje de éxito
                    JOptionPane.showMessageDialog(menu, "Curso eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    mostrarCursos(listCursos4);
                }
            }
        });

        // Bloque de Inscripciones

        listEstudiantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarEstudiantes(listEstudiantes);
                }
            }
        });

        listCursos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarCursos(listCursos);
                }
            }
        });

        listEstudiantes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                EstudiantesEntity selectedStudent = (EstudiantesEntity) listEstudiantes.getSelectedValue();
            }
        });

        listCursos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                CursosEntity selectedCourse = (CursosEntity) listCursos.getSelectedValue();
            }
        });

        btnCrearInscripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los IDs de los elementos seleccionados en las listas de estudiantes y cursos
                EstudiantesEntity estudianteSeleccionado = (EstudiantesEntity) listEstudiantes.getSelectedValue();
                CursosEntity cursoSeleccionado = (CursosEntity) listCursos.getSelectedValue();

                // Verificar si se han seleccionado datos en ambas listas
                if (estudianteSeleccionado != null && cursoSeleccionado != null) {
                    int idEstudiante = estudianteSeleccionado.getIdEstudiante(); // Obtener el ID del estudiante
                    int idCurso = cursoSeleccionado.getIdCurso(); // Obtener el ID del curso

                    // Crear una nueva InscripcionesEntity con los IDs seleccionados
                    InscripcionesEntity nuevaInscripcion = new InscripcionesEntity();
                    nuevaInscripcion.setIdEstudiante(idEstudiante);
                    nuevaInscripcion.setIdCurso(idCurso);

                    Date fechaActual = new Date();
                    nuevaInscripcion.setFechaInscripcion(fechaActual);

                    // Guardar la nueva inscripción en la base de datos utilizando el DAO
                    InscripcionesDAO inscripcionesDAO = new InscripcionesDAO(session);
                    inscripcionesDAO.guardarInscripcion(nuevaInscripcion);

                    // Mostrar un mensaje de éxito
                    JOptionPane.showMessageDialog(menu, "Inscripción guardada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Si no se han seleccionado datos en ambas listas, muestra un mensaje de error
                    JOptionPane.showMessageDialog(menu, "Por favor, seleccione un estudiante y un curso para la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLeerInscripcionesID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = tfLeerInscripciones.getText();

                // Verifica si el campo de texto no está vacío y si contiene un número válido
                if (idText.isEmpty() || !idText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(menu, "Por favor, ingrese un ID válido.", "ID inválida", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id = Integer.parseInt(idText);

                    InscripcionesDAO inscripcionesDAO = new InscripcionesDAO(session);
                    InscripcionesEntity inscripcion = inscripcionesDAO.obtenerInscripcionPorId(id);

                    if (inscripcion != null) {
                        // Obteniendo detalles de Estudiante y Curso a partir de sus respectivos IDs
                        EstudiantesDAO estudiantesDAO = new EstudiantesDAO(session); // Suponiendo que existe un DAO para estudiantes
                        EstudiantesEntity estudiante = estudiantesDAO.obtenerEstudiantePorId(inscripcion.getIdEstudiante());

                        CursosDAO cursosDAO = new CursosDAO(session); // Suponiendo que existe un DAO para cursos
                        CursosEntity curso = cursosDAO.obtenerCursoPorId(inscripcion.getIdCurso());

                        // Mostrar la información de la inscripción junto con los detalles del estudiante y curso
                        if (estudiante != null && curso != null) {
                            taLeerInscripcionesID.setText("ID: " + inscripcion.getIdInscripcion() + "\n" +
                                    "ID del Estudiante: " + inscripcion.getIdEstudiante() + "\n" +
                                    "Nombre del Estudiante: " + estudiante.getNombre() + "\n" +
                                    // Mostrar otros detalles del estudiante que desees
                                    "ID del Curso: " + inscripcion.getIdCurso() + "\n" +
                                    "Nombre del Curso: " + curso.getNombreCurso() + "\n" +
                                    // Mostrar otros detalles del curso que desees
                                    "Fecha de Inscripción: " + inscripcion.getFechaInscripcion());
                        } else {
                            // Mostrar mensaje si no se encuentran los detalles del estudiante o curso
                            JOptionPane.showMessageDialog(menu, "No se encontró información completa para la inscripción.", "Información faltante", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        // Mostrar mensaje si no se encuentra la inscripción
                        JOptionPane.showMessageDialog(menu, "No se encontró una inscripción con el ID proporcionado.", "Inscripción no encontrada", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnLeerInscripcionesAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionesDAO inscripcionesDAO = new InscripcionesDAO(session);
                List<InscripcionesEntity> inscripciones = inscripcionesDAO.obtenerTodasLasInscripciones();

                if (inscripciones != null && !inscripciones.isEmpty()) {
                    StringBuilder inscripcionesInfo = new StringBuilder();
                    EstudiantesDAO estudiantesDAO = new EstudiantesDAO(session); // Suponiendo que existe un DAO para estudiantes
                    CursosDAO cursosDAO = new CursosDAO(session); // Suponiendo que existe un DAO para cursos

                    for (InscripcionesEntity inscripcion : inscripciones) {
                        EstudiantesEntity estudiante = estudiantesDAO.obtenerEstudiantePorId(inscripcion.getIdEstudiante());
                        CursosEntity curso = cursosDAO.obtenerCursoPorId(inscripcion.getIdCurso());

                        inscripcionesInfo.append("ID de Inscripción: ").append(inscripcion.getIdInscripcion()).append("\n")
                                .append("ID del Estudiante: ").append(inscripcion.getIdEstudiante()).append("\n")
                                .append("Nombre del Estudiante: ").append(estudiante.getNombre()).append("\n")
                                .append("ID del Curso: ").append(inscripcion.getIdCurso()).append("\n")
                                .append("Nombre del Curso: ").append(curso.getNombreCurso()).append("\n")
                                .append("Fecha de Inscripción: ").append(inscripcion.getFechaInscripcion()).append("\n\n");
                    }

                    taLeerInscripcionesAll.setText(inscripcionesInfo.toString());
                } else {
                    // Mostrar mensaje si no hay inscripciones en la tabla
                    taLeerInscripcionesAll.setText("No se encontraron inscripciones en la base de datos.");
                }
            }
        });

        listInscripciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarInscripciones(listInscripciones);
                }
            }
        });

        listEstudiantes2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarEstudiantes(listEstudiantes2);
                }
            }
        });

        listCursos2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarCursos(listCursos2);
                }
            }
        });

        listInscripciones.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                InscripcionesEntity selectedInscription = (InscripcionesEntity) listInscripciones.getSelectedValue();
            }
        });

        listEstudiantes2.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                EstudiantesEntity selectedStudent = (EstudiantesEntity) listEstudiantes2.getSelectedValue();
            }
        });

        listCursos2.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                CursosEntity selectedCourse = (CursosEntity) listCursos2.getSelectedValue();
            }
        });

        btnActualizarInscripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listInscripciones.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(menu, "Por favor, seleccione una inscripción.", "Inscripción no seleccionada", JOptionPane.WARNING_MESSAGE);
                } else {
                    EstudiantesEntity estudianteSeleccionado = (EstudiantesEntity) listEstudiantes2.getSelectedValue();
                    CursosEntity cursoSeleccionado = (CursosEntity) listCursos2.getSelectedValue();

                    if (estudianteSeleccionado == null || cursoSeleccionado == null) {
                        JOptionPane.showMessageDialog(menu, "Por favor, seleccione un estudiante y un curso para la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        InscripcionesEntity inscripcionSeleccionada = (InscripcionesEntity) listInscripciones.getSelectedValue();
                        inscripcionSeleccionada.setIdEstudiante(estudianteSeleccionado.getIdEstudiante());
                        inscripcionSeleccionada.setIdCurso(cursoSeleccionado.getIdCurso());

                        Date fechaActual = new Date();
                        inscripcionSeleccionada.setFechaInscripcion(fechaActual);

                        InscripcionesDAO inscripcionesDAO = new InscripcionesDAO(session);
                        inscripcionesDAO.actualizarInscripcion(inscripcionSeleccionada);

                        // Mensaje de éxito
                        JOptionPane.showMessageDialog(menu, "Inscripción actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        mostrarInscripciones(listInscripciones);
                        mostrarEstudiantes(listEstudiantes2);
                        mostrarCursos(listCursos2);
                    }
                }
            }
        });

        listInscripciones2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarInscripciones(listInscripciones2);
                }
            }
        });

        listInscripciones2.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                InscripcionesEntity selectedInscription = (InscripcionesEntity) listInscripciones2.getSelectedValue();
            }
        });

        btnBorrarInscripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listInscripciones2.getSelectedIndex();

                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(menu, "Por favor, seleccione una inscripción.", "Inscripción no seleccionada", JOptionPane.WARNING_MESSAGE);
                } else {
                    InscripcionesEntity inscripcionSeleccionada = (InscripcionesEntity) listInscripciones2.getSelectedValue();

                    InscripcionesDAO inscripcionesDAO = new InscripcionesDAO(session);
                    inscripcionesDAO.eliminarInscripcion(inscripcionSeleccionada);

                    // Mensaje de éxito
                    JOptionPane.showMessageDialog(menu, "Inscripción eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    mostrarInscripciones(listInscripciones2);
                }
            }
        });
    }

    private void mostrarEstudiantes(JList listEstudiantes) {
        EstudiantesDAO estudiantesDAO = new EstudiantesDAO(session);
        List<EstudiantesEntity> estudiantes = estudiantesDAO.obtenerTodosLosEstudiantes();
        DefaultListModel<EstudiantesEntity> model = new DefaultListModel<>();
        for (EstudiantesEntity estudiante : estudiantes) {
            model.addElement(estudiante);
        }
        listEstudiantes.setModel(model);
    }

    private void mostrarCursos(JList listCursos) {
        CursosDAO cursosDAO = new CursosDAO(session);
        List<CursosEntity> cursos = cursosDAO.obtenerTodosLosCursos();
        DefaultListModel<CursosEntity> model = new DefaultListModel<>();
        for (CursosEntity curso : cursos) {
            model.addElement(curso);
        }
        listCursos.setModel(model);
    }

    private void mostrarInscripciones(JList listInscripciones) {
        InscripcionesDAO inscripcionesDAO = new InscripcionesDAO(session);
        List<InscripcionesEntity> inscripciones = inscripcionesDAO.obtenerTodasLasInscripciones();
        DefaultListModel<InscripcionesEntity> model = new DefaultListModel<>();
        for (InscripcionesEntity inscripcion : inscripciones) {
            model.addElement(inscripcion);
        }
        listInscripciones.setModel(model);
    }

    // Metodo para mostrar los datos de las inscripciones en la lista
    // Retorna un error, queda como archivo de referencia
    /*private void mostrarInscripciones(JList listInscripciones) {
        InscripcionesDAO inscripcionesDAO = new InscripcionesDAO(session);
        List<InscripcionesEntity> inscripciones = inscripcionesDAO.obtenerTodasLasInscripciones();

        DefaultListModel<String> model = new DefaultListModel<>();
        EstudiantesDAO estudiantesDAO = new EstudiantesDAO(session);
        CursosDAO cursosDAO = new CursosDAO(session);

        for (InscripcionesEntity inscripcion : inscripciones) {
            String nombreEstudiante = "";
            String nombreCurso = "";

            EstudiantesEntity estudiante = estudiantesDAO.obtenerEstudiantePorId(inscripcion.getIdEstudiante());
            CursosEntity curso = cursosDAO.obtenerCursoPorId(inscripcion.getIdCurso());

            if (estudiante != null) {
                nombreEstudiante = estudiante.getNombre(); // Obtener el nombre del estudiante
            }

            if (curso != null) {
                nombreCurso = curso.getNombreCurso(); // Obtener el nombre del curso
            }

            String inscriptionDetails = "ID: " + inscripcion.getIdInscripcion() +
                    " - Estudiante: " + nombreEstudiante +
                    " - Curso: " + nombreCurso;

            model.addElement(inscriptionDetails);
        }

        listInscripciones.setModel(model);
    }*/

}
