package dev.diego;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionDePartidas {
    /**
     * Crea un archivo de partida en la carpeta de partidas del perfil
     * y agrega la fecha y hora de inicio de la partida al archivo de
     * lista de partidas.
     * 
     * @param archivoPartida       El archivo de la partida a crear.
     * @param archivoListaPartidas El archivo de lista de partidas.
     * @param fechaFormateada      La fecha y hora de inicio de la partida.
     */
    public static void crearArchivoPartida(File archivoPartida, File archivoListaPartidas, String fechaFormateada) {

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(archivoListaPartidas, true))) {
            writer.println(fechaFormateada + ";");
            if (!archivoPartida.exists()) {
                archivoPartida.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Carga las partidas guardadas de cada perfil en la lista de perfiles
     * pasada como par metro.
     *
     * @param perfiles La lista de perfiles cuyas partidas se van a cargar
     */
    public static void cargarPartidas(List<Perfil> perfiles) {
        // ------------------------CARGAR-PERFIL------------------------
        for (Perfil perfil : perfiles) {
            List<String> datos = new ArrayList<>();
            File carpetaPerfil = new File("./src/VideoJuego/Saves/", perfil.getNombre());
            File archivoPartidas = new File(carpetaPerfil, "partidas.txt");
            File carpetaPartidas = new File(carpetaPerfil, "/Partidas");
            try (Scanner lector = new Scanner(archivoPartidas)) {
                while (lector.hasNextLine()) {
                    String[] partidas = lector.nextLine().split(";");
                    for (String partida : partidas) {
                        if (!partida.trim().isEmpty()) {
                            datos.add(partida.trim());
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error al leer el archivo");
            }
            perfil.cargarPartidas(datos.toArray(new String[0]), carpetaPartidas);
        }
    }

    /**
     * Muestra una lista de partidas disponibles del perfil activo y permite
     * al usuario seleccionar una para cargar.
     *
     * @param scanner      Scanner para leer la entrada del usuario.
     * @param perfilActivo El perfil del usuario cuyas partidas están disponibles.
     * @return La partida seleccionada por el usuario.
     */

    public static Partida cargarPartida(Scanner scanner, Perfil perfilActivo) {
        Herramientas.limpiarConsola();
        System.out.println("Partidas disponibles:");
        System.out.println("Perfil: " + perfilActivo.getNombre());
        int i = 0;
        for (Partida partida : perfilActivo.getPartidas()) {
            System.out.printf("%d: %s\n", i, partida.getFechaHoraInicio());
            i++;
        }
        int opcion = Herramientas.pedirNumeroEntero(scanner, "Introduce el número de la partida a cargar", 0,
                perfilActivo.getPartidas().size() - 1);
        return perfilActivo.getPartidas().get(opcion);
    }
}
