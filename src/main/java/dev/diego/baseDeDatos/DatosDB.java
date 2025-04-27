package dev.diego.baseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dev.diego.Perfil;

public class DatosDB {

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

    public static void crearDefault(List<Perfil> perfiles) {
        perfiles.add(new Perfil("DEFAULT", 1));
        PerfilesBD.insertarPerfil(perfiles.get(0));
    }

    public static void cargarJuego(List<Perfil> perfiles) {
        if (!hayPerfiles()) {
            crearDefault(perfiles);
        } else {
            perfiles.addAll(PerfilesBD.obtenerPerfiles());
        }
    }
}
