package dev.diego;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import dev.diego.baseDeDatos.PartidasBD;
import dev.diego.ficheros.PartidasFicheros;

public class GestionDePartidas {

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

    public static void cargarPartidas(List<Perfil> perfiles) {
        if (GestionDeDatos.getSistemaGuardado() == 1) {
            PartidasFicheros.cargarPartidas(perfiles);
        } else if (GestionDeDatos.getSistemaGuardado() == 2) {
            PartidasBD.cargarPartidas(perfiles);
        }
    }

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
