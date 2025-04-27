package dev.diego.ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import dev.diego.Perfil;

public class DatosFicheros {
        /**
     * Borra todas las carpetas y archivos relacionados con un perfil del juego.
     * 
     * @param perfiles     La lista de perfiles actuales del juego.
     * @param nombrePerfil El nombre del perfil a borrar.
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
     * @param perfiles     La lista de perfiles actuales del juego.
     * @param nombrePerfil El nombre del perfil a borrar.
     */
    public static void borrarCarpetas(List<Perfil> perfiles, File directorioUsuario) {
        try (FileWriter writer = new FileWriter("./src/VideoJuego/Saves/perfiles.txt")) {
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

    private static void borrarSubDirectorios(File directory) {
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                borrarSubDirectorios(file);
            }
        }
        directory.delete();
    }

    //-----------------CARGAR JUEGO AL INICIAR------------------

    private static void archivoVacio(List<Perfil> perfiles, File archivo) {
        try (PrintWriter escritor = new PrintWriter(archivo)) {
            if (archivo.createNewFile()) {
                System.out.println("Archivo 'perfiles.txt' creado en: " + archivo.getAbsolutePath());
            }
            perfiles.add(new Perfil("DEFAULT"));
            escritor.println(perfiles.get(0).toString());
            File carpetaPerfil = new File("./src/VideoJuego/Saves/", perfiles.get(0).getNombre());
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

    private static void cargarPerfilesAFichero(List<Perfil> perfiles, File archivo){
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

    private static void crearFichero(List<Perfil> perfiles, File archivo){
        try (PrintWriter escritor = new PrintWriter(archivo)) {
            archivo.createNewFile();
            perfiles.add(new Perfil("DEFAULT"));
            escritor.println(perfiles.get(0).toString());

            File carpetaPerfil = new File("./src/VideoJuego/Saves/", perfiles.get(0).getNombre());
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
        File carpetaVideoJuego = new File("./src/VideoJuego");
        if (!carpetaVideoJuego.exists()) {
            carpetaVideoJuego.mkdirs();
        }

        File carpeta = new File(carpetaVideoJuego, "Saves");
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
