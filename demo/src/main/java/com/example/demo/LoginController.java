package main.java.com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private static final String URL_BASE = "jdbc:mysql://localhost:3306/login";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "admin";

    private Connection conexion;

    public LoginController() {
        try {
            conexion = DriverManager.getConnection(URL_BASE, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        try {
            String sql = "SELECT * FROM usuario WHERE username = ? AND password = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario getUsuario(String username) {
        try {
            String sql = "SELECT * FROM usuario WHERE username = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String rut = resultSet.getString("rut");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                return new Usuario(rut, nombre, apellido, username, password);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
