

# Proyecto: EXTREME MEMORY

Este proyecto es una implementación de un juego de memoria enlazando cartas con el mismo numero hasta completar el tablero

| Detalle               | Información       |
|-----------------------|-------------------|
| **Autor**            | DLG               |
| **Fecha de inicio**  | 01/11/2024        |
| **Última revisión**  | v1.7 17/03/2025     |

## Contenido del Proyecto

- **Versión 1.7 (`ExtremeMemoryV1_DiegoLuengo.java`)**: Clase TableroOculto y TableroResultado creado.


## Estructura del Proyecto

```plaintext
VideoJuegoOptativaDiegoLuengo/
│   README.md
│   VideoJuegoOptativaDiegoLuengo.jar
│   
├───.vscode
│       settings.json
│       tasks.json
│       
├───bin
│   ├───Pruebas
│   │       Cartas.class
│   │       Pruebas2.class
│   │       Tablero2.class
│   │       Videojuego2.class
│   │       Videojuego3.class
│   │       
│   └───VideoJuego
│           Colores.class
│           Configuracion.class
│           ExtremeMemoryV1_DiegoLuengo.class
│           Herramientas.class
│           Partida.class
│           Perfil.class
│           Tablero.class
│
└───src
    ├───Pruebas
    │       Cartas.java
    │       Pruebas2.java
    │       Tablero2.java
    │       Videojuego2.java
    │       Videojuego3.java
    │
    └───VideoJuego
            Colores.java
            Configuracion.java
            ExtremeMemoryV1_DiegoLuengo.java
            Herramientas.java
            Partida.java
            Perfil.java
            Tablero.java
```

## Requisitos del Sistema

- **Java Development Kit (JDK)** versión 21 o superior.
- Entorno de desarrollo integrado (IDE) recomendado: Visual Studio Code.

## Instrucciones de Ejecución

1. Abre el proyecto en **Visual Studio Code**.
2. Asegúrate de que JDK 21 esté correctamente configurado.
3. Ve a la carpeta `src/VideoJuego/` y ejecuta el archivo `ExtremeMemoryV1_DiegoLuengo.java`.
4. Compila y ejecuta el programa:


## Funcionalidades Principales

- **Tablero dinámico**: Se muestra un tablero actualizado después de cada seleccion.
- **Validaciones**: Impide movimientos inválidos (casillas ocupadas o fuera de rango).
- **Condiciones de victoria**: Verifica automáticamente si el jugador ha ganado.
- **Colores**: Diferencia las cartas ya acertadas de las seleccionadas y las demas.