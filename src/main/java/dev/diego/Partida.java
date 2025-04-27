package dev.diego;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Representa una partida del juego.
 *
 * Contiene toda la información necesaria para describir una partida del juego.
 */
public class Partida implements Serializable {
    /**
     * La fecha en la que se realizó la partida.
     */
    private LocalDate fecha;

    /**
     * La hora en la que se comenzó la partida.
     */
    private LocalTime horaInicio;

    /**
     * La fecha y hora en la que se comenzó la partida.
     */
    private LocalDateTime fechaHoraInicio;

    /**
     * La hora en la que se finalizó la partida.
     */
    private LocalTime horaFin;

    /**
     * El tablero principal del juego.
     */
    private String[][][] tablero;

    /**
     * El tablero que muestra el estado oculto del juego.
     */
    private String[][][] tableroOculto;

    /**
     * El número de aciertos realizados en la partida.
     */
    private int aciertos;

    /**
     * El número máximo de intentos permitidos en la partida.
     */
    private int maxIntentos;

    public Partida(int aciertos, int maxIntentos) {
        this.fecha = LocalDate.now();
        this.horaInicio = LocalTime.now();
        this.fechaHoraInicio = LocalDateTime.now();
        this.aciertos = aciertos;
        this.maxIntentos = maxIntentos;
    }

    /**
     * Constructor para inicializar una nueva partida con los tableros dados,
     * el número de aciertos y el máximo de intentos permitidos.
     * 
     * @param tablero       El tablero principal del juego.
     * @param tableroOculto El tablero que muestra el estado oculto del juego.
     * @param aciertos      El número de aciertos iniciales.
     * @param maxIntentos   El número máximo de intentos permitidos en la partida.
     */
    public Partida(Tablero tablero, Tablero tableroOculto, int aciertos, int maxIntentos) {
        this.fecha = LocalDate.now();
        this.horaInicio = LocalTime.now();
        this.fechaHoraInicio = LocalDateTime.now();
        this.tablero = tablero.getTablero();
        this.tableroOculto = tableroOculto.getTablero();
        this.aciertos = aciertos;
        this.maxIntentos = maxIntentos;
    }

    /**
     * Obtiene la fecha en la que se inicio esta partida.
     *
     * @return La fecha en la que se inicio esta partida.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de esta partida.
     * 
     * @param fecha Nueva fecha para esta partida.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * Obtiene la hora de inicio de esta partida.
     *
     * @return La hora de inicio de esta partida.
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Establece la hora de inicio de esta partida.
     *
     * @param horaInicio Nueva hora de inicio para esta partida.
     */

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Obtiene la hora de fin de esta partida.
     *
     * @return La hora de fin de esta partida, o null si no se ha establecido.
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * Establece la hora de fin de esta partida.
     *
     * @param horaFin Nueva hora de fin para esta partida.
     */
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * Obtiene el tablero de esta partida.
     *
     * @return La matriz tridimensional que representa el tablero.
     */
    public String[][][] getTablero() {
        return tablero;
    }

    /**
     * Establece un nuevo estado para el tablero.
     *
     * @param tablero Nueva matriz tridimensional que representa el tablero.
     */

    public void setTablero(String[][][] tablero) {
        this.tablero = tablero;
    }

    /**
     * Obtiene el tablero oculto de esta partida.
     * 
     * @return Matriz tridimensional que representa el tablero oculto.
     */
    public String[][][] getTableroOculto() {
        return tableroOculto;
    }

    /**
     * Establece un nuevo estado para el tablero oculto.
     *
     * @param tableroOculto Nueva matriz tridimensional que representa el tablero
     *                      oculto.
     */

    public void setTableroOculto(String[][][] tableroOculto) {
        this.tableroOculto = tableroOculto;
    }

    /**
     * Obtiene el número de aciertos logrados en esta partida.
     * 
     * @return El número de aciertos logrados en esta partida.
     */
    public int getAciertos() {
        return aciertos;
    }

    /**
     * Establece el numero de aciertos en esta partida.
     * 
     * @param aciertos El numero de aciertos que se han logrado en esta partida.
     */
    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    /**
     * Obtiene el máximo de intentos permitidos para esta partida.
     * 
     * @return El número máximo de intentos que se pueden realizar en la partida.
     */
    public int getMaxIntentos() {
        return maxIntentos;
    }

    /**
     * Establece el máximo de intentos permitidos para la partida.
     *
     * @param maxIntentos El número máximo de intentos que se pueden realizar en la
     *                    partida.
     */

    public void setMaxIntentos(int maxIntentos) {
        this.maxIntentos = maxIntentos;
    }

    /**
     * Lee una partida desde un archivo y la devuelve.
     *
     * @param archivo El archivo que contiene la partida a leer.
     * @return La partida leida, o null si no se ha podido leer.
     */
    public static Partida leerPartida(File archivo) {
        try (ObjectInputStream flujoEntrada = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Partida) flujoEntrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Lee una partida desde un array de bytes y la devuelve.
     *
     * @param bytes El array de bytes que contiene la partida a leer.
     * @return La partida leida, o null si no se ha podido leer.
     */
    public static Partida leerPartidaByte(byte[] bytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream flujoEntrada = new ObjectInputStream(bais)) {
            return (Partida) flujoEntrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Guarda esta partida en un archivo.
     *
     * @param archivo El archivo donde se guardara la partida.
     */
    public void guardarPartida(File archivo) {
        try (ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            flujoSalida.writeObject(this);
        } catch (IOException e) {
            // No hacer nada
        }
    }

    public byte[] guardarPartidaByte() {
        byte[] bytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream flujoSalida = new ObjectOutputStream(baos)) {
            flujoSalida.writeObject(this);
            bytes = baos.toByteArray();
        } catch (IOException e) {
            // No hacer nada
        }
        return bytes;
    }

    /**
     * Devuelve una representacion en String de esta partida, mostrando su
     * fecha, hora de inicio, hora de fin, tablero, tablero oculto, aciertos y
     * maximo de intentos.
     *
     * @return La representacion en String de esta partida.
     */
    @Override
    public String toString() {
        return "Partida {\n"
                + "  fecha: " + fecha.toString() + ",\n"
                + "  horaInicio: " + horaInicio.toString() + ",\n"
                + "  horaFin: " + (horaFin != null ? horaFin.toString() : "null") + ",\n"
                + "  tablero: " + Arrays.deepToString(tablero) + ",\n"
                + "  tableroOculto: " + Arrays.deepToString(tableroOculto) + ",\n"
                + "  aciertos: " + aciertos + ",\n"
                + "  maxIntentos: " + maxIntentos + "\n"
                + "}";
    }
}
