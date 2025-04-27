package dev.diego;

import java.util.List;
import java.util.Scanner;

import dev.diego.baseDeDatos.DatosDB;
import dev.diego.ficheros.DatosFicheros;

public class GestionDeDatos {
    // 1-Fichero 2-Base de datos
    private static int sistemaGuardado = 2;
    private static Perfil perfilActivo = null;

    public static int getSistemaGuardado() {
        return sistemaGuardado;
    }

    public static void setSistemaGuardado(int sistemaGuardado) {
        GestionDeDatos.sistemaGuardado = sistemaGuardado;
    }

    public static Perfil getPerfilActivo() {
        return perfilActivo;
    }

    public static void setPerfilActivo(Perfil perfilActivo) {
        GestionDeDatos.perfilActivo = perfilActivo;
    }

    public static void cargarJuego(List<Perfil> perfiles, Scanner scanner) {
        if (sistemaGuardado == 1) {
            DatosFicheros.cargarJuego(perfiles, scanner);
        } else if (sistemaGuardado == 2) {
            DatosDB.cargarJuego(perfiles);
        }
    }
}
