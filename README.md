# 📚 Literalura - Catálogo de Libros
![Java](https://img.shields.io/badge/java-21-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.2-%236DB33F.svg?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Status](https://img.shields.io/badge/Status-Completado-success?style=for-the-badge)


Bienvenido a **Literalura**, una aplicación de consola desarrollada en Java que funciona como un catálogo interactivo de libros y autores. Este proyecto es parte del Challenge Back-End del programa Oracle Next Education (ONE) y Alura Latam.

La aplicación consume la [API de Gutendex](https://gutendex.com/) para buscar información literaria en tiempo real, deserializa los datos JSON y los almacena de forma persistente en una base de datos relacional para su posterior consulta y análisis.

---
## 🚀 Características Principales

El sistema cuenta con un menú interactivo que permite realizar las siguientes operaciones:

1. **Buscar libro por título:** Consulta la API externa, recupera la información del libro, verifica que no existan duplicados y lo guarda en la base de datos local.
2. **Buscar Autor:** Permite buscar los libros registrados de un autor específico ingresando su nombre.
3. **Top 5 libros más descargados:** Genera un ranking con los 5 libros más populares almacenados en la base de datos.
4. **Lista de todos los libros:** Muestra el catálogo completo de libros registrados con un formato de tarjeta amigable.
5. **Listar autores vivos en determinado año:** A través de *Derived Queries*, filtra y muestra los autores que se encontraban vivos en un año específico ingresado por el usuario.
6. **Lista de todos los autores:** Muestra todos los autores almacenados en la base de datos.
7. **Estadísticas de idiomas:** Exhibe la cantidad exacta de libros registrados filtrados por idiomas específicos (Español, Italiano e Inglés).

## 🛠️ Tecnologías y Herramientas Utilizadas

* **Lenguaje:** Java 21
* **Framework:** Spring Boot (v4.0.2)
* **Persistencia de Datos:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL
* **Consumo de API:** `java.net.http.HttpClient`
* **Manejo de JSON:** Biblioteca Jackson (anotaciones `@JsonAlias` e `@JsonIgnoreProperties`)
* **Gestor de Dependencias:** Maven

## ⚙️ Configuración e Instalación

### Prerrequisitos
* Tener instalado Java JDK 21 o superior.
* Tener instalado y configurado PostgreSQL (puedes usar herramientas como pgAdmin).

### Pasos para ejecutar el proyecto

1. **Clonar el repositorio:**
   ```bash
   git clone <url-de-tu-repositorio>
   ```
2. **Configurar las variables de entorno:**  
   El proyecto utiliza variables de entorno para proteger las credenciales de la base de datos. Debes configurar las siguientes variables en tu entorno local o en tu IDE (ej. IntelliJ IDEA):
    * ``DB_HOST:`` localhost:5432 (o tu puerto configurado)
    * ``DB_USER:`` tu_usuario_de_postgres
    * ``DB_PASSWORD:`` tu_contraseña_de_postgres  
    

3. **Crear la base de datos:**  
   Crea una base de datos en PostgreSQL llamada ``literalura``. Las tablas se generarán automáticamente gracias a la propiedad ``update`` de Hibernate.  
  

4. **Ejecutar la aplicación:**  
   Ejecuta la clase principal ``LiteraluraApplication.java`` para inicializar el menú en la consola.

---
### ✒️ Autor
* **Nicole Fernández (Zhainy)** - *Desarrolladora Full Stack Java* en formación