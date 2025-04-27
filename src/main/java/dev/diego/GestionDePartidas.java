package dev.diego;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import dev.diego.baseDeDatos.PartidasBD;
import dev.diego.ficheros.PartidasFicheros;

/**
 * Clase que gestiona el guardado y la carga de partidas.
 */
public class GestionDePartidas {

    /**
     * Guarda una partida en el sistema de guardado seleccionado (ficheros o base de
     * datos).
     * 
     * @param archivoPartida       Archivo de la partida a guardar.
     * @param archivoListaPartidas Archivo de la lista de partidas.
     * @param fechaFormateada      Fecha formateada de la partida.
     * @param partida              Partida a guardar.
     */
    public static void guardarPartida(File archivoPartida, File archivoListaPartidas, String fechaFormateada,
            Partida partida) {
        if (GestionDeDatos.getSistemaGuardado() == 1) {
            PartidasFicheros.guardarPartidas(archivoPartida, archivoListaPartidas, fechaFormateada, partida);
        } else if (GestionDeDatos.getSistemaGuardado() == 2) {
            if (!PartidasBD.existePartida(partida, GestionDeDatos.getPerfilActivo())) {
                PartidasBD.insertarPartida(partida, GestionDeDatos.getPerfilActivo(), partida.guardarPartidaByte());
            } else {
                PartidasBD.actualizarPartida(partida, GestionDeDatos.getPerfilActivo(), partida.guardarPartidaByte());
            }
        }
    }

    /**
     * Carga las partidas guardadas en el sistema de guardado seleccionado (ficheros
     * o
     * base de datos).
     * 
     * @param perfiles Lista de perfiles en los que se cargarán las partidas.
     */
    public static void cargarPartidas(List<Perfil> perfiles) {
        if (GestionDeDatos.getSistemaGuardado() == 1) {
            PartidasFicheros.cargarPartidas(perfiles);
        } else if (GestionDeDatos.getSistemaGuardado() == 2) {
            PartidasBD.cargarPartidas(perfiles);
        }
    }

    /**
     * Carga una partida del sistema de guardado seleccionado (ficheros o base de
     * datos).
     * 
     * @param scanner Scanner para leer la entrada del usuario.
     * @param perfil  Perfil al que pertenece la partida.
     * @return Partida cargada.
     */
    public static Partida cargarPartida(Scanner scanner, Perfil perfil) {
        Partida partida = null;
        if (GestionDeDatos.getSistemaGuardado() == 1) {
            partida = PartidasFicheros.cargarPartida(scanner, perfil);
        } else if (GestionDeDatos.getSistemaGuardado() == 2) {
            partida = PartidasBD.listarYCargarPartida(scanner);
        }
        return partida;
    }
}
