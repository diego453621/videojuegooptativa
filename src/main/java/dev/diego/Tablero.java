package dev.diego;

import java.util.Arrays;

import java.util.*;

/**
 * Clase que define la estructura básica para un tablero en el juego.
 * No puede generar tableros por sí misma, ya que esto queda a cargo de las
 * clases que la extiendan.
 */
public class Tablero {
    /** Dimensiones del tablero (n x n) */
    private int dimensiones;
    /** Representación del tablero tridimensional, donde se almacenan las cartas */
    private String[][][] tablero;
    /** Diseño oculto de las cartas */
    private final String[] DISENOOCULTO = {
            ".------.",
            "|?.--. |",
            "| :(): |",
            "| ()() |",
            "| '--'?|",
            "`------'"
    };
    /** Altura de cada carta en el tablero (en número de líneas) */
    private final int ALTURACARTAS = 6;
    /** Secuencia ANSI para resetear el color al predeterminado en la consola */
    private final String RESET = "\033[0m";

    /**
     * Constructor de la clase Tablero.
     * Inicializa el tablero con las dimensiones dadas.
     * Según el parámetro {@code oculto}, genera el tablero con cartas reveladas o
     * con el diseño oculto.
     *
     * @param dimensiones Tamaño del tablero (n x n)
     * @param oculto      Si es {@code true} genera el tablero oculto; de lo
     *                    contrario, genera el tablero revelado.
     */
    public Tablero(int dimensiones, boolean oculto) {
        this.dimensiones = dimensiones;
        this.tablero = new String[dimensiones][dimensiones][ALTURACARTAS];

        if (!oculto) {
            generarTablero();
        } else if (oculto) {
            generarTableroOculto();
        }
    }

    /**
     * Obtiene el diseño oculto de las cartas.
     *
     * @return Array de cadenas que representa el diseño oculto de las cartas.
     */
    public String[] getDisenoOculto() {
        return DISENOOCULTO;
    }

    /**
     * Obtiene las dimensiones del tablero.
     *
     * @return Dimensiones del tablero.
     */
    public int getDimensiones() {
        return dimensiones;
    }

    /**
     * Establece nuevas dimensiones para el tablero.
     *
     * @param dimensiones Nuevo tamaño del tablero.
     */
    public void setDimensiones(int dimensiones) {
        this.dimensiones = dimensiones;
    }

    /**
     * Obtiene la matriz tridimensional que representa el tablero.
     *
     * @return Matriz tridimensional del tablero.
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
     * Obtiene el código ANSI para resetear el color en la consola.
     *
     * @return Secuencia ANSI para resetear el color.
     */
    public String getRESET() {
        return RESET;
    }

    /**
     * Valida si las coordenadas dadas están dentro del rango permitido.
     *
     * @param fila    Fila a validar.
     * @param columna Columna a validar.
     * @param minimo   Valor mínimo permitido.
     * @return true si las coordenadas son válidas; false en caso contrario.
     */
    public boolean esNumeroValido(int fila, int columna, int minimo) {
        return (fila >= minimo && fila < dimensiones) && (columna >= minimo && columna < dimensiones);
    }


    /**
     * Valida si las coordenadas dadas están dentro del rango permitido y si ya
     * han sido descubiertas anteriormente.
     *
     * @param fila    Fila a validar.
     * @param columna Columna a validar.
     * @param minimo   Valor mínimo permitido.
     * @return true si las coordenadas son válidas; false en caso contrario.
     */
    public boolean validarCoordenada(int fila, int columna, int minimo) {
        boolean numeroValido = true;
        if (!esNumeroValido(fila, columna, minimo)) {
            System.out.println("Las coordenadas deben estar entre 1 y 4.");
            numeroValido = false;
        } else if(!Arrays.equals(recogerCarta(fila, columna), getDisenoOculto())) {
            System.out.println("La posición seleccionada ya fue descubierta. Elige otra.");
            numeroValido = false;
        }
        return numeroValido;
    }

    /**
     * Genera el tablero con cartas reveladas.
     * 
     * Inicializa el tablero tridimensional asignando un diseño aleatorio para
     * cada carta. Cada diseño se asigna a dos cartas en el tablero. Los diseños
     * son seleccionados aleatoriamente de una lista predefinida y se asegura que
     * cada diseño se utiliza exactamente dos veces.
     */

    public void generarTablero() {
        int dimensiones = getDimensiones();
        String[][][] tablero = getTablero();

        List<String[]> disenos = new ArrayList<>();
        disenos.add(new String[] { ".------.", "|1.--. |", "| :/\\: |", "| (__) |", "| '--'1|", "`------'" });
        disenos.add(new String[] { ".------.", "|2.--. |", "| (\\/) |", "| :\\/: |", "| '--'2|", "`------'" });
        disenos.add(new String[] { ".------.", "|3.--. |", "| :(): |", "| ()() |", "| '--'3|", "`------'" });
        disenos.add(new String[] { ".------.", "|4.--. |", "| :/\\: |", "| :\\/: |", "| '--'4|", "`------'" });
        disenos.add(new String[] { ".------.", "|5.--. |", "| :/\\: |", "| (__) |", "| '--'5|", "`------'" });
        disenos.add(new String[] { ".------.", "|6.--. |", "| (\\/) |", "| :\\/: |", "| '--'6|", "`------'" });
        disenos.add(new String[] { ".------.", "|7.--. |", "| :(): |", "| ()() |", "| '--'7|", "`------'" });
        disenos.add(new String[] { ".------.", "|8.--. |", "| :/\\: |", "| :\\/: |", "| '--'8|", "`------'" });
        Map<String[], Integer> contador = new HashMap<>();
        for (String[] diseno : disenos) {
            contador.put(diseno, 0);
        }

        for (int j = 0; j < dimensiones; j++) {
            for (int j2 = 0; j2 < dimensiones; j2++) {
                int random = (int) (Math.random() * disenos.size());
                String[] diseno = disenos.get(random);
                while (contador.get(diseno) >= 2) {
                    random++;
                    if (random > 7) {
                        random = 0;
                    }
                    diseno = disenos.get(random);
                }
                contador.put(diseno, contador.get(diseno) + 1);
                for (int i = 0; i < diseno.length; i++) {
                    tablero[j][j2][i] = diseno[i];
                }
                Collections.shuffle(disenos);
            }
        }
    }

    /**
     * Genera el tablero con las cartas ocultas.
     * Se asigna a cada posición del tablero el diseño predefinido de cartas
     * ocultas.
     */
    public void generarTableroOculto() {
        int dimensiones = getDimensiones();
        String[][][] tablero = getTablero();
        for (int i = 0; i < dimensiones; i++) {
            for (int j = 0; j < dimensiones; j++) {
                for (int j2 = 0; j2 < tablero[i][j].length; j2++) {
                    tablero[i][j][j2] = DISENOOCULTO[j2];
                }
            }
        }
    }

    /**
     * Muestra el tablero en la consola.
     * Recorre las filas y las cartas para imprimir su contenido de forma
     * organizada.
     */
    public void mostrarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            // Se recorre cada línea de la carta (asumiendo que todas tienen el mismo número
            // de líneas)
            for (int j = 0; j < tablero[i][0].length; j++) {
                // Se recorre cada carta en la fila i
                for (int j2 = 0; j2 < tablero[i].length; j2++) {
                    System.out.print(tablero[i][j2][j] + "              ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * Devuelve el diseño de la carta en la posición especificada del tablero.
     *
     * @param filas    Coordenada de la fila en el tablero.
     * @param columnas Coordenada de la columna en el tablero.
     * @return Array de cadenas que representa el diseño de la carta seleccionada.
     */
    public String[] recogerCarta(int filas, int columnas) {
        return tablero[filas][columnas];
    }

    /**
     * Cambia el diseño de una carta en el tablero sin modificar su color.
     *
     * @param filas    Coordenada de la fila en el tablero.
     * @param columnas Coordenada de la columna en el tablero.
     * @param nueva    Nuevo diseño de la carta (array de cadenas).
     */
    public void cambiarCartaSinColor(int filas, int columnas, String nueva[]) {
        String[][][] tablero = getTablero();
        for (int i = 0; i < nueva.length; i++) {
            tablero[filas][columnas][i] = nueva[i];
        }
    }

    /**
     * Cambia el diseño de una carta en el tablero y le asigna un color.
     *
     * @param filas    Coordenada de la fila en el tablero.
     * @param columnas Coordenada de la columna en el tablero.
     * @param nueva    Nuevo diseño de la carta (array de cadenas).
     * @param color    Código ANSI del color que se aplicará a la carta.
     */
    public void cambiarCartaConColor(int filas, int columnas, String[] nueva, String color) {
        String[][][] tablero = getTablero();
        for (int i = 0; i < nueva.length; i++) {
            tablero[filas][columnas][i] = color + nueva[i] + getRESET();
        }
    }

    /**
     * Devuelve una representacion en String de este tablero, mostrando su
     * dimension, el contenido del tablero, el dise o oculto, la altura de las
     * cartas y el codigo ANSI para resetear el color.
     *
     * @return La representacion en String de este tablero.
     */
    @Override
    public String toString() {
        return "Tablero [dimensiones=" + dimensiones + ", tablero=" + Arrays.toString(tablero) + ", DISENOOCULTO="
                + Arrays.toString(DISENOOCULTO) + ", ALTURACARTAS=" + ALTURACARTAS + ", RESET=" + RESET + "]";
    }

}
