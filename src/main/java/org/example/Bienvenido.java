package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Bienvenido extends JFrame{
    public JPanel panel;
    public JTable dataTable;
    public JLabel welcomeLabel;

    public Bienvenido(String nombreUsuario) {
        // Configurar el JFrame
        setTitle("Bienvenida");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel y componentes
        panel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel("¡Bienvenido/a, " + nombreUsuario + "!", SwingConstants.CENTER);
        dataTable = new JTable();

        // Agregar componentes al panel
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(dataTable), BorderLayout.CENTER);

        // Agregar el panel al JFrame
        add(panel);

        // Cargar datos en el JTable
        cargarDatos();
    }

    private void cargarDatos() {
        String url = "jdbc:mysql://localhost:3306/estudiantes2024A";
        String username = "root";
        String password = "123456";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM estudiantes";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Modelo para la tabla
            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"Cedula", "Nombre", "B1", "B2"}, 0
            );

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("b1"),
                        rs.getString("b2")
                });
            }

            dataTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

