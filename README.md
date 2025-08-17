# Challenge-literalura

# Literalura – Gestión de Libros

**Literalura** es una aplicación de consola en Java que permite gestionar libros y autores utilizando una base de datos MySQL. Los datos de los libros se obtienen desde la API *
El proyecto se centra en funcionalidades principales de búsqueda, registro y consulta de libros y autores.

## Requisitos funcionales

1. **Buscar libro por título (API Gutendex)**
   - El usuario ingresa el título de un libro.  
   - La aplicación consulta la API Gutendex.  
   - Si encuentra resultados: muestra título, autor (apellido primero), idioma y número de descargas.  
   - Registra el libro en la base de datos (sin duplicados).  
   - Si no encuentra resultados, muestra: *“El libro no fue encontrado”*.

2. **Listar libros registrados**
   - Muestra todos los libros almacenados con su título, autor, idioma y descargas.

3. **Listar autores registrados**
   - Cada autor aparece una sola vez, incluso si tiene varios libros.

4. **Listar autores vivos en un determinado año**
   - El usuario ingresa un año y se muestran los autores que estaban vivos en ese año.

5. **Listar libros por idioma**
   - El usuario ingresa un código de idioma (EN, ES, FR, PT).  
   - Se muestran los libros de la base de datos que coinciden con el idioma.
  
##  Menú de la aplicación

1. Buscar libro por título

2. Listar libros registrados

3. Listar autores registrados

4. Listar autores vivos en un determinado año

5. Listar libros por idioma

6. Salir
     
## Estructura 
libros/
- Conexion.java --- Clase que gestiona la conexión con MySQL
- Autor.java --- Clase entidad Autor
- Libro.java --- Clase entidad Libro
- LibroDAO.java --- Clase que maneja las operaciones de libros en la base de datos
- Libros.java --- Clase principal con el menú de consola


## Base de datos

Nombre de la base: **literalura**  
Integra un archivo llamado "Base de datos. txt" con las tablas principales 

## Cómo ejecutar y requisitos

1. Instalar un gestor de base de datos y asegurarse de que MySQL está corriendo.  
2. Crear la base de datos y las tablas usando el script SQL incluido.  
3. Agregar el **MySQL JDBC Driver** (`mysql-connector-java`) al proyecto.  
4. Configurar `Conexion.java` con tu usuario/contraseña de MySQL.  
5. Ejecutar la clase `Libros.java` para iniciar la aplicación de consola.  



