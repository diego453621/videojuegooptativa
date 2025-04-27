package dev.diego;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

/**
 * Representa un perfil de un usuario en el juego Extreme Memory.
 *
 * Un perfil se compone de un nombre y una lista de partidas guardadas.
 *
 */
public class Perfil {
    /**
     * El nombre del perfil.
     */
    private String nombre;

    /**
     * El identificador del perfil.
     *
     * El identificador se utiliza para identificar el perfil en el sistema de
     * guardado de partidas.
     */
    private int id;

    /**
     * La lista de partidas guardadas en el perfil.
     */
    private List<Partida> partidas;


    public Perfil(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.partidas = new ArrayList<>();
    }

    /**
     * Constructor de un perfil.
     * 
     * Crea un perfil nuevo con el nombre especificado y una lista vac a de
     * partidas.
     * 
     * @param nombre El nombre del perfil.
     */
    public Perfil(String nombre) {
        this.nombre = nombre;
        this.partidas = new ArrayList<>();
    }

    public Perfil(String nombre, List<Partida> partidas) {
        this.nombre = nombre;
        this.partidas = partidas;
    }

    /**
     * Obtiene el nombre de este perfil.
     *
     * @return El nombre del perfil
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de este perfil.
     *
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    /**
     * Añade la partida pasada como par metro a la lista de partidas del perfil.
     *
     * @param partida La partida a a adir
     */
    public void agregarPartida(Partida partida) {
        partidas.add(partida);
    }

    /**
     * Devuelve la lista de partidas guardadas en este perfil.
     *
     * @return La lista de partidas
     */
    public List<Partida> getPartidas() {
        return partidas;
    }

    /**
     * Carga las partidas especificadas en el perfil.
     *
     * Este m todo carga las partidas especificadas en el par metro
     * nombresPartidas en el perfil. Si una partida no existe, no se
     * a ade a la lista de partidas del perfil.
     *
     * @param nombresPartidas Los nombres de las partidas a cargar.
     */
    public void cargarPartidas(String[] nombresPartidas, File rutaPerfil) {
        for (int i = 0; i < nombresPartidas.length; i++) {
            File rutaPartida = new File(rutaPerfil, "/" + nombresPartidas[i] + ".txt");
            Partida partida = Partida.leerPartida(rutaPartida);
            if (partida != null) {
                partidas.add(partida);
                System.out.println("Partida cargada: " + rutaPartida);
            } else {
                System.out.println("Error loading partida from file: " + rutaPartida);
            }
        }
    }

    /**
     * Elimina una partida del perfil.
     *
     * @param partida La partida a eliminar de la lista de partidas del perfil.
     */
    public void eliminarPartida(Partida partida) {
        partidas.remove(partida);
    }

    /**
     * Muestra por pantalla la lista de partidas guardadas en este perfil.
     * Por cada partida se muestra su informacion en formato String.
     */
    public void mostrarPartidas() {
        System.out.println("Partidas de " + nombre + ":");
        for (Partida partida : partidas) {
            System.out.println(partida.toString());
        }
    }

    /**
     * Devuelve una representación en String de este perfil, mostrando su nombre.
     *
     * @return El nombre del perfil seguido de un punto y coma.
     */

    @Override
    public String toString() {
        return nombre + ";";
    }
}
