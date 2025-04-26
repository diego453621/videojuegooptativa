package dev.diego;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Este archivo contiene herramientas que se pueden usar en otros archivos
 */
public class Herramientas {


    public static boolean comprobarCadena(String patron, String cadena){
        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(cadena);
        return m.find();
    }

    /**
     * Lee una cadena de entrada con el Scanner y la devuelve.
     * 
     * Antes de leer la cadena, se consume una linea de entrada si existiera,
     * esto se hace para evitar que se quede una entrada pendiente en el
     * buffer.
     * 
     * @param scanner Scanner para leer la cadena
     * @return La cadena leida
     */
    public static String pedirCadena(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        String cadena = scanner.nextLine();

        return cadena;
    }

    /**
     * Esta tiene un try catch incluido para pedir un int
     * 
     * @param scanner Scanner para leer el numero
     * @return Devuelve el numero
     */
    public static int pedirNumeroEntero(Scanner scanner) {
        int numero = 0;
        boolean entradaValida = false;

        do {
            try {
                numero = scanner.nextInt();
                entradaValida = true;
            } catch (Exception InputMismatchException) {
                scanner.nextLine();
                System.out.println("\nDato no valido \n");
            }

        } while (!entradaValida);
        return numero;
    }

    /**
     * Funcion para pedir un numero entero que tenga que mas que min y menos que max
     * 
     * @param scanner Scanner para leer el numero
     * @param min     El minimo que se puede introducir
     * @param max     El maximo que se puede introducir
     * @return El numero introducido
     */
    public static int pedirNumeroEntero(Scanner scanner, int min, int max) {
        int numero = 0;
        boolean entradaValida = false;

        do {
            try {
                entradaValida = false;
                numero = scanner.nextInt();
                entradaValida = true;
            } catch (Exception InputMismatchException) {
                scanner.nextLine();
                System.out.println("\nDato no valido \n");
            }
            if ((numero < min || numero > max) && entradaValida) {
                System.out.println("\nNumero no valido\n");
            }
        } while ((!entradaValida) || (numero < min || numero > max));
        return numero;
    }

    /**
     * Funcion que imprime un texto y despues solicita un numero con un minimo y un
     * maximo
     * 
     * @param scanner Scanner para leer el numero
     * @param texto   El texto a imprimir
     * @param min     El minimo que se puede introducir
     * @param max     El maximo que se puede introducir
     * @return El numero introducido
     */
    public static int pedirNumeroEntero(Scanner scanner, String texto, int min, int max) {
        int numero = 0;
        boolean entradaValida = false;

        System.out.println(texto);
        do {
            try {
                entradaValida = false;
                numero = scanner.nextInt();
                entradaValida = true;
            } catch (Exception InputMismatchException) {
                scanner.nextLine();
                System.out.println("\nDato no valido \n");
            }
            if ((numero < min || numero > max) && entradaValida) {
                System.out.println("\nNumero no valido\n");
            }
        } while ((!entradaValida) || (numero < min || numero > max));
        return numero;
    }

    /**
     * Esta funcion imprime un texto y despues solicita un numero
     * 
     * @param scanner Scanner para leer el numero
     * @param texto   El texto a imprimir
     * @return El numero introducido
     */
    public static int pedirNumeroEntero(Scanner scanner, String texto) {
        int numero = 0;
        boolean entradaValida = false;

        System.out.println(texto);
        do {
            try {
                numero = scanner.nextInt();
                entradaValida = true;
            } catch (Exception InputMismatchException) {
                scanner.nextLine();
                System.out.println("\nDato no valido \n");
            }
        } while (!entradaValida);
        return numero;
    }

    /**
     * Esta funcion tiene un try catch pero sin bucle por si ya esta en el codigo
     * principal
     * 
     * @param scanner Scanner para leer el numero
     * @return Devuelve el numero
     */
    public static int pedirNumeroEnteroSinBucle(Scanner scanner) {
        int numero = 0;

        try {
            numero = scanner.nextInt();
        } catch (Exception InputMismatchException) {
            scanner.nextLine();
            System.out.println("\nDato no valido\n");
        }
        return numero;
    }

    /**
     * Esta funcion limpia la consola
     */
    public static void limpiarConsola() {
        // Aparentemente esta parte para borrar la terminal no funciona en windows
        // https://es.stackoverflow.com/questions/529856/limpiar-la-consola-en-java
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Esta funcion sirve para esperar antes de la siguiente accion
     * 
     * @param millis Cantidad de tiempo a esperar
     */
    public static void esperar(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception InterruptedException) {
        }
    }

    /**
     * Limpia la consola y espera unos milisegundos antes de hacerlo.
     * 
     * @param millis Tiempo a esperar antes de limpiar la consola en milisegundos
     */
    public static void limpiarConsola(int millis) {
        esperar(millis);
        limpiarConsola();
    }
}
