package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {
    public JPanel login;
    private JPasswordField passwordtxt;
    private JTextField logintxt;
    private JButton OKButton;

    public login() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/estudiantes2024A";
                String username = "root";
                String password = "123456";

                try {
                    String parametro1 = logintxt.getText();
                    String parametro2 = new String(passwordtxt.getPassword());

                    Connection con = DriverManager.getConnection(url, username, password);
                    System.out.println("Conexión exitosa con la base de datos.");

                    String query = "SELECT * FROM estudiantes WHERE usuario = ? AND password = ?";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, parametro1);
                    pstmt.setString(2, parametro2);

                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        String nombreUsuario = rs.getString("nombre");
                        Bienvenido bienvenida1 = new Bienvenido(nombreUsuario);
                        bienvenida1.setVisible(true);

                        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(login);
                        if (parentFrame != null) {
                            parentFrame.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}