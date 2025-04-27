package dev.diego.juegoPrincipal;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dev.diego.GestionDeDatos;
import dev.diego.GestionDePartidas;
import dev.diego.GestionDePerfiles;
import dev.diego.MensajesColores;
import dev.diego.Tablero;
import dev.diego.Herramientas;
import dev.diego.Partida;
import dev.diego.Perfil;
import dev.diego.Configuracion;

public class ExtremeMemoryV1_DiegoLuengo {
    private static void cambiarDosCartas(Tablero tablero, Tablero tableroOculto, int[] filaColumna1, int[] filaColumna2,
            String color) {
        revelarCarta(tableroOculto, tablero, filaColumna1, color);
        revelarCarta(tableroOculto, tablero, filaColumna2, color);
        tableroOculto.mostrarTablero();
    }

    /**
     * Muestra en verde las dos cartas acertadas y espera un tiempo determinado
     * antes de borrar la terminal.
     * 
     * @param tableroOculto Tablero oculto que se va a mostrar
     * @param tablero       Tablero que contiene las cartas
     * @param filaColumna1  Cordenadas de la primera carta acertada
     * @param filaColumna2  Cordenadas de la segunda carta acertada
     */
    private static void acierto(Tablero tableroOculto, Tablero tablero, int[] filaColumna1, int[] filaColumna2) {
        MensajesColores.getMensajeAcierto(true);
        cambiarDosCartas(tablero, tableroOculto, filaColumna1, filaColumna2, MensajesColores.getColorVerde());
        Herramientas.limpiarConsola(2000);
    }

    /**
     * Muestra en rojo las dos cartas falladas y reinicia el estado de las cartas a
     * su estado
     * original (dise o oculto) y borra la terminal despues de dos segundos.
     * 
     * @param tableroOculto Tablero oculto que se va a mostrar
     * @param tablero       Tablero que contiene las cartas
     * @param filaColumna1  Cordenadas de la primera carta fallada
     * @param filaColumna2  Cordenadas de la segunda carta fallada
     */
    private static void fallo(Tablero tableroOculto, Tablero tablero, int[] filaColumna1, int[] filaColumna2) {
        MensajesColores.getMensajeAcierto(false);
        cambiarDosCartas(tablero, tableroOculto, filaColumna1, filaColumna2, MensajesColores.getColorRojo());
        Herramientas.limpiarConsola(2000);

        tableroOculto.cambiarCartaSinColor(filaColumna1[0], filaColumna1[1], tableroOculto.getDisenoOculto());
        tableroOculto.cambiarCartaSinColor(filaColumna2[0], filaColumna2[1], tableroOculto.getDisenoOculto());
    }

    /**
     * Comprueba si las dos cartas introducidas son iguales y marca las cartas
     * acertadas en verde y las falladas en rojo.
     * 
     * @param tableroOculto Tablero oculto que se va a mostrar
     * @param tablero       Tablero que contiene las cartas
     * @param filaColumna1  Cordenadas de la primera carta
     * @param filaColumna2  Cordenadas de la segunda carta
     * @param partida       Partida actual
     */
    private static void comprobarCartas(Tablero tableroOculto, Tablero tablero, int[] filaColumna1,
            int[] filaColumna2, Partida partida) {
        if ((filaColumna1[0] != filaColumna2[0]) || (filaColumna1[1] != filaColumna2[1])) {
            if (Arrays.equals(tablero.recogerCarta(filaColumna1[0], filaColumna1[1]),
                    tablero.recogerCarta(filaColumna2[0], filaColumna2[1]))) {
                acierto(tableroOculto, tablero, filaColumna1, filaColumna2);
                partida.setAciertos(partida.getAciertos() + 1);
            } else {
                fallo(tableroOculto, tablero, filaColumna1, filaColumna2);
                int intentos = partida.getMaxIntentos();
                partida.setMaxIntentos(intentos - 1);
            }
        } else {
            System.out.println("Introduce dos posiciones distintas");
            tableroOculto.cambiarCartaSinColor(filaColumna1[0], filaColumna1[1], tableroOculto.getDisenoOculto());
            tableroOculto.cambiarCartaSinColor(filaColumna2[0], filaColumna2[1], tableroOculto.getDisenoOculto());
        }
    }

    /**
     * Inicia el juego, crea un tablero y un tablero oculto, y una partida, y
     * permite al jugador jugar. Permite guardar la partida en un archivo
     * en cualquier momento. Si el jugador decide guardar, se guarda la
     * partida actual y se muestra el resultado. Si el jugador decide
     * seguir, se continua con el juego. Si el jugador no introduce una
     * opcion valida, se muestra un mensaje de error y se vuelve a preguntar.
     * 
     * @param scanner        Scanner para leer las opciones del usuario
     * @param COLOR_ROJO     Color rojo para utilizar en los mensajes de error
     * @param COLOR_VERDE    Color verde para utilizar en los mensajes de acierto
     * @param COLOR_AMARILLO Color amarillo para utilizar en los mensajes de
     *                       turno
     * @param perfil         Perfil del jugador
     */
    private static void juegoIniciado(Scanner scanner, String COLOR_ROJO, String COLOR_VERDE, String COLOR_AMARILLO,
            Perfil perfil) {
        Tablero tablero = new Tablero(Configuracion.getDimensiones(), false);
        Tablero tableroOculto = new Tablero(Configuracion.getDimensiones(), true);
        Partida partida = new Partida(tablero, tableroOculto, 0, Configuracion.getMaxIntentos());
        String fechaFormateada = partida.getFechaHoraInicio().toString().replace(":", "-");
        File archivoPartida = new File(
                "./Saves/" + perfil.getNombre().toUpperCase() + "/Partidas/" + fechaFormateada + ".txt");
        File archivoListaPartidas = new File("./Saves/", perfil.getNombre() + "/partidas.txt");

        perfil.agregarPartida(partida);

        tableroOculto.mostrarTablero();
        System.out.println();
        boolean partidaGuardada = false;

        GestionDePartidas.guardarPartida(archivoPartida, archivoListaPartidas, fechaFormateada, partida);
        partida.guardarPartida(archivoPartida);

        do {

            int[] filaColumna1 = pedirCordenadas(tableroOculto, scanner);
            if (filaColumna1 != null) {
                revelarCarta(tableroOculto, tablero, filaColumna1, COLOR_AMARILLO);

                if (Configuracion.getDificultad() == 1) {
                    tableroOculto.mostrarTablero();
                }

                int[] filaColumna2 = pedirCordenadas(tableroOculto, scanner);
                if (filaColumna2 != null) {
                    revelarCarta(tableroOculto, tablero, filaColumna2, COLOR_AMARILLO);
                    tableroOculto.mostrarTablero();

                    Herramientas.limpiarConsola(1000);
                    comprobarCartas(tableroOculto, tablero, filaColumna1, filaColumna2, partida);

                    tableroOculto.mostrarTablero();
                    System.out.println("Intentos restantes: " + partida.getMaxIntentos());
                } else {
                    partidaGuardada = true;
                }
            } else {
                partidaGuardada = true;
            }
        } while (!esJuegoTerminado(partida) && !partidaGuardada);
        if (partidaGuardada) {
            GestionDePartidas.guardarPartida(archivoPartida, archivoListaPartidas, fechaFormateada, partida);
        }

        partida.setHoraFin(LocalTime.now());

        mostrarResultado(partida);
    }

    /**
     * Inicia el juego para el perfil especificado, y utilizando la partida
     * cargada.
     * 
     * @param scanner        Scanner para leer las opciones.
     * @param COLOR_ROJO     Color rojo para las cartas falladas.
     * @param COLOR_VERDE    Color verde para las cartas acertadas.
     * @param COLOR_AMARILLO Color amarillo para las cartas seleccionadas.
     * @param perfil         Perfil del usuario que juega.
     * @param partidaCargada Partida cargada que se va a jugar.
     */
    private static void juegoIniciado(Scanner scanner, String COLOR_ROJO, String COLOR_VERDE, String COLOR_AMARILLO,
            Perfil perfil, Partida partidaCargada) {
        Tablero tablero = new Tablero(Configuracion.getDimensiones(), false);
        Tablero tableroOculto = new Tablero(Configuracion.getDimensiones(), true);
        Partida partida = partidaCargada;

        tablero.setTablero(partida.getTablero());
        tableroOculto.setTablero(partida.getTableroOculto());
        String fechaFormateada = partida.getFechaHoraInicio().toString().replace(":", "-");
        File archivoPartida = new File(
                "./Saves/" + perfil.getNombre().toUpperCase() + "/Partidas/" + fechaFormateada + ".txt");
        File archivoListaPartidas = new File("./Saves/", perfil.getNombre() + "/partidas.txt");

        perfil.agregarPartida(partida);

        tableroOculto.mostrarTablero();
        System.out.println();
        boolean partidaGuardada = false;

        do {

            int[] filaColumna1 = pedirCordenadas(tableroOculto, scanner);
            if (filaColumna1 != null) {
                revelarCarta(tableroOculto, tablero, filaColumna1, COLOR_AMARILLO);
                if (Configuracion.getDificultad() == 1) {
                    tableroOculto.mostrarTablero();
                }
                int[] filaColumna2 = pedirCordenadas(tableroOculto, scanner);
                if (filaColumna2 != null) {
                    revelarCarta(tableroOculto, tablero, filaColumna2, COLOR_AMARILLO);
                    tableroOculto.mostrarTablero();

                    Herramientas.limpiarConsola(1000);

                    comprobarCartas(tableroOculto, tablero, filaColumna1, filaColumna2, partida);
                    tableroOculto.mostrarTablero();
                    System.out.println("Intentos restantes: " + partida.getMaxIntentos());
                } else {
                    partidaGuardada = true;
                }
            } else {
                partidaGuardada = true;
            }

        } while (!esJuegoTerminado(partida) && !partidaGuardada);

        if (partidaGuardada) {
            GestionDePartidas.guardarPartida(archivoPartida, archivoListaPartidas, fechaFormateada, partida);
        }
        partida.setHoraFin(LocalTime.now());
        mostrarResultado(partida);
    }

    /**
     * Revela una carta en el tablero oculto, y le asigna el color especificado.
     * 
     * @param tableroOculto Tablero oculto donde se va a revelar la carta.
     * @param tablero       Tablero que contiene las cartas.
     * @param filaColumna   Cordenadas de la carta a revelar.
     * @param color         Color que se va a asignar a la carta.
     */
    private static void revelarCarta(Tablero tableroOculto, Tablero tablero, int[] filaColumna, String color) {
        tableroOculto.cambiarCartaConColor(filaColumna[0], filaColumna[1],
                tablero.recogerCarta(filaColumna[0], filaColumna[1]), color);
    }

    /**
     * Verifica si el juego ha terminado.
     * Se considera que el juego ha terminado si se han acertado 8 cartas o si
     * se han agotado los intentos.
     * 
     * @param partida Partida en curso.
     * @return true si el juego ha terminado, false en caso contrario.
     */
    private static boolean esJuegoTerminado(Partida partida) {
        return partida.getAciertos() == 8 || partida.getMaxIntentos() <= 0;
    }

    /**
     * Muestra el resultado del juego en la consola.
     * Si se han acertado 8 cartas, se imprime un mensaje de victoria.
     * De lo contrario, se imprime un mensaje de derrota.
     * 
     * @param partida Partida en curso, de la cual se evaluarán los aciertos.
     */
    private static void mostrarResultado(Partida partida) {
        if (partida.getAciertos() == 8)
            System.out.println("¡GANASTE!");
        else
            System.out.println("Has perdido");
    }

    /**
     * Este metodo devuelve un array de int que incluye dos numero para usarlos como
     * cordenadas
     * 
     * @param tableroOculto Hay que pasarle el tablero oculto que se usa para
     *                      comprobar que la cordenada no se haya resuelto ya
     * @param scanner       Scanner para poder leer los numeros
     * @return Devuelve el array
     */
    private static int[] pedirCordenadas(Tablero tableroOculto, Scanner scanner) {
        int[] filaColumna = { 0, 1 };
        boolean datosCorrectos = true;
        do {
            datosCorrectos = true;
            System.out.println("Introduce unas coordenadas (fila) (columna) entre 1 y 4 o 0 para guardar:");
            filaColumna[0] = Herramientas.pedirNumeroEntero(scanner) - 1;
            filaColumna[1] = Herramientas.pedirNumeroEntero(scanner) - 1;

            for (int i : filaColumna) {
                if (i == -1) {
                    filaColumna = null;
                }
            }
            if (filaColumna != null) {
                if (!tableroOculto.validarCoordenada(filaColumna[0], filaColumna[1])) {
                    datosCorrectos = false;
                }
            }
        } while (!datosCorrectos);
        return filaColumna;
    }

    /**
     * Muestra el menú principal del juego y permite al usuario seleccionar una
     * opción.
     * 
     * @param scanner Scanner para leer la entrada del usuario.
     */
    private static void mostrarMenu(Scanner scanner) {
        MensajesColores.mostrarTitulo(MensajesColores.getColorRojo(), MensajesColores.reset());
        GestionDeDatos.setSistemaGuardado(MensajesColores.explicacionInicial(scanner));
        List<Perfil> perfiles = new ArrayList<>();
        GestionDeDatos.cargarJuego(perfiles, scanner);
        GestionDeDatos.setPerfilActivo(perfiles.get(0));
        Perfil perfilActivo = GestionDeDatos.getPerfilActivo();
        GestionDePartidas.cargarPartidas(perfiles);
        boolean iniciado = true;

        do {
            int opciones = 0;
            do {
                System.out.println("\n1: INICIAR");
                System.out.println("2: Cargar Partida");
                System.out.println("3: Configuracion");
                System.out.println("4: Perfiles");
                System.out.println("5: Ver partidas");
                System.out.println("6: Salir");
                opciones = Herramientas.pedirNumeroEnteroSinBucle(scanner);
                Herramientas.limpiarConsola(500);
            } while (!(opciones >= 1 && opciones <= 6));
            switch (opciones) {
                case 1 -> juegoIniciado(scanner, MensajesColores.getColorRojo(), MensajesColores.getColorVerde(),
                        MensajesColores.getColorAmarillo(), perfilActivo);
                case 2 -> juegoIniciado(scanner, MensajesColores.getColorRojo(), MensajesColores.getColorVerde(),
                        MensajesColores.getColorAmarillo(), perfilActivo,
                        GestionDePartidas.cargarPartida(scanner, perfilActivo));
                case 3 -> Configuracion.ajustes(scanner);
                case 4 -> {
                    GestionDePerfiles.gestionarPerfiles(scanner, perfiles, perfilActivo);
                    // GestionDeDatos.setPerfilActivo(GestionDePerfiles.gestionarPerfiles(scanner,
                    // perfiles, perfilActivo));
                    perfilActivo = GestionDeDatos.getPerfilActivo();
                }
                case 5 -> perfilActivo.mostrarPartidas();
                default -> iniciado = false;
            }
        } while (iniciado == true);
    }

    /**
     * Método principal que inicia el juego y maneja el flujo principal del
     * programa.
     * Este método realiza las siguientes acciones:
     * 1. Limpia la consola antes de iniciar.
     * 2. Inicializa un objeto Scanner para la entrada del usuario.
     * 3. Muestra el menú principal del juego para que el usuario interactúe.
     * 4. Cierra el objeto Scanner al finalizar el uso.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        Herramientas.limpiarConsola();
        Scanner scanner = new Scanner(System.in);
        mostrarMenu(scanner);
        scanner.close();
    }
}
