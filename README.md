# Taller de Arquitectura Empresarial: Diseño de Aplicaciones de Seguridad

En este taller, se desarrolló una aplicación segura y escalable utilizando AWS, compuesta por dos componentes principales: un servidor Apache que gestiona el frontend y un servidor de Spring Framework que se encarga del backend.

![image](https://github.com/user-attachments/assets/f8bd8667-554d-4a1e-a083-e6698f2bea78)


## Seguridad

Para garantizar la seguridad, se implementó cifrado TLS utilizando certificados de Let's Encrypt en ambos servidores. Además, mediante Spring Security, se definieron las políticas de autenticación y autorización de usuarios. Las contraseñas se encriptaron utilizando la interfaz `PasswordEncoder` junto con el algoritmo de hashing bcrypt, lo que asegura un manejo seguro de las credenciales.

## Certificados

Certificado Apache:
![image](https://github.com/user-attachments/assets/eb4796f6-3675-4f34-aeec-c6900e371684)

Certificado Spring
![image](https://github.com/user-attachments/assets/524b323c-4dae-44c3-a7bb-d6fa50d9046c)


## Arquitectura

![image](https://github.com/user-attachments/assets/2e422c65-b6e5-443c-bd0e-0326b5334565)

### Servidor 1: Servidor Apache

El servidor Apache será responsable de brindar servicio a un cliente HTML+JavaScript asincrónico a través de una conexión segura mediante TLS. El código del lado del cliente se entregará a través de canales cifrados, lo que garantiza la integridad y confidencialidad de los datos durante la descarga.

### Servidor 2: Spring Framework

El servidor Spring se encargará de los servicios de backend y ofrecerá puntos finales de API RESTful. Estos servicios también estarán protegidos mediante TLS, lo que garantiza una comunicación segura entre el cliente y el backend. Se utilizó el patrón de diseño arquitectónico Modelo-Vista-Controlador.

### Componente Mongo (Base de datos)

Este componente es la base de datos Mongo implementada en el servidor de Spring, para que este pueda guardar informacion. El componente Spring se comunica con Mongo a través de una conexión de red utilizando Mongo Repository para realizar operaciones de lectura y escritura en la base de datos. 
### AWS

Se utilizan los servicios de EC2 para la creación de una instancia para cada uno de los servicios anteriores.

## Explicación de las Clases en Spring

- **RegistrationController**: Maneja las solicitudes relacionadas con el registro y la gestión de usuarios.
  - `addUser()`: Agrega un usuario (método para administradores) y redirige a una página específica.
    
- **UserController**: Maneja las solicitudes relacionadas con el registro y la gestión de usuarios.
  - `findUsers()`: Encuentra un usuario y redirige a una página específica con la informacion de este.
  - `updateUser()`: Actualiza un usuario  y redirige a una página específica.
  - `deleteUser()`: Elimina un usuario y redirige a una página específica.
  
- **MyUser**: Representa el modelo de datos del usuario en la aplicación.
  - Atributos: `id`, `username`, `password`, `role`.
  
- **MyUserRepository**: Interfaz que extiende `MongoRepository` para acceder a los datos de los usuarios.
  
- **SecurityConfiguration**: Configura la seguridad de la aplicación, estableciendo la autorización del usuario.
  
- **MyUserDetailService**: Implementa `UserDetailsService` para cargar usuarios desde la base de datos, poseyendo la lógica para la autenticación del usuario.
  
- **AuthenticationSuccessHandler**: Maneja el éxito de la autenticación y redirige al usuario según su rol.

  ## Ejemplo del GET /api/v1/user/{email}
  ![image](https://github.com/user-attachments/assets/dd47acbf-f19f-4335-90de-05a51351cea9)

### Requisitos Previos

Para ejecutar este proyecto, necesitarás tener instalado:

- Java JDK 21.
- Un IDE de Java como IntelliJ IDEA, Eclipse, o NetBeans.
- Maven para manejar las dependencias (preferiblemente la versión 3.9.4).
- Un navegador web para interactuar con el servidor.
- Contenedor Docker con la imagen de mongo corriendo antes de ejecutar el proyecto

### Instalación

1. Tener instalado Git en tu máquina local.
2. Elegir una carpeta en donde guardar el proyecto.
3. Abrir la terminal de GIT (clic derecho y seleccionar "Git bash here").
4. Clonar el repositorio en tu máquina local:
   ```bash
   git clone https://github.com/Medina95/TallerSeguridadArem.git
### IMPORTANTE:

En application.properties, cambia el spring.datasource.username por root o por un usuario que tengas predefinido.
asi como los certificados de seguridad, deben estar presentes ** bootsecurity y dependiendo del entorno de ejecucion. 
Es decir para que funcione localmente deberia verse asi:
![image](https://github.com/user-attachments/assets/329deff1-3c84-4024-9447-63654694ddfa)

No olvides tener un contenedor docker corriendo la base de datos mongo y de igual manera tenerla referenciada en application.properties

### Deployment

1. Abre el proyecto con tu IDE favorito o navega hasta el directorio del proyecto.
2. Desde la terminal, para compilar y empaquetar el proyecto, ejecuta:
   ```bash
   mvn clean install
3. Compila el proyecto que contiene el método MAIN: SpringbackApplication.java o ejecútalo desde la terminal:
   ```bash
   mvn spring-boot:run
4. Es posible interactuar con los endpoints de la API una vez implementada la autorizacion en postman o quitando la autorizacion de el archivo SecurityConfiguration
   ![image](https://github.com/user-attachments/assets/10b399d4-b1b9-4aa0-9575-0b1ec9c12430)
  

# Endpoints de la API

## UserController

### Obtener todos los usuarios

- **Endpoint:** `GET /api/v1/users`
- **Descripción:** Obtiene una lista de todos los usuarios.
- **Respuesta:**
  - **Código de estado:** `200 OK`
  - **Cuerpo:** Lista de objetos `MyUser`.

### Obtener un usuario por email

- **Endpoint:** `GET /api/v1/users/{email}`
- **Descripción:** Busca un usuario por su email y redirige a la URL con sus datos.
- **Parámetros:**
  - `email` (Path Variable): El email del usuario a buscar.
- **Redirección:**
  - Si el usuario es encontrado: Redirige a `https://taller-apache-security.duckdns.org/user.html` con los parámetros `name`, `password`, `id`, y `role`.
  - Si el usuario no es encontrado: Redirige a `https://taller-apache-security.duckdns.org/error.html`.

### Actualizar un usuario

- **Endpoint:** `PUT /api/v1/users/{email}`
- **Descripción:** Actualiza la información de un usuario existente.
- **Parámetros:**
  - `email` (Path Variable): El email del usuario a actualizar.
- **Cuerpo:**
  - Objeto `MyUser` con los nuevos datos del usuario.
- **Redirección:**
  - Redirige a `https://taller-apache-security.duckdns.org/index.html` si la actualización es exitosa.
- **Errores:**
  - **Código de estado:** `404 Not Found` si el usuario no es encontrado.

### Eliminar un usuario

- **Endpoint:** `DELETE /api/v1/users/{email}`
- **Descripción:** Elimina un usuario por su email.
- **Parámetros:**
  - `email` (Path Variable): El email del usuario a eliminar.
- **Redirección:**
  - Redirige a `https://taller-apache-security.duckdns.org/index.html` si la eliminación es exitosa.
- **Errores:**
  - **Código de estado:** `404 Not Found` si el usuario no es encontrado.

## RegistrationController

### Registrar un nuevo usuario

- **Endpoint:** `POST /api/v1/register`
- **Descripción:** Registra un nuevo usuario.
- **Cuerpo:**
  - Objeto `MyUser` con los datos del nuevo usuario.
- **Redirección:**
  - Redirige a `https://taller-apache-security.duckdns.org/index.html` si el registro es exitoso.

### DESPLIEGUE EN AWS

[Ver video de despliegue en AWS](https://drive.google.com/file/d/1-mtJHqsKAHRGVOoxh-xD5n-rLOUHXTtf/view?usp=sharing)

### Ejecucion de pruebas unitarias

`mvn test`

### Funcionamiento de las Pruebas Unitarias

## UserControllerTest

### Propósito
- Probar el comportamiento del controlador de usuarios (`UserController`), asegurando que las solicitudes HTTP se manejen correctamente y que las interacciones con los servicios se realicen según lo esperado.

### Métodos de Prueba

1. **testFindUsers**
   - **Descripción**: Verifica que la lista de usuarios se devuelva correctamente.
   - **Funcionamiento**:
     - Simula la respuesta del servicio para que devuelva una lista de usuarios.
     - Realiza una solicitud GET a `/api/v1/users`.
     - Asegura que el estado de la respuesta sea 200 OK.
     - Verifica que los datos del usuario devueltos sean correctos.

2. **testFindUserByEmail**
   - **Descripción**: Verifica que se redirija correctamente al usuario por su email.
   - **Funcionamiento**:
     - Simula la respuesta del servicio para que devuelva un usuario específico.
     - Realiza una solicitud GET a `/api/v1/users/{email}`.
     - Asegura que la respuesta sea una redirección (3xx).

3. **testFindUserByEmailNotFound**
   - **Descripción**: Verifica el manejo de errores cuando el usuario no se encuentra.
   - **Funcionamiento**:
     - Simula una excepción al intentar encontrar un usuario por email no existente.
     - Realiza una solicitud GET a `/api/v1/users/{unknownEmail}`.
     - Asegura que la respuesta sea una redirección a la página de error.

---

## RegistrationControllerTest

### Propósito
- Probar el comportamiento del controlador de registro (`RegistrationController`), asegurando que el registro de nuevos usuarios se realice correctamente.

### Métodos de Prueba

1. **testAddUser**
   - **Descripción**: Verifica que un nuevo usuario se registre correctamente y se redirija a la página adecuada.
   - **Funcionamiento**:
     - Crea un objeto de usuario y simula la codificación de su contraseña.
     - Realiza una solicitud POST a `/api/v1/register` con los datos del usuario.
     - Asegura que la respuesta sea una redirección (3xx) a la página de inicio.
     - Verifica que el método `save` del repositorio sea llamado una vez con el usuario correcto.

---

## Consideraciones Generales
- Las pruebas utilizan **`MockMvc`** para simular las solicitudes HTTP.
- **Mockito** se utiliza para simular comportamientos de servicios y repositorios.
- Se comprueba que las respuestas y redirecciones sean las esperadas en diferentes escenarios.

![image](https://github.com/user-attachments/assets/756a3215-d7db-43f2-9221-f132d5fa2bca)


### Built with 
- [Maven](https://maven.apache.org/)

### Authors 
- (https://github.com/ChristianDuarteR) 
