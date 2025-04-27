package dev.diego;

import java.util.Scanner;

/**
 * Clase que guarda todos los atributos de configuracion del juego
 */
public class Configuracion {
    /**
     * Numero de filas y columnas del tablero.
     */
    private static int dimensiones = 4;

    /**
     * Dificultad del juego, 1 para facil y 2 para dificil.
     */
    private static int dificultad = 1;

    /**
     * Numero maximo de intentos permitidos en una partida.
     */
    private static int maxIntentos = 20;

    /**
     * Obtiene el número de filas y columnas del tablero.
     *
     * @return El número de filas y columnas del tablero.
     */
    public static int getDimensiones() {
        return dimensiones;
    }

    /**
     * Establece el número de filas y columnas del tablero.
     *
     * @param dimensiones El nuevo número de filas y columnas del tablero.
     */

    public static void setDimensiones(int dimensiones) {
        Configuracion.dimensiones = dimensiones;
    }

    /**
     * Obtiene la dificultad del juego.
     * 
     * @return Dificultad del juego, 1 para facil y 2 para dificil.
     */
    public static int getDificultad() {
        return dificultad;
    }

    /**
     * Establece la dificultad del juego.
     * 
     * @param dificultad La nueva dificultad del juego, 1 para facil y 2 para
     *                   dificil.
     */
    public static void setDificultad(int dificultad) {
        Configuracion.dificultad = dificultad;
    }

    /**
     * Obtiene el número máximo de intentos permitidos en una partida.
     * 
     * @return El número máximo de intentos permitidos en una partida.
     */
    public static int getMaxIntentos() {
        return maxIntentos;
    }

    /**
     * Establece el número máximo de intentos permitidos en una partida.
     *
     * @param maxIntentos El nuevo número máximo de intentos permitidos en una
     *                    partida.
     */
    public static void setMaxIntentos(int maxIntentos) {
        Configuracion.maxIntentos = maxIntentos;
    }

    /**
     * Permite configurar el juego, permite elegir:
     * 
     * 1. El tamañno del tablero (Solo funciona 4)
     * 2. La dificultad del juego, en dificil no se muestran las cartas elegidas
     * 3. El maximo de intentos permitidos en una partida
     * 
     * @param scanner Scanner para leer las opciones
     */
    public static void ajustes(Scanner scanner) {

        /*
         * **********************************
         *
         * CONFIGURACION
         *
         * **********************************
         */

        int configuracion = 1;

        do {
            // Menu de opciones
            System.out.println("\nCONFIGURACION\n");
            System.out.println("1: Dimensiones del tablero");
            System.out.println("2: Dificultad");
            System.out.println("3: Maximo de intentos");

            configuracion = Herramientas.pedirNumeroEnteroSinBucle(scanner);

        } while (configuracion < 1 && configuracion > 3);

        switch (configuracion) {
            case 1:
                // Opcion para configurar el tamañno del tablero para un futuro
                do {
                    System.out.println(
                            "Introduce las dimensiones del tablero, este numero representara el numero de filas y columnas (Solo funciona 4)");
                    dimensiones = Herramientas.pedirNumeroEnteroSinBucle(scanner);
                } while (dimensiones != 4);
                break;

            case 2:
                // Opcion para configurar la dificultad, en dificil no se muestran las cartas
                // elegidas

                dificultad = Herramientas.pedirNumeroEntero(scanner,
                        "Elige la dificultad (1)Facil (2)Dificil, Esto afecta a si se muestra la carta elegida o no", 1,
                        2);
                break;

            case 3:
                do {
                    setMaxIntentos(Herramientas.pedirNumeroEntero(scanner,
                            "Introduce el numero de limite de intentos que quieres"));
                } while (getMaxIntentos() < 1);
                break;
            default:
                break;
        }
    }
}
