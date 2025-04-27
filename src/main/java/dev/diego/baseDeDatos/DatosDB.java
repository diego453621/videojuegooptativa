package dev.diego.baseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dev.diego.Perfil;

/**
 * Clase que gestiona los datos de la base de datos.
 * 
 * @author Diego Luengo
 */
public class DatosDB {

    /**
     * Comprueba si existen perfiles en la tabla Perfiles de la base de datos.
     * 
     * @return true si hay perfiles, false en caso contrario
     */
    public static boolean hayPerfiles() {
        String sql = "SELECT COUNT(*) AS total FROM Perfiles";

        boolean hayPerfiles = false;
        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                if (total > 0) {
                    hayPerfiles = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al comprobar la tabla Perfiles: " + e.getMessage());
        }

        return hayPerfiles;
    }

    /**
     * Crea un perfil por defecto en la base de datos.
     * 
     * @param perfiles Lista de perfiles en los que se guardará el nuevo perfil
     */
    public static void crearDefault(List<Perfil> perfiles) {
        perfiles.add(new Perfil("DEFAULT", 1));
        PerfilesBD.insertarPerfil(perfiles.get(0));
    }

    /**
     * Carga los perfiles de la base de datos en la lista de perfiles.
     * 
     * @param perfiles Lista de perfiles en los que se guardarán los perfiles
     *                 cargados
     */
    public static void cargarJuego(List<Perfil> perfiles) {
        if (!hayPerfiles()) {
            crearDefault(perfiles);
        } else {
            perfiles.addAll(PerfilesBD.obtenerPerfiles());
        }
    }
}
