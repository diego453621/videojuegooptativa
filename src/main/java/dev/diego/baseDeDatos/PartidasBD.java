package dev.diego.baseDeDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.diego.GestionDeDatos;
import dev.diego.Herramientas;
import dev.diego.Partida;
import dev.diego.Perfil;

/**
 * Clase que gestiona las partidas en la base de datos.
 * 
 * @author Diego Luengo
 */
public class PartidasBD {

    /**
     * Inserta una partida en la base de datos.
     * 
     * @param partida La partida a insertar.
     * @param perfil  El perfil al que pertenece la partida.
     * @param datos   Los datos de la partida en formato de bytes.
     */
    public static void insertarPartida(Partida partida, Perfil perfil, byte[] datos) {
        String sql = "INSERT INTO partidas (id, perfil_id, datos) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, partida.getFechaHoraInicio().toString().replace(":", "-"));
            statement.setInt(2, perfil.getId());
            statement.setBytes(3, datos);

            int filas = statement.executeUpdate();

            System.out.println(filas + " Partida insertada");
        } catch (SQLException e) {
            System.out.println("Error al insertar la partida: " + e.getMessage());
        }
    }

    /**
     * Actualiza una partida existente en la base de datos.
     * 
     * @param partida La partida a actualizar.
     * @param perfil  El perfil al que pertenece la partida.
     * @param datos   Los nuevos datos de la partida en formato de bytes.
     */

    public static void actualizarPartida(Partida partida, Perfil perfil, byte[] datos) {
        String sql = "UPDATE partidas SET datos = ? WHERE id = ? AND perfil_id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setBytes(1, datos);
            System.out.println(partida.getFechaHoraInicio().toString().replace(":", "-"));
            statement.setString(2, partida.getFechaHoraInicio().toString().replace(":", "-"));
            statement.setInt(3, perfil.getId());

            int filas = statement.executeUpdate();

            System.out.println(filas + " Partida actualizada");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la partida: " + e.getMessage());
        }
    }

    /**
     * Comprueba si una partida existe en la base de datos.
     * 
     * @param partida La partida a comprobar.
     * @param perfil  El perfil al que pertenece la partida.
     * @return true si la partida existe, false en caso contrario.
     */
    public static boolean existePartida(Partida partida, Perfil perfil) {
        String sql = "SELECT COUNT(*) FROM partidas WHERE id = ? AND perfil_id = ?";
        boolean partidaExiste = false;

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, partida.getFechaHoraInicio().toString().replace(":", "-"));
            statement.setInt(2, perfil.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        partidaExiste = true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al comprobar la partida: " + e.getMessage());
        }

        return partidaExiste;
    }

    /**
     * Carga una partida guardada en la base de datos.
     * 
     * @param nombre El nombre de la partida a cargar.
     * @param perfil El perfil al que pertenece la partida.
     * 
     * @return La partida cargada, o null si no se encuentra.
     */
    public static Partida cargarPartida(String nombre, Perfil perfil) {
        String sql = "SELECT * FROM partidas WHERE id = ? AND perfil_id = ?";

        Partida partida = null;

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setInt(2, perfil.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    byte[] datos = resultSet.getBytes("datos");
                    partida = Partida.leerPartidaByte(datos);
                } else {
                    System.out.println("Partida no encontrada");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la partida: " + e.getMessage());
        }

        return partida;
    }

    /**
     * Carga todas las partidas de la base de datos en la lista de perfiles
     * proporcionada.
     * 
     * @param perfiles La lista de perfiles a cargar.
     */
    public static void cargarPartidas(List<Perfil> perfiles) {
        String sql = "SELECT * FROM partidas";

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    int perfilId = resultSet.getInt("perfil_id");
                    byte[] datos = resultSet.getBytes("datos");
                    Perfil perfilEncontrado = null;
                    int i = 0;
                    boolean partidaCargada = false;
                    do {
                        perfilEncontrado = perfiles.get(i);
                        if (perfilEncontrado.getId() == perfilId) {
                            perfilEncontrado.agregarPartida(Partida.leerPartidaByte(datos));
                            partidaCargada = true;
                            System.out.println("Partida cargada: " + id);
                        }
                        i++;
                    } while (i < perfiles.size() && !partidaCargada);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las partidas: " + e.getMessage());
        }
    }

    /**
     * Lista todas las partidas de un perfil activo en la base de datos.
     * 
     * @return Una lista de partidas del perfil activo.
     */
    public static List<Partida> listarPartidas() {
        String sql = "SELECT * FROM partidas WHERE perfil_id = ?";
        List<Partida> listaPartidas = new ArrayList<>();

        try (Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, GestionDeDatos.getPerfilActivo().getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                int index = 1; // Contador para numerar las partidas
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    int perfilId = resultSet.getInt("perfil_id");
                    byte[] datos = resultSet.getBytes("datos");

                    // Mostrar la partida con un número a la izquierda
                    System.out.println(index + ". Partida: " + id + " - Perfil: " + perfilId);

                    // Agregar la partida a la lista
                    listaPartidas.add(Partida.leerPartidaByte(datos));
                    index++;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las partidas: " + e.getMessage());
        }

        return listaPartidas;
    }

    /**
     * Lista las partidas y permite al usuario seleccionar una para cargarla.
     * 
     * @param scanner Scanner para leer la entrada del usuario.
     * @return La partida seleccionada por el usuario, o null si no hay partidas
     *         disponibles.
     */
    public static Partida listarYCargarPartida(Scanner scanner) {
        // Listar las partidas y obtener la lista
        List<Partida> listaPartidas = listarPartidas();
        Partida cargada = null;

        if (listaPartidas.isEmpty()) {
            System.out.println("No hay partidas disponibles para cargar.");
        } else {
            // Pedir al usuario que elija una partida por su número
            System.out.println("Introduce el número de la partida a cargar: ");
            int opcion = Herramientas.pedirNumeroEntero(scanner, "Selecciona una partida válida", 1,
                    listaPartidas.size());

            // Cargar la partida seleccionada
            cargada = listaPartidas.get(opcion - 1); // Restar 1 porque las listas empiezan en 0
        }
        return cargada;
    }
}
