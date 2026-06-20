# miniblog-api
 
A small **REST API for a blog**, built with **Spring Boot** and **PostgreSQL**. It
exposes full CRUD operations for **users** and **posts**, with each post linked to
its author.
 
*Read this in [English](#english) · [Español](#español).*
 
---
 
## English
 
### 📖 Overview
 
**miniblog-api** is a backend REST API that models a minimal blog. It manages two
resources:
 
- **Users** — each with a unique `username` and a valid, unique `email`.
- **Posts** — each with a `title`, `content`, and an **author** (a reference to a
  user).
Persistence is handled with **Spring Data JPA / Hibernate** over a **PostgreSQL**
database, and incoming data is checked with **Bean Validation**.
 
### 🛠️ Tech stack
 
- **Java 17**
- **Spring Boot 4.1.0** (Web, Data JPA, Validation)
- **Hibernate** as the JPA provider
- **PostgreSQL** as the database
- **Lombok** to reduce boilerplate
- **Maven** (with the included Maven Wrapper)
### 🧱 Architecture
 
The project follows a classic layered structure:
 
```
controller  →  service  →  repository  →  database
```
 
- **model** — JPA entities (`User`, `Post`).
- **repository** — Spring Data interfaces (`JpaRepository`).
- **service** — business logic (CRUD, validation of the post's author).
- **controller** — REST endpoints under `/api`.
### 📡 Endpoints
 
#### Users — `/api/user`
 
| Method   | Route             | Description              |
| -------- | ----------------- | ------------------------ |
| `GET`    | `/api/user`       | List all users.          |
| `GET`    | `/api/user/{id}`  | Get a user by id.        |
| `POST`   | `/api/user`       | Create a user.           |
| `PUT`    | `/api/user/{id}`  | Update a user.           |
| `DELETE` | `/api/user/{id}`  | Delete a user.           |
 
#### Posts — `/api/post`
 
| Method   | Route             | Description              |
| -------- | ----------------- | ------------------------ |
| `GET`    | `/api/post`       | List all posts.          |
| `GET`    | `/api/post/{id}`  | Get a post by id.        |
| `POST`   | `/api/post`       | Create a post.           |
| `PUT`    | `/api/post/{id}`  | Update a post.           |
| `DELETE` | `/api/post/{id}`  | Delete a post.           |
 
### 📦 Example requests
 
Create a user:
 
```json
POST /api/user
{
  "username": "chema",
  "email": "chema@example.com"
}
```
 
Create a post (the author is referenced by id; the user must already exist):
 
```json
POST /api/post
{
  "title": "My first post",
  "content": "Hello world!",
  "autor": { "id": 1 }
}
```
 
> Note: the author field is named `autor` in the JSON, matching the entity.
 
### ⚙️ Configuration
 
Database settings live in `src/main/resources/application.properties`:
 
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/miniblog
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```
 
Make sure you have a PostgreSQL database named `miniblog` (or adjust the URL,
username, and password). With `ddl-auto=update`, Hibernate creates and updates the
tables automatically.
 
### 🚀 Running the project
 
```bash
cd miniblog
 
# Using the Maven Wrapper (no global Maven needed)
./mvnw spring-boot:run        # Linux / macOS
mvnw.cmd spring-boot:run      # Windows
```
 
The API starts on `http://localhost:8080` by default.
 
---
 
## Español
 
### 📖 Descripción
 
**miniblog-api** es una API REST de backend que modela un blog mínimo. Gestiona dos
recursos:
 
- **Usuarios (users)** — cada uno con un `username` único y un `email` válido y
  único.
- **Publicaciones (posts)** — cada una con `title`, `content` y un **autor** (una
  referencia a un usuario).
La persistencia se gestiona con **Spring Data JPA / Hibernate** sobre una base de
datos **PostgreSQL**, y los datos de entrada se comprueban con **Bean Validation**.
 
### 🛠️ Tecnologías
 
- **Java 17**
- **Spring Boot 4.1.0** (Web, Data JPA, Validation)
- **Hibernate** como proveedor JPA
- **PostgreSQL** como base de datos
- **Lombok** para reducir código repetitivo
- **Maven** (con el Maven Wrapper incluido)
### 🧱 Arquitectura
 
El proyecto sigue una estructura clásica por capas:
 
```
controller  →  service  →  repository  →  base de datos
```
 
- **model** — entidades JPA (`User`, `Post`).
- **repository** — interfaces de Spring Data (`JpaRepository`).
- **service** — lógica de negocio (CRUD, validación del autor del post).
- **controller** — endpoints REST bajo `/api`.
### 📡 Endpoints
 
#### Usuarios — `/api/user`
 
| Método   | Ruta              | Descripción                   |
| -------- | ----------------- | ----------------------------- |
| `GET`    | `/api/user`       | Listar todos los usuarios.    |
| `GET`    | `/api/user/{id}`  | Obtener un usuario por id.    |
| `POST`   | `/api/user`       | Crear un usuario.             |
| `PUT`    | `/api/user/{id}`  | Actualizar un usuario.        |
| `DELETE` | `/api/user/{id}`  | Eliminar un usuario.          |
 
#### Publicaciones — `/api/post`
 
| Método   | Ruta              | Descripción                   |
| -------- | ----------------- | ----------------------------- |
| `GET`    | `/api/post`       | Listar todas las publicaciones.|
| `GET`    | `/api/post/{id}`  | Obtener una publicación por id.|
| `POST`   | `/api/post`       | Crear una publicación.        |
| `PUT`    | `/api/post/{id}`  | Actualizar una publicación.   |
| `DELETE` | `/api/post/{id}`  | Eliminar una publicación.     |
 
### 📦 Ejemplos de petición
 
Crear un usuario:
 
```json
POST /api/user
{
  "username": "chema",
  "email": "chema@example.com"
}
```
 
Crear una publicación (el autor se referencia por id; el usuario debe existir ya):
 
```json
POST /api/post
{
  "title": "Mi primer post",
  "content": "¡Hola mundo!",
  "autor": { "id": 1 }
}
```
 
> Nota: el campo del autor se llama `autor` en el JSON, igual que en la entidad.
 
### ⚙️ Configuración
 
La configuración de la base de datos está en
`src/main/resources/application.properties`:
 
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/miniblog
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```
 
Asegúrate de tener una base de datos PostgreSQL llamada `miniblog` (o ajusta la URL,
el usuario y la contraseña). Con `ddl-auto=update`, Hibernate crea y actualiza las
tablas automáticamente.
 
### 🚀 Ejecución del proyecto
 
```bash
cd miniblog
 
# Usando el Maven Wrapper (no necesitas Maven instalado globalmente)
./mvnw spring-boot:run        # Linux / macOS
mvnw.cmd spring-boot:run      # Windows
```
 
La API arranca por defecto en `http://localhost:8080`.
 
---
 
## 👤 Author / Autor
 
**jgodoy-m / chema**
 










