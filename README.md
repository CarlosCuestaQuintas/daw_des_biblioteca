# API Rest biblioteca con Spring

Esta es una API Rest desarrollada con Spring que gestiona los libros, usuarios y préstamos de una biblioteca.

## Configuración del proyecto

### Instalación del IDE
Nos descargamos las Spring Tools para Eclipse desde la [página oficial](https://spring.io/tools).

### Importación del proyecto

1. Abrimos las Spring Tools y elegimos una carpeta como el work station de Eclipse.
2. Creamos una carpeta dentro del work station para nuestra API.
3. Clonamos el repositorio en dicha carpeta.
4. Nos vamos a File --> Import --> Maven --> Existing Maven Projects y buscamos la carpeta de nuestro repositorio. En el explorador de paquetes de la parte izquierda debería aparecer nuestro proyecto.

### Ejecución de la API

1. Dentro de Eclipse desplegamos el contenido de nuestro proyecto en el explorador de paquetes.
2. Navegamos hasta la ruta src/main/java/com/biblioteca, donde veremos los archivos BibliotecaApplication.java y DataLoader.java
3. Hacemos clic derecho en BibliotecaApplication.java y clicamos en Run As --> Java Application. De manera alternativa, si Eclipse ya sabe que es ese archivo el que debe ejecutar podremos iniciar la API pulsando en el botón verde con un símbolo de play en la barra superior.

## Estructura del proyecto

### Estructura de archivos

Los archivos están separados en paquetes según la función de cada uno.

#### Paquete com.biblioteca

En este paquete tenemos BibliotecaApplication, que es el archivo principal de la API y DataLoader (más información abajo).

#### Controllers

En este paquete tenemos LibroController, PrestamoController y UsuarioController, que están marcados con la anotación @RestController y gestionan las posibles peticiones (con sus endpoints correspondientes) que admite la API, además de incluir anotaciones para la documentación OpenAPI. Cada uno de ellos hace uno de los servicios que necesita.

#### Services

En este paquete tenemos LibroService, PrestamoService y UsuarioService, marcados con la anotación @Service. En ellos se realiza la lógica de negocio principal, y hacen uso de los repositorios que necesitan.

#### Models

En este paquete tenemos Libro, Prestamo y Usuario, marcados con la anotación @Entity, que son los tipos de objetos básicos que maneja la API. Tienen incluidas anotaciones de validación.

#### Repositories

En este paquete tenemos LibroRepository, PrestamoRepository y UsuarioRepository, marcados con la anotación @Repository, que heredan de JpaRepository, y que se encargan de la persistencia de los datos, además de incluir algunas búsquedas personalizadas en la base de datos.

#### Exceptions

En este paquete tenemos un manejador global de excepciones (GlobalExceptionHandler) y algunas excepciones personalizadas: LibroNotFoundException, LibroSinExistenciasException y UsuarioNotFoundException.

Las excepciones personalizadas devuelven un breve mensaje al usuario.

GlobalExceptionHandler maneja esas excepciones personalizadas, además de sustituir las respuestas devueltas por defecto cuando no se encuentra un recurso (404, not found) y cuando no se hace una petición correctamente (400, bad request).

### Persistencia de datos

La API utiliza una base de datos H2 alojada en memoria volátil. El archivo DataLoader, marcado con la anotación @Component, se encarga de inicializar algunos datos de prueba en la base de datos.