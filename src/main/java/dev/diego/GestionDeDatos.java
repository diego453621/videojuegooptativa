package dev.diego;

import java.util.List;
import java.util.Scanner;

import dev.diego.baseDeDatos.DatosDB;
import dev.diego.ficheros.DatosFicheros;

/**
 * Clase que gestiona la carga y el guardado de los datos del juego, ya sea en
 * ficheros o en base de datos.
 */
public class GestionDeDatos {

    /**
     * Número de sistema guardado. 1 para ficheros, 2 para base de datos.
     */
    private static int sistemaGuardado = 2;

    /**
     * Perfil activo del usuario.
     */
    private static Perfil perfilActivo = null;

    /**
     * Obtiene el número de sistema guardado.
     * 
     * @return Número de sistema guardado.
     */
    public static int getSistemaGuardado() {
        return sistemaGuardado;
    }

    /**
     * Establece el número de sistema guardado.
     * 
     * @param sistemaGuardado Número de sistema guardado.
     */
    public static void setSistemaGuardado(int sistemaGuardado) {
        GestionDeDatos.sistemaGuardado = sistemaGuardado;
    }

    /**
     * Obtiene el perfil activo del usuario.
     * 
     * @return Perfil activo del usuario.
     */
    public static Perfil getPerfilActivo() {
        return perfilActivo;
    }

    /**
     * Establece el perfil activo del usuario.
     * 
     * @param perfilActivo Perfil activo del usuario.
     */
    public static void setPerfilActivo(Perfil perfilActivo) {
        GestionDeDatos.perfilActivo = perfilActivo;
    }

    /**
     * Carga el juego a partir de los datos guardados en el sistema seleccionado.
     * 
     * @param perfiles Lista de perfiles disponibles.
     * @param scanner  Scanner para leer la opción del usuario.
     */
    public static void cargarJuego(List<Perfil> perfiles, Scanner scanner) {
        if (sistemaGuardado == 1) {
            DatosFicheros.cargarJuego(perfiles, scanner);
        } else if (sistemaGuardado == 2) {
            DatosDB.cargarJuego(perfiles);
        }
    }
}
