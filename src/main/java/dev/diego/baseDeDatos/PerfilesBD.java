package dev.diego.baseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import dev.diego.GestionDeDatos;
import dev.diego.Perfil;
import dev.diego.Herramientas;

public class PerfilesBD {

    /**
     * Inserta un perfil en la base de datos.
     *
     * @param perfil El perfil a insertar
     */
    public static void insertarPerfil(Perfil perfil) {
        String sql = "INSERT INTO perfiles (nombre, id) VALUES (?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, perfil.getNombre());
            statement.setInt(2, 1);

            int filas = statement.executeUpdate();

            System.out.println(filas + " Perfil insertado");
        } catch (SQLException e) {
            System.out.println("Error al insertar el perfil: " + e.getMessage());
        }
    };

    /**
     * Inserta un perfil en la base de datos.
     *
     * @param perfil El perfil a insertar
     */
    public static void insertarPerfil(Perfil perfil, List<Perfil> perfiles) {
        String sql = "INSERT INTO perfiles (nombre, id) VALUES (?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, perfil.getNombre());
            statement.setInt(2, perfiles.size() + 1);

            int filas = statement.executeUpdate();

            System.out.println(filas + " Perfil insertado");

            GestionDeDatos.setPerfilActivo(perfiles.get(perfiles.size() - 1));
        } catch (SQLException e) {
            System.out.println("Error al insertar el perfil: " + e.getMessage());
        }
    };

    /**
     * Borra un perfil de la base de datos.
     * 
     * @param perfil El perfil a borrar
     */
    public static void borrarPerfil(Perfil perfil, List<Perfil> perfiles) {
        String sql = "DELETE FROM perfiles WHERE nombre = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, perfil.getNombre());

            int filas = statement.executeUpdate();

            System.out.println(filas + "Perfil borrado");

            GestionDeDatos.setPerfilActivo(perfiles.get(0));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    };

    public static void borrarPerfilYReorganizar(Scanner scanner, List<Perfil> perfiles) {
        // Listar los perfiles disponibles
        listarPerfiles();

        // Pedir al usuario que introduzca el ID del perfil a borrar
        int idPerfil = Herramientas.pedirNumeroEntero(scanner, "Introduce el ID del perfil a borrar", 1,
                perfiles.size());

        // Buscar el perfil correspondiente en la lista usando un do-while
        Perfil perfil = null;
        int i = 0;

        do {
            if (perfiles.get(i).getId() == idPerfil) {
                perfil = perfiles.get(i);
            } else {
                i++;
            }
        } while (perfil == null && i < perfiles.size());

        if (perfil == null) {
            System.out.println("Perfil no encontrado.");

        } else {
            String sqlBorrarPerfil = "DELETE FROM perfiles WHERE id = ?";
            String sqlActualizarPerfiles = "UPDATE perfiles SET id = id - 1 WHERE id > ?";
            String sqlActualizarPartidas = "UPDATE partidas SET perfil_id = perfil_id - 1 WHERE perfil_id > ?";

            try (Connection conn = ConexionBD.obtenerConexion()) {
                conn.setAutoCommit(false); // Inicia una transacción

                // Eliminar el perfil
                try (PreparedStatement statementBorrar = conn.prepareStatement(sqlBorrarPerfil)) {
                    statementBorrar.setInt(1, perfil.getId());
                    statementBorrar.executeUpdate();
                }

                // Reorganizar los IDs de los perfiles
                try (PreparedStatement statementActualizarPerfiles = conn.prepareStatement(sqlActualizarPerfiles)) {
                    statementActualizarPerfiles.setInt(1, perfil.getId());
                    statementActualizarPerfiles.executeUpdate();
                }

                // Actualizar las referencias en la tabla partidas
                try (PreparedStatement statementActualizarPartidas = conn.prepareStatement(sqlActualizarPartidas)) {
                    statementActualizarPartidas.setInt(1, perfil.getId());
                    statementActualizarPartidas.executeUpdate();
                }

                conn.commit(); // Confirma la transacción
                System.out.println("Perfil eliminado y IDs reorganizados correctamente.");

                // Actualizar la lista de perfiles
                perfiles.clear();
                perfiles.addAll(obtenerPerfiles());
            } catch (SQLException e) {
                System.out.println("Error al borrar el perfil y reorganizar los IDs: " + e.getMessage());
            }
        }
    }

    /**
     * Obtiene la lista de perfiles de la base de datos.
     * 
     * @param perfiles La lista donde se guardan los perfiles.
     */
    public static List<Perfil> obtenerPerfiles() {
        String sql = "SELECT * FROM perfiles";

        List<Perfil> perfiles = new ArrayList<>();

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int id = resultSet.getInt("id");
                Perfil perfil = new Perfil(nombre, id);
                perfiles.add(perfil);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los perfiles: " + e.getMessage());
        }

        return perfiles;
    }

    public static void obtenerPerfil(Perfil perfil, int id) {
        String sql = "SELECT * FROM perfiles WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    perfil.setNombre(nombre);
                    perfil.setId(id);
                } else {
                    System.out.println("Perfil no encontrado");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el perfil: " + e.getMessage());
        }
    };

    public static void obtenerPerfil(Perfil perfil, String nombre) {
        String sql = "SELECT * FROM perfiles WHERE nombre = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nombre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    perfil.setNombre(nombre);
                    perfil.setId(id);
                } else {
                    System.out.println("Perfil no encontrado");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el perfil: " + e.getMessage());
        }
    };

    public static void listarPerfiles() {
        String sql = "SELECT * FROM perfiles";

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                System.out.println(id + ": " + nombre);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los perfiles: " + e.getMessage());
        }
    }

    public static void cargarPerfil(List<Perfil> perfiles, Scanner scanner) {
        listarPerfiles();

        int id = Herramientas.pedirNumeroEntero(scanner, "Introduce el ID del perfil a cargar", 1, perfiles.size() + 1);
        Perfil perfil = perfiles.get(id - 1);
        GestionDeDatos.setPerfilActivo(perfil);
    }
}
