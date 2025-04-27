package dev.diego.ficheros;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import dev.diego.GestionDeDatos;
import dev.diego.Herramientas;
import dev.diego.Perfil;

public class PerfilesFicheros {
    /**
     * Borra un perfil de la lista de perfiles.
     * Pide al usuario que introduzca el nombre del perfil que desea borrar y lo
     * busca en la lista de perfiles. Si lo encuentra, lo borra y muestra un
     * mensaje de confirmación. Si no lo encuentra, muestra un mensaje de error.
     * 
     * @param scanner  Scanner para leer el nombre del perfil a borrar
     * @param perfiles Lista de perfiles en la que se busca el perfil a borrar
     */
    public static void borrarPerfil(Scanner scanner, List<Perfil> perfiles) {
        if (perfiles.size() <= 1) {
            System.out.println("No se puede borrar, ya que solo hay un perfil disponible");
        } else {
            System.out.println("Introduce el nombre del perfil que deseas borrar:");
            String nombrePerfil = Herramientas.pedirCadena(scanner).toUpperCase();
            boolean perfilBorrado = false;
            int contador = 0;
            do {
                if (perfiles.get(contador).getNombre().equals(nombrePerfil)) {
                    perfilBorrado = true;
                    perfiles.remove(contador);
                    System.out.println("Perfil eliminado");

                    DatosFicheros.borrarCarpetas(perfiles,
                            new File("./Saves/" + nombrePerfil.toUpperCase()));

                    GestionDeDatos.setPerfilActivo(perfiles.get(0));

                } else if (contador == perfiles.size() - 1)
                    System.out.println("No se encontró el perfil");
                contador++;
            } while (!perfilBorrado && contador < perfiles.size());
            // boolean eliminado = perfiles.removeIf(perfil ->
            // perfil.getNombre().equals(nombrePerfil));
        }
    }

    /**
     * Carga un perfil ya existente en la lista de perfiles.
     * 
     * @param scanner  Scanner para leer la opción del usuario
     * @param perfiles Lista de perfiles en la que se busca el perfil a cargar
     * @return El perfil seleccionado o null si no hay perfiles disponibles
     */
    public static void cargarPerfil(Scanner scanner, List<Perfil> perfiles) {
        Perfil perfilSeleccionado;
        System.out.println("Perfiles disponibles:");
        for (int i = 0; i < perfiles.size(); i++) {
            System.out.println((i + 1) + ": " + perfiles.get(i).getNombre());
        }
        int opcion = Herramientas.pedirNumeroEntero(scanner, "Introduce el número del perfil a cargar", 1,
                perfiles.size());
        perfilSeleccionado = perfiles.get(opcion - 1);
        System.out.println("Perfil cargado: " + perfilSeleccionado.getNombre());
        GestionDeDatos.setPerfilActivo(perfilSeleccionado);
    }

    /**
     * Crea un perfil con el nombre introducido por el usuario.
     * 
     * @param scanner  Scanner para leer el nombre del perfil
     * @param perfiles Lista de perfiles en la que se guardará el nuevo perfil
     */
    public static void crearPerfil(Scanner scanner, List<Perfil> perfiles) {
        System.out.println("Introduce el nombre del perfil: ");
        String nombre = Herramientas.pedirCadena(scanner);
        Perfil perfilDevolver = null;

        if (nombre.length() > 0 && !Herramientas.comprobarCadena(".*[\\\\/:*?\"<>|\\s].*", nombre)) {
            boolean nombreRepetido = false;
            for (Perfil perfil : perfiles) {
                if (perfil.getNombre().equalsIgnoreCase(nombre)) {
                    nombreRepetido = true;
                }
            }
            if (nombreRepetido) {
                System.out.println("Ya existe un perfil con ese nombre.");
            } else {
                perfiles.add(new Perfil(nombre.toUpperCase()));
                System.out.println("Perfil creado " + nombre);

                try (PrintWriter writer = new PrintWriter("./Saves/perfiles.txt")) {
                    for (Perfil perfil : perfiles) {
                        writer.println(perfil.toString());
                    }
                    System.out.println("Perfiles guardados en perfiles.txt");
                    File carpetaPerfil = new File("./Saves/" + nombre.toUpperCase());
                    File archivoPartidas = new File(carpetaPerfil, "partidas.txt");
                    if (!carpetaPerfil.exists()) {
                        carpetaPerfil.mkdirs();
                    }
                    if (!archivoPartidas.exists()) {
                        archivoPartidas.createNewFile();
                    }
                    File carpetaPartidas = new File("./Saves/", nombre.toUpperCase() + "/Partidas");
                    if (!carpetaPartidas.exists()) {
                        carpetaPartidas.mkdir();
                    }
                    perfilDevolver = perfiles.get(perfiles.size() - 1);
                    GestionDeDatos.setPerfilActivo(perfilDevolver);
                } catch (IOException e) {
                    System.out.println("Error al guardar los perfiles: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Nombre no valido");
            perfilDevolver = perfiles.get(0);
        }
        System.out.println("Perfil cargado");
    }
}
