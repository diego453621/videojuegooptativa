package dev.diego.ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import dev.diego.Perfil;

/**
 * Clase que contiene los metodos para leer y escribir los datos del juego en
 * archivos. Estos datos se guardan en la carpeta "Saves" y se dividen en dos
 * carpetas: "Perfiles" y "Partidas". La carpeta "Perfiles" contiene una lista
 * de
 * perfiles en un archivo de texto llamado "perfiles.txt" y cada perfil tiene su
 * propia carpeta con el nombre del perfil en la que se guardan las partidas y
 * los
 * datos del perfil. La carpeta "Partidas" contiene los archivos de texto de las
 * partidas guardadas del juego.
 * 
 * @author Diego Luengo
 */
public class DatosFicheros {
    /**
     * Borra todas las carpetas y archivos relacionados con un perfil del juego.
     * 
     * @param directorio La carpeta a borrar.
     */
    public static void borrarCarpetas(File directorio) {
        try {
            if (directorio.exists() && directorio.isDirectory()) {
                borrarSubDirectorios(directorio);
            }
        } catch (Exception e) {
            System.out.println("Error al borrar las carpetas: " + e.getMessage());
        }
    }

    /**
     * Borra todas las carpetas y archivos relacionados con un perfil del juego.
     * 
     * @param perfiles       La lista de perfiles actuales del juego.
     * @param directorioUsuario El directorio del usuario a borrar.
     */
    public static void borrarCarpetas(List<Perfil> perfiles, File directorioUsuario) {
        try (FileWriter writer = new FileWriter("./Saves/perfiles.txt")) {
            for (Perfil p : perfiles) {
                writer.write(p.toString() + "\n");
            }

            if (directorioUsuario.exists() && directorioUsuario.isDirectory()) {
                borrarSubDirectorios(directorioUsuario);
            }
        } catch (IOException e) {
            System.out.println("Error al borrar el perfil: " + e.getMessage());
        }
    }

    /**
     * Borra todos los archivos y subdirectorios de un directorio.
     * 
     * @param directory El directorio a borrar.
     */
    private static void borrarSubDirectorios(File directorio) {
        if (directorio.isDirectory()) {
            for (File file : directorio.listFiles()) {
                borrarSubDirectorios(file);
            }
        }
        directorio.delete();
    }

    // -----------------CARGAR JUEGO AL INICIAR------------------

    /**
     * Crea un archivo de perfiles vacio, si no existe, o reemplaza su contenido
     * con el perfil por defecto.
     * 
     * @param perfiles La lista de perfiles actuales del juego.
     * @param archivo  El archivo de perfiles.
     */
    private static void archivoVacio(List<Perfil> perfiles, File archivo) {
        try (PrintWriter escritor = new PrintWriter(archivo)) {
            if (archivo.createNewFile()) {
                System.out.println("Archivo 'perfiles.txt' creado en: " + archivo.getAbsolutePath());
            }
            perfiles.add(new Perfil("DEFAULT"));
            escritor.println(perfiles.get(0).toString());
            File carpetaPerfil = new File("./Saves/", perfiles.get(0).getNombre());
            File archivoPartidas = new File(carpetaPerfil, "partidas.txt");
            if (!carpetaPerfil.exists()) {
                carpetaPerfil.mkdirs();
                System.out.println("Carpeta creada");
            } else {
                System.out.println("La carpeta ya existe");
            }
            if (!archivoPartidas.exists()) {
                archivoPartidas.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error al crear o escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Lee el archivo de perfiles y carga los perfiles en la lista de perfiles.
     * 
     * @param perfiles La lista de perfiles actuales del juego.
     * @param archivo  El archivo de perfiles.
     */
    private static void cargarPerfilesAFichero(List<Perfil> perfiles, File archivo) {
        try (Scanner lector = new Scanner(archivo)) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] nombresPerfiles = linea.split(";");
                perfiles.add(new Perfil(nombresPerfiles[0]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo");
        }
    }

    /**
     * Crea un archivo de perfiles vac o si no existe y agrega un perfil
     * "DEFAULT" a la lista de perfiles.
     *
     * @param perfiles La lista de perfiles actuales del juego.
     * @param archivo  El archivo de perfiles.
     */
    private static void crearFichero(List<Perfil> perfiles, File archivo) {
        try (PrintWriter escritor = new PrintWriter(archivo)) {
            archivo.createNewFile();
            perfiles.add(new Perfil("DEFAULT"));
            escritor.println(perfiles.get(0).toString());

            File carpetaPerfil = new File("./Saves/", perfiles.get(0).getNombre());
            File archivoPartidas = new File(carpetaPerfil, "partidas.txt");

            if (!carpetaPerfil.exists()) {
                carpetaPerfil.mkdirs();
                System.out.println("Carpeta creada");
            } else {
                System.out.println("La carpeta ya existe");
            }
            if (!archivoPartidas.exists()) {
                archivoPartidas.createNewFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al escribir en el archivo");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo" + e);
        }
    }

    /**
     * Carga el juego desde el archivo de perfiles.
     * 
     * Si el archivo no existe, lo crea y crea un perfil por defecto. Si el archivo
     * existe y tiene contenido, lo lee y crea los perfiles correspondientes.
     * 
     * @param perfiles La lista de perfiles que se modificara.
     * @param scanner  El scanner para leer el archivo.
     */
    public static void cargarJuego(List<Perfil> perfiles, Scanner scanner) {

        File carpeta = new File("./Saves");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        File archivo = new File(carpeta, "perfiles.txt");

        if (archivo.exists()) {
            if (archivo.length() == 0) {
                archivoVacio(perfiles, archivo);
            } else {
                cargarPerfilesAFichero(perfiles, archivo);
            }
        } else {
            crearFichero(perfiles, archivo);
        }
    }
}
