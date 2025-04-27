package dev.diego;

import java.util.List;
import java.util.Scanner;

import dev.diego.ficheros.PerfilesFicheros;
import dev.diego.baseDeDatos.PerfilesBD;


/**
 * Clase que gestiona los perfiles del juego.
 * 
 * Permite crear, cargar, mostrar y borrar perfiles.
 * 
 * @author Diego Luengo
 */
public class GestionDePerfiles {

    /**
     * Crea un perfil con el nombre introducido por el usuario.
     * 
     * El perfil se crea en la lista de perfiles pasada como par metro.
     * 
     * @param scanner  Scanner para leer el nombre del perfil
     * @param perfiles Lista de perfiles en la que se crear el nuevo perfil
     */
    private static void crearPerfil(Scanner scanner, List<Perfil> perfiles) {
        if (GestionDeDatos.getSistemaGuardado() == 1) {
            PerfilesFicheros.crearPerfil(scanner, perfiles);
        } else if (GestionDeDatos.getSistemaGuardado() == 2) {
            System.out.println("Introduce el nombre del perfil: ");
            String nombre = Herramientas.pedirCadena(scanner);
            perfiles.add(new Perfil(nombre, perfiles.size() + 1));
            PerfilesBD.insertarPerfil(perfiles.get(perfiles.size() - 1));
        }
    }

    /**
     * Borra un perfil del sistema.
     * 
     * @param scanner  Scanner para leer la opci n del usuario
     * @param perfiles Lista de perfiles en la que se busca el perfil a borrar
     */
    private static void borrarPerfil(Scanner scanner, List<Perfil> perfiles) {
        if (perfiles.size() <= 1) {
            System.out.println("No se puede borrar, ya que solo hay un perfil disponible");
        } else {
            if (GestionDeDatos.getSistemaGuardado() == 1) {
                PerfilesFicheros.borrarPerfil(scanner, perfiles);
    
            } else if (GestionDeDatos.getSistemaGuardado() == 2) {
                PerfilesBD.borrarPerfilYReorganizar(scanner, perfiles);
    
            }
        }
    }

    private static void cargarPerfil(Scanner scanner, List<Perfil> perfiles) {
        if (GestionDeDatos.getSistemaGuardado() == 1) {
            PerfilesFicheros.cargarPerfil(scanner, perfiles);
        } else if (GestionDeDatos.getSistemaGuardado() == 2) {
            PerfilesBD.cargarPerfil(perfiles, scanner);
        }
    }

    /**
     * Gestiona los perfiles del juego.
     * 
     * Permite crear, cargar, mostrar y borrar perfiles.
     * 
     * @param scanner      Scanner para leer la entrada del usuario
     * @param perfiles     Lista de perfiles en la que se realizan las operaciones
     * @param perfilActivo Perfil activo en el juego
     */
    public static void gestionarPerfiles(Scanner scanner, List<Perfil> perfiles, Perfil perfilActivo) {
        int opcion = 0;
        do {
            System.out.println("1: Crear Perfil");
            System.out.println("2: Cargar Perfil");
            System.out.println("3: Mostrar Perfiles");
            System.out.println("4: Borrar Perfil");
            System.out.println("5: Salir");

            opcion = Herramientas.pedirNumeroEntero(scanner, "Introduce una opcion", 1, 5);
            switch (opcion) {
                case 1 -> crearPerfil(scanner, perfiles);
                case 2 -> cargarPerfil(scanner, perfiles);
                case 3 -> {
                    Herramientas.limpiarConsola();
                    // Mostrar perfiles
                    System.out.println("Perfiles actuales:");
                    for (Perfil perfil : perfiles) {
                        System.out.println(perfil.getNombre());
                    }
                }
                case 4 -> borrarPerfil(scanner, perfiles);
            }
        } while (opcion != 5);
    }
}
