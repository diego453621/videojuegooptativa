package dev.diego;

import java.util.Scanner;

/**
 * Clase que proporciona secuencias ANSI de colores para la consola y mensajes.
 */
public class MensajesColores {

    /**
     * Obtiene el código ANSI para el color rojo en la consola.
     *
     * @return Secuencia ANSI que representa el color rojo.
     */

    public static String getColorRojo() {
        return "\033[31m";
    }

    /**
     * Obtiene el código ANSI para el color verde en la consola.
     * 
     * @return Secuencia ANSI que representa el color verde.
     */
    public static String getColorVerde() {
        return "\033[32m";
    }

    /**
     * Obtiene el código ANSI para el color amarillo en la consola.
     *
     * @return Secuencia ANSI que representa el color amarillo.
     */

    public static String getColorAmarillo() {
        return "\033[33m";
    }

    /**
     * Obtiene el mensaje que se debe mostrar en la consola segun se haya
     * producido un acierto o fallo.
     * 
     * @param acierto Indica si se ha producido un acierto o fallo.
     */
    public static void getMensajeAcierto(boolean acierto) {
        if (acierto) {
            System.out.println("Acierto");
        } else {
            System.out.println("Fallo");
        }
    }

    /**
     * Obtiene el mensaje que se debe mostrar en la consola cuando se
     * gana el juego.
     * 
     * @return El mensaje que se debe mostrar en la consola.
     */
    public static String getMensajeGanador() {
        return "¡Has ganado!";
    }

    /**
     * Esta funcion imprime el titulo del juego
     * 
     * @param COLOR1 El color en el que se quiere impimir
     * @param RESET  Resetea el color
     */
    public static void mostrarTitulo(String COLOR1, String RESET) {
        System.out.print(COLOR1 +
                "   __      _                                                                    \n" +
                "  /__\\_  _| |_ _ __ ___ _ __ ___   ___    /\\/\\   ___ _ __ ___   ___  _ __ _   _ \n" +
                " /_\\ \\ \\/ / __| '__/ _ \\ '_ ` _ \\ / _ \\  /    \\ / _ \\ '_ ` _ \\ / _ \\| '__| | | | \n" +
                "//__  >  <| |_| | |  __/ | | | | |  __/ / /\\/\\ \\  __/ | | | | | (_) | |  | |_| | \n" +
                "\\__/ /_/\\_\\\\__|_|  \\___|_| |_| |_|\\___| \\/    \\/\\___|_| |_| |_|\\___/|_|   \\__, |        \n"
                +
                "                                                                          |___/  \n" + RESET);
    }

    /**
     * Imprime las instrucciones del juego
     * 
     * @param scanner Scanner para el pulsa enter para continuar
     */
    public static int explicacionInicial(Scanner scanner) {
        System.out.println("Al comienzo del juego se mostrara un tablero de 4x4 con cartas ocultas, el objetivo \n" +
                "del juego es revelar las cartas mientras se encuentran las parejas \n Elije el sistema de guardado \n"
                + "1. Guardar en Archivos" + "\n" + "2. Guardar en base de datos" + "\n");
        return Herramientas.pedirNumeroEntero(scanner, 1, 2);
    }

    /**
     * Obtiene el codigo ANSI para resetear el color en la consola.
     *
     * @return Secuencia ANSI para resetear el color.
     */
    public static String reset() {
        return "\033[0m";
    }
}
