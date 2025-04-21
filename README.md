


# NTT Data

1.- Para levantar el microservicio, solo se debe descargar el proyecto, y tener instalado java 8.

2.- Abrir el proyecto con algun ide de preferencia intellij

3.- No es necesario cargar el scrypt de base de datos ya que se levanta solo con la configuracion en el yml 
`ddl-auto: update`

4.- Para utilizar los endpoint puede ver la documentacion en swagger [Documentacion](http://localhost:8080/swagger-ui/index.html)

5.- Dejo los cURL en caso de ser necesarios


## Pasos para probar el microservicio

Nota: los endpoint que estan bajo el path de "/api/v1/" necesitan token tipo Bearer. 

#### Guardar usuario

```sh
  curl --location 'http://localhost:8080/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nombre": "Juan Rodriguez",
    "correo": "juan@rodriguez.cl",
    "contrasena": "hunter2",
    "telefonos": [
        {
            "numero": "49138183",
            "codigoCiudad": "9",
            "codigoPais": "56"
        }
    ]
}'
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nombre`      | `string` | **Requerido**. Nombre de usuario |
| `correo`      | `string` | **Requerido**. Email de usuario|
| `contrasena`  | `string` | **Requerido**. Contraseña de inicio de sesion del usuario |
| `telefonos`      | `array<telefono>` | **Opcional**. Arreglo de telefono de 1 o mas|

Se crea un usuario nuevo. La contraseña debe tener un mínimo de 7 caracteres compuesto de mayusculas y numeros

------------------------------------------------------------
#### Login

```sh
  curl --location 'localhost:8080/user/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "email":"juan@rodriguez.cl",
        "password":"hunter2"
    }'
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `email`      | `string` | **Requerido**. Email del usuario|
| `password`      | `string` | **Requerido**. Contraseña inicio de sesion |

Inicio de sesion de usuario, si el usuario se encuentra en estado desactivado, no podra iniciar

------------------------------------------------------------
#### Informacion del usuario

```sh
  curl --location 'localhost:8080/api/v1/user-profile/email \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5jbCIsImlhdCI6MTc0NTIwODI4NH0._O-NbktwUKDMB7Hg8SuR7TCcV8VYHIaitRnfGijzj-EYSDbGDYCXJGBdr_FRlFiJ0PkuwYd4xVh_INvDn834jA'
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `email`      | `string` | **Requerido**. Email del usuario |

Se obtiene los datos personales del usuario.

------------------------------------------------------------
#### Actualizacion del usuario

```sh
  curl --location --request PUT 'localhost:8080/api/v1/user-update/id' \
--header 'Content-Type: application/json' \
--header 'Authorization: ••••••' \
--data '{
    "nombre": "eduardo Rodriguez"
}'

```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Requerido**. Uuid del usuario|
| `nombre`      | `string` | **Opcional**. Nombre de usuario |
| `email`      | `string` | **Opcional**. Email de usuario|
| `contrasena`      | `string` | **Opcional**. Contraseña de incio de sesion del usuario |

Se actualiza los datos del usuario, donde todos los campos son  opcionales del cuerpo.

------------------------------------------------------------
#### Desactivar usuario

```sh
  curl --location --request PATCH 'localhost:8080/api/v1/user-desactivate/id' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5jbCIsImlhdCI6MTc0NTEyMjc0MH0.78pLMSn7XaBM9tAqt5gI9fXsDa-329y9AM3x5PlmKqk7kp8h3hb0TEUWhknwifGRsmFdHTYMvePFUwqUhD-V9Q'

```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Requerido**. Uuid del usuario |

Se desactiva el usuario y asi no podra iniciar sesion.

------------------------------------------------------------
#### Eliminar usuario

```sh
  curl --location --request DELETE 'localhost:8080/api/v1/user-delete/id' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5jbCIsImlhdCI6MTc0NTEyMjE1M30.CXpKWUzUd1leOJpyNiZ09rZHdqHaICncjVCWDtFgvcoOMuC0HlupbhBKqkWBdW3IXhUT0f4XTJCcSsdQE94G_A'

```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Requerido**. Uuid del usuario|

Se elimina el usuario.