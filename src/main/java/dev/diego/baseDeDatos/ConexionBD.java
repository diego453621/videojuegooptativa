package dev.diego.baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que establece la conexión con la base de datos MySQL.
 * 
 * @author Diego Luengo
 */
public class ConexionBD {
    /**
     * URL de la base de datos MySQL.
     */
    private static final String URL = "jdbc:mysql://localhost:3306/extremememory";
    /**
     * Direccion URL de la base de datos MySQL.
     */
    private static final String USUARIO = "root";
    /**
     * Contraseña de acceso a la base de datos.
     */
    private static final String CLAVE = "Password1234"; // pon aquí tu contraseña si tienes

    /**
     * Establishes and returns a connection to the database.
     *
     * @return A Connection object to the database.
     * @throws SQLException If there is an error while attempting to connect.
     */
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
