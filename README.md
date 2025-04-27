

# Proyecto: EXTREME MEMORY

Este proyecto es una implementación de un juego de memoria enlazando cartas con el mismo numero hasta completar el tablero

| Detalle               | Información       |
|-----------------------|-------------------|
| **Autor**            | DLG               |
| **Fecha de inicio**  | 01/11/2024        |
| **Última revisión**  | v1.8 27/04/2025     |

## COMO DESCARGAR

Utilizar el siguiente comando de git: git clone https://github.com/diego453621/videojuegooptativa.git

## Contenido del Proyecto

- **Versión 1.8 (`ExtremeMemoryV1_DiegoLuengo.java`)**:


## Estructura del Proyecto

```plaintext
C:.
│   pom.xml
│   README.md
│   
├───.vscode
│       settings.json
│       
├───help
│   └───reports
│       └───apidocs
│           │   allclasses-index.html
│           │   allpackages-index.html
│           │   copy.svg
│           │   element-list
│           │   help-doc.html
│           │   index-all.html
│           │   index.html
│           │   link.svg
│           │   member-search-index.js
│           │   module-search-index.js
│           │   overview-summary.html
│           │   overview-tree.html
│           │   package-search-index.js
│           │   script.js
│           │   search-page.js
│           │   search.html
│           │   search.js
│           │   serialized-form.html
│           │   stylesheet.css
│           │   tag-search-index.js
│           │   type-search-index.js
│           │
│           ├───dev
│           │   └───diego
│           │       │   Configuracion.html
│           │       │   GestionDeDatos.html
│           │       │   GestionDePartidas.html
│           │       │   GestionDePerfiles.html
│           │       │   Herramientas.html
│           │       │   MensajesColores.html
│           │       │   package-summary.html
│           │       │   package-tree.html
│           │       │   package-use.html
│           │       │   Partida.html
│           │       │   Perfil.html
│           │       │   Tablero.html
│           │       │
│           │       ├───baseDeDatos
│           │       │   │   ConexionBD.html
│           │       │   │   DatosDB.html
│           │       │   │   package-summary.html
│           │       │   │   package-tree.html
│           │       │   │   package-use.html
│           │       │   │   PartidasBD.html
│           │       │   │   PerfilesBD.html
│           │       │   │
│           │       │   └───class-use
│           │       │           ConexionBD.html
│           │       │           DatosDB.html
│           │       │           PartidasBD.html
│           │       │           PerfilesBD.html
│           │       │
│           │       ├───class-use
│           │       │       Configuracion.html
│           │       │       GestionDeDatos.html
│           │       │       GestionDePartidas.html
│           │       │       GestionDePerfiles.html
│           │       │       Herramientas.html
│           │       │       MensajesColores.html
│           │       │       Partida.html
│           │       │       Perfil.html
│           │       │       Tablero.html
│           │       │
│           │       ├───ficheros
│           │       │   │   DatosFicheros.html
│           │       │   │   package-summary.html
│           │       │   │   package-tree.html
│           │       │   │   package-use.html
│           │       │   │   PartidasFicheros.html
│           │       │   │   PerfilesFicheros.html
│           │       │   │
│           │       │   └───class-use
│           │       │           DatosFicheros.html
│           │       │           PartidasFicheros.html
│           │       │           PerfilesFicheros.html
│           │       │
│           │       └───juegoPrincipal
│           │           │   ExtremeMemoryV1_DiegoLuengo.html
│           │           │   package-summary.html
│           │           │   package-tree.html
│           │           │   package-use.html
│           │           │
│           │           └───class-use
│           │                   ExtremeMemoryV1_DiegoLuengo.html
│           │
│           ├───legal
│           │       COPYRIGHT
│           │       jquery.md
│           │       jqueryUI.md
│           │       LICENSE
│           │
│           ├───resources
│           │       glass.png
│           │       x.png
│           │
│           └───script-dir
│                   jquery-3.7.1.min.js
│                   jquery-ui.min.css
│                   jquery-ui.min.js
│
├───Saves
│   │   perfiles.txt
│   │
│   └───DEFAULT
│       │   partidas.txt
│       │
│       └───Partidas
│               2025-04-27T21-46-59.682518700.txt
│
├───src
│   ├───main
│   │   └───java
│   │       └───dev
│   │           └───diego
│   │               │   Configuracion.java
│   │               │   GestionDeDatos.java
│   │               │   GestionDePartidas.java
│   │               │   GestionDePerfiles.java
│   │               │   Herramientas.java
│   │               │   MensajesColores.java
│   │               │   Partida.java
│   │               │   Perfil.java
│   │               │   Tablero.java
│   │               │
│   │               ├───baseDeDatos
│   │               │       ConexionBD.java
│   │               │       DatosDB.java
│   │               │       PartidasBD.java
│   │               │       PerfilesBD.java
│   │               │       ScriptBBDD.sql
│   │               │
│   │               ├───ficheros
│   │               │       DatosFicheros.java
│   │               │       PartidasFicheros.java
│   │               │       PerfilesFicheros.java
│   │               │
│   │               └───juegoPrincipal
│   │                       ExtremeMemoryV1_DiegoLuengo.java
│   │
│   └───test
│       └───java
│           └───dev
│               └───diego
└───target
    │   maven-javadoc-plugin-stale-data.txt
    │
    ├───classes
    │   └───dev
    │       └───diego
    │           │   Configuracion.class
    │           │   GestionDeDatos.class
    │           │   GestionDePartidas.class
    │           │   GestionDePerfiles.class
    │           │   Herramientas.class
    │           │   MensajesColores.class
    │           │   Partida.class
    │           │   Perfil.class
    │           │   Tablero.class
    │           │
    │           ├───baseDeDatos
    │           │       ConexionBD.class
    │           │       DatosDB.class
    │           │       PartidasBD.class
    │           │       PerfilesBD.class
    │           │       ScriptBBDD.sql
    │           │
    │           ├───ficheros
    │           │       DatosFicheros.class
    │           │       PartidasFicheros.class
    │           │       PerfilesFicheros.class
    │           │
    │           └───juegoPrincipal
    │                   ExtremeMemoryV1_DiegoLuengo.class
    │
    ├───javadoc-bundle-options
    │       javadoc-options-javadoc-resources.xml
    │
    ├───reports
    │   └───apidocs
    │       │   allclasses-index.html
    │       │   allpackages-index.html
    │       │   copy.svg
    │       │   element-list
    │       │   help-doc.html
    │       │   index-all.html
    │       │   index.html
    │       │   link.svg
    │       │   member-search-index.js
    │       │   module-search-index.js
    │       │   overview-summary.html
    │       │   overview-tree.html
    │       │   package-search-index.js
    │       │   script.js
    │       │   search-page.js
    │       │   search.html
    │       │   search.js
    │       │   serialized-form.html
    │       │   stylesheet.css
    │       │   tag-search-index.js
    │       │   type-search-index.js
    │       │
    │       ├───dev
    │       │   └───diego
    │       │       │   Configuracion.html
    │       │       │   GestionDeDatos.html
    │       │       │   GestionDePartidas.html
    │       │       │   GestionDePerfiles.html
    │       │       │   Herramientas.html
    │       │       │   MensajesColores.html
    │       │       │   package-summary.html
    │       │       │   package-tree.html
    │       │       │   package-use.html
    │       │       │   Partida.html
    │       │       │   Perfil.html
    │       │       │   Tablero.html
    │       │       │
    │       │       ├───baseDeDatos
    │       │       │   │   ConexionBD.html
    │       │       │   │   DatosDB.html
    │       │       │   │   package-summary.html
    │       │       │   │   package-tree.html
    │       │       │   │   package-use.html
    │       │       │   │   PartidasBD.html
    │       │       │   │   PerfilesBD.html
    │       │       │   │
    │       │       │   └───class-use
    │       │       │           ConexionBD.html
    │       │       │           DatosDB.html
    │       │       │           PartidasBD.html
    │       │       │           PerfilesBD.html
    │       │       │
    │       │       ├───class-use
    │       │       │       Configuracion.html
    │       │       │       GestionDeDatos.html
    │       │       │       GestionDePartidas.html
    │       │       │       GestionDePerfiles.html
    │       │       │       Herramientas.html
    │       │       │       MensajesColores.html
    │       │       │       Partida.html
    │       │       │       Perfil.html
    │       │       │       Tablero.html
    │       │       │
    │       │       ├───ficheros
    │       │       │   │   DatosFicheros.html
    │       │       │   │   package-summary.html
    │       │       │   │   package-tree.html
    │       │       │   │   package-use.html
    │       │       │   │   PartidasFicheros.html
    │       │       │   │   PerfilesFicheros.html
    │       │       │   │
    │       │       │   └───class-use
    │       │       │           DatosFicheros.html
    │       │       │           PartidasFicheros.html
    │       │       │           PerfilesFicheros.html
    │       │       │
    │       │       └───juegoPrincipal
    │       │           │   ExtremeMemoryV1_DiegoLuengo.html
    │       │           │   package-summary.html
    │       │           │   package-tree.html
    │       │           │   package-use.html
    │       │           │
    │       │           └───class-use
    │       │                   ExtremeMemoryV1_DiegoLuengo.html
    │       │
    │       ├───legal
    │       │       COPYRIGHT
    │       │       jquery.md
    │       │       jqueryUI.md
    │       │       LICENSE
    │       │
    │       ├───resources
    │       │       glass.png
    │       │       x.png
    │       │
    │       └───script-dir
    │               jquery-3.7.1.min.js
    │               jquery-ui.min.css
    │               jquery-ui.min.js
    │
    └───test-classes
        └───dev
            └───diego
```

## Requisitos del Sistema

- **Java Development Kit (JDK)** versión 21 o superior.
- Entorno de desarrollo integrado (IDE) recomendado: Visual Studio Code.
- **MySQL** con la base de datos creada

## Instrucciones de Ejecución

1. Abre el proyecto en **Visual Studio Code**.
2. Crear la base de datos con el script ubicado en  **./src/main/java/dev/diego/baseDeDatos**
3. Cambiar las credenciales de la base de datos en el archivo ubicado en **./src/main/java/dev/diego/baseDeDatos/ConexionBD.java**
4. Asegúrate de que JDK 21 esté correctamente configurado.
5. Ve a la carpeta `src/main/java/dev/diego/juegoPrincipal` y ejecuta el archivo `ExtremeMemoryV1_DiegoLuengo.java`.
6. Compila y ejecuta el programa:

## Funcionalidades Principales

- **Tablero dinámico**: Se muestra un tablero actualizado después de cada selección.
- **Validaciones**: Impide movimientos inválidos (casillas ocupadas o fuera de rango).
- **Condiciones de victoria**: Verifica automáticamente si el jugador ha ganado.
- **Colores**: Diferencia las cartas ya acertadas de las seleccionadas y las demás.
- **Guardar Partida**: Permite guardar el estado actual del juego para continuar más tarde desde el mismo punto.
- **Gestión de Perfiles**: Los jugadores pueden crear, modificar y eliminar sus perfiles, cada uno con su propio historial de partidas guardadas.
- **Cargar Partida**: Opción para cargar una partida previamente guardada desde el sistema de archivos o base de datos.
- **Base de Datos**: Integración con una base de datos para almacenar y recuperar perfiles y partidas de forma persistente.