## Api de registro y gestión de usuarios

### Build y ejecución

1) Cómo prerrequisito para la ejecución del proyecto se deben tener instalados:

* JDK 8 +

```
   https://www.oracle.com/co/java/technologies/javase/javase8-archive-downloads.html
```

* Maven  

```
   https://maven.apache.org
```

* Git y git bash	

```
   https://github.com
```

2) Desde la terminal clonar el proyecto

```
   $ git clone https://github.com/rubenmorc7/nisum-user-api.git
```

3) Luego cambiar directorio del proyecto 

```
   $ cd nisum-user-api/ 
```

4) Realizar el build del proyecto

```
   $ mvn install
```

5) Ejecutar el proyecto

```
   $ mvn spring-boot:run
```

6) Desplegar en el browser la documentación swagger  

```
    http://localhost:8080/swagger-ui.html
```
![](Aspose.Words.e64bfa96-5670-4667-8c11-4f64a36ddfab.008.png)

## Consumo de los servicios del API

- Creación de un usuario

```
curl -X 'POST' \
  'http://localhost:8080/users/create-user' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "Ruben Moreno",
  "email": "ruben@moreno.cl",
  "password": "Hawk123@",
  "phones": [
    {
      "number": "3333333",
      "citycode": "11",
      "countrycode": "57"
    }
  ]
}'
```

- Respuesta
```
{
  "id": "faa673fb-2809-4035-93ee-251c79b614b1",
  "created": "2022-06-27",
  "modified": "2022-06-27 18:40",
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiaHR0cDovL25pc3VtLmNvbSIsIm5hbWUiOiJSdWJlbiBNb3Jlbm8iLCJlbWFpbCI6InJ1YmVuQG1vcmVuby5jbCIsImlhdCI6MTQ2Njc5NjgyMiwiZXhwIjo0NjIyNDcwNDIyfQ.yqiie-tNtf-SZ0wzrB4Iyi7mFwFLiFaO_CbmuZFVjzI",
  "isActive": true,
  "last_login": "2022-06-27 18:40"
}
```
- Consultar un usuario existente por email

```
curl -X 'GET' \
  'http://localhost:8080/users/findByEmail?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiaHR0cDovL25pc3VtLmNvbSIsIm5hbWUiOiJSdWJlbiBNb3Jlbm8iLCJlbWFpbCI6InJ1YmVuQG1vcmVuby5jbCIsImlhdCI6MTQ2Njc5NjgyMiwiZXhwIjo0NjIyNDcwNDIyfQ.yqiie-tNtf-SZ0wzrB4Iyi7mFwFLiFaO_CbmuZFVjzI&email=ruben%40moreno.cl' \
  -H 'accept: application/json'
```
- Respuesta

```
{
    "uuid": "faa673fb-2809-4035-93ee-251c79b614b1",
    "name": "Ruben Moreno",
    "email": "ruben@moreno.cl",
    "password": "Hawk123@",
    "phones": [
        {
            "number": "3333333",
            "cityCode": "11",
            "countryCode": "57"
        }
    ],
    "created": "2022-06-27",
    "modified": "2022-06-27T18:40:32.491",
    "lastLogin": "2022-06-27T18:40:32.491",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiaHR0cDovL25pc3VtLmNvbSIsIm5hbWUiOiJSdWJlbiBNb3Jlbm8iLCJlbWFpbCI6InJ1YmVuQG1vcmVuby5jbCIsImlhdCI6MTQ2Njc5NjgyMiwiZXhwIjo0NjIyNDcwNDIyfQ.yqiie-tNtf-SZ0wzrB4Iyi7mFwFLiFaO_CbmuZFVjzI",
    "isActive": true
}
```

##Tecnologías utilizadas

- JDK 8 +
- Spring Boot
- JPA
- Hibernate
- H2
- Maven 
- Git
- Autenticación con token JWT
- Pruebas unitarias con Junit

<!DOCTYPE html>
<html>
<body>

<h4>Se adjuntan en la carpeta del proyecto diagrama de la solución y colección para consumo de los servicios</h4>

<table style="width:100%;border:1px solid black;color:blue;">
  <tr>
    <th>Autor</th>
    <th>Profesion</th>
    <th>Fecha</th>
  </tr>
  <tr>
    <td>Rubén Darío Moreno</td>
    <td>Ingeniero de Sistemas</td>
    <td>27 de Julio de 2022</td>
  </tr>
</table>

</body>
</html>








