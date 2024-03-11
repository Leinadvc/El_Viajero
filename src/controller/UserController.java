/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class UserController {
     // Creamos un objeto de la clase ConexionMySQL
    ConexionMySQL conexion;

    public UserController() {
        this.conexion = new ConexionMySQL();
    }
    
    public void insert(int identification, String name, String lastName, int age, int phone, String mail, String password) {
    // Establecemos la conexión con la base de datos
    try (Connection conn = conexion.conectarMySQL()) {
        // Verificamos si la conexión fue exitosa
        if (conn != null) {
            // Verificamos si la cédula ya existe en la base de datos
            if (!cedulaExistente(conn, identification)) {
                // Preparamos la consulta SQL para insertar datos
                String insertSQL = "INSERT INTO usuario (identification, name, lastName, age, phone, mail, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setInt(1, identification);
                    pstmt.setString(2, name);
                    pstmt.setString(3, lastName);
                    pstmt.setInt(4, age);
                    pstmt.setInt(5, phone);
                    pstmt.setString(6, mail);
                    pstmt.setString(7, password);
                    

                    // Ejecutamos la consulta
                    int rowsAffected = pstmt.executeUpdate();

                    // Verificamos si la inserción fue exitosa
                    if (rowsAffected > 0) {
                        System.out.println("Inserción exitosa");
                    } else {
                        System.out.println("No se pudo insertar los datos");
                    }
                }
            } else {
                System.out.println("La cédula ya existe en la base de datos");
            }
        } else {
            System.out.println("No se pudo establecer la conexión con la base de datos");
        }
    } catch (SQLException e) {
        System.out.println("Ocurrió un error al realizar la inserción en la base de datos");
        e.printStackTrace();
    }
}
    
    // Método para verificar si la cédula ya existe en la base de datos
private boolean cedulaExistente(Connection conn, int identification) throws SQLException {
    String query = "SELECT COUNT(*) AS count FROM usuario WHERE identification = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, identification);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        }
    }
    return false;
}
    
     public void select() {
        // Establecemos la conexión con la base de datos
        try (Connection conn = conexion.conectarMySQL()) {
            // Verificamos si la conexión fue exitosa
            if (conn != null) {
                // Preparamos la consulta SQL para seleccionar datos
                String selectSQL = "SELECT * FROM usuario";
                try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                    // Ejecutamos la consulta
                    ResultSet rs = pstmt.executeQuery();

                    // Iteramos sobre los resultados
                    while (rs.next()) {
                        System.out.println("Cedula: " + rs.getInt("identification") + "Nombre: " + rs.getString("name") + ", Apellido: " + rs.getString("lastName") + ", Edad: " + rs.getInt("edad") + ", Telefono: " + rs.getInt("phone") + ", Correo: " + rs.getString("mail") + ", Contraseña: " + rs.getString("password"));
                    }
                }
            } else {
                System.out.println("No se pudo establecer la conexión con la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al realizar la selección en la base de datos");
            e.printStackTrace();
        }
    }
     
     public void delete(int identification) {
    // Establecemos la conexión con la base de datos
    try (Connection conn = conexion.conectarMySQL()) {
        // Verificamos si la conexión fue exitosa
        if (conn != null) {
            // Preparamos la consulta SQL para eliminar el usuario
            String deleteSQL = "DELETE FROM usuario WHERE identification = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, identification);

                // Ejecutamos la consulta
                int rowsAffected = pstmt.executeUpdate();

                // Verificamos si la eliminación fue exitosa
                if (rowsAffected > 0) {
                    System.out.println("Eliminación exitosa del usuario con cédula " + identification);
                } else {
                    System.out.println("No se encontró ningún usuario con la cédula especificada");
                }
            }
        } else {
            System.out.println("No se pudo establecer la conexión con la base de datos");
        }
    } catch (SQLException e) {
        System.out.println("Ocurrió un error al realizar la eliminación en la base de datos");
    }
}
     
     public void update(int identification, String name, String lastName, int age, int phone, String mail, String password) {
    // Establecemos la conexión con la base de datos
    try (Connection conn = conexion.conectarMySQL()) {
        // Verificamos si la conexión fue exitosa
        if (conn != null) {
            // Verificamos si la cédula ya existe en la base de datos
            if (cedulaExistente(conn, identification)) {
                // Preparamos la consulta SQL para actualizar datos
                String updateSQL = "UPDATE usuario SET identification = ?, name = ?, lastName = ?, age = ?, phone = ?, mail = ?, password = ? WHERE identification = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                    pstmt.setInt(1, identification);
                    pstmt.setString(2, name);
                    pstmt.setString(3, lastName);
                    pstmt.setInt(4, age);
                    pstmt.setInt(5, phone);
                    pstmt.setString(6, mail);
                    pstmt.setString(7, password);

                    // Ejecutamos la consulta
                    int rowsAffected = pstmt.executeUpdate();

                    // Verificamos si la actualización fue exitosa
                    if (rowsAffected > 0) {
                        System.out.println("Actualización exitosa para el usuario con cédula " + identification);
                    } else {
                        System.out.println("No se pudo actualizar los datos para la cédula especificada");
                    }
                }
            } else {
                System.out.println("La cédula no existe en la base de datos, no se puede actualizar");
            }
        } else {
            System.out.println("No se pudo establecer la conexión con la base de datos");
        }
    } catch (SQLException e) {
        System.out.println("Ocurrió un error al realizar la actualización en la base de datos");
    }
    
     }
}
