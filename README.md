# api-almundo-callcenter
Call center for almundo Multithreading

DOCUMENTACION:
Para acceder a la documentacion haga click aqui(api-almundo-callcenter\doc\index.html)

REQUERIMIENTOS:
Java 8+
Maven 3.3+

Ejecucion Local:
PASO 1)
Clonar el repositorio
$ git clone ......


PASO 2)
Luego ingresaos al proyecto api-almundo-callcenter y ejecutamos el siguiente comando:

$ mvn clean install

PASO 3)
Luego ingresamos al directorio api-almundo-callcenter/target y para ejecutar la aplicacion ejecutamos el comando:

$ java -jar api-almundo-callcenter-0.0.1-SNAPSHOT.jar

PASO 4)
Para inicializar el CallCenter y comenzar a recibir llamadas, debemos hacer la invocacion por Postman o CURL de la siguiente manera:

$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/start

Con esto la aplicacion se encuentra lista oara recibir llamadas y poder consultar los estados de los empleados y las llamadas.

PASO 5)
Para crear llamadas, el formato y/o estructura del JSON deberia ser de la siguiente manera:

{
	"client": {
	 			"name": "Marcelo",
	 			"surname": "Martini",
	 			"age": 32,
	 			"dni": 2324234
	}
}

Para ejecutar la llamada ejecutamos el siguiente comando ya sea por CURL o POSTMAN

$ curl -XPOST -d '{"client": {"name": "Marcelo","surname": "Martini","age": 32,"dni": 2324234}}' -H 'Content-Type: application/json' http://localhost:8090/api/call

PASO 6)
Para consultar una llamada a traves de un idCall en el ejemplo se consulta por el idCall=0 :

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/call/0

PASO 7)
Para consultar todas las llamadas:

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/calls

PASO 8)
Para consultar una empleado a traves de un idEmployee en el ejemplo se consulta por el idEmployee=0 :

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employee/0

PASO 9)
Para consultar todos los empleados:
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employees

Paso 10)
Para parar el CallCenter y dejar de recibir llamadas, debemos hacer la invocacion por Postman o CURL de la siguiente manera:

$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/stop

------------------------------------------------------------------------------------------------------------------------------------------------

Ejecucion con DOCKER:
PASO 1)
Clonar el repositorio
$ git clone ......


PASO 2)
Luego ingresaos al proyecto api-almundo-callcenter y ejecutamos el siguiente comando:

$ mvn clean install

PASO 3)
Dentro del proyecto api-almundo-callcenter ejecutamos el siguiente comando para crear la imagen:

$ docker build -t api-almundo-callcenter .

Paso 4)
Run container in detach mode and public ports 

$ docker run -d -p 8080:8090 -p 80:8090 -p 8090:8090 api-almundo-callcenter

PASO 5)
Para inicializar el CallCenter y comenzar a recibir llamadas, debemos hacer la invocacion por Postman o CURL de la siguiente manera:

$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/start

Con esto la aplicacion se encuentra lista oara recibir llamadas y poder consultar los estados de los empleados y las llamadas.

PASO 6)
Para crear llamadas, el formato y/o estructura del JSON deberia ser de la siguiente manera:

{
	"client": {
	 			"name": "Marcelo",
	 			"surname": "Martini",
	 			"age": 32,
	 			"dni": 2324234
	}
}

Para ejecutar la llamada ejecutamos el siguiente comando ya sea por CURL o POSTMAN

$ curl -XPOST -d '{"client": {"name": "Marcelo","surname": "Martini","age": 32,"dni": 2324234}}' -H 'Content-Type: application/json' http://localhost:8090/api/call

PASO 7)
Para consultar una llamada a traves de un idCall en el ejemplo se consulta por el idCall=0 :

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/call/0

PASO 8)
Para consultar todas las llamadas:

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/calls

PASO 9)
Para consultar una empleado a traves de un idEmployee en el ejemplo se consulta por el idEmployee=0 :

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employee/0

PASO 10)
Para consultar todos los empleados:
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employees

Paso 11)
Para parar el CallCenter y dejar de recibir llamadas, debemos hacer la invocacion por Postman o CURL de la siguiente manera:

$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/stop

Paso 12)
Para parar el contenedor ejecutamos.
$ docker stop image-id

---------------------------------------------------------------------------------------------------------------------------------------------------



Haciendo el build en base a una imagen ya subida en la registry TREESCALE

PASO 1)
Realizar Login:
$ docker login repo.treescale.com

PASO 2)
Dentro del proyecto api-almundo-callcenter ejecutamos el siguiente comando para crear la imagen:
$ docker build -t api-almundo-callcenter -f Dockerfile.production .

Paso 3)
Run container in detach mode and public ports 

$ docker run -d -p 8080:8090 -p 80:8090 -p 8090:8090 api-almundo-callcenter

PASO 4)
Para inicializar el CallCenter y comenzar a recibir llamadas, debemos hacer la invocacion por Postman o CURL de la siguiente manera:

$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/start

Con esto la aplicacion se encuentra lista oara recibir llamadas y poder consultar los estados de los empleados y las llamadas.

PASO 5)
Para crear llamadas, el formato y/o estructura del JSON deberia ser de la siguiente manera:

{
	"client": {
	 			"name": "Marcelo",
	 			"surname": "Martini",
	 			"age": 32,
	 			"dni": 2324234
	}
}

Para ejecutar la llamada ejecutamos el siguiente comando ya sea por CURL o POSTMAN

$ curl -XPOST -d '{"client": {"name": "Marcelo","surname": "Martini","age": 32,"dni": 2324234}}' -H 'Content-Type: application/json' http://localhost:8090/api/call

PASO 6)
Para consultar una llamada a traves de un idCall en el ejemplo se consulta por el idCall=0 :

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/call/0

PASO 7)
Para consultar todas las llamadas:

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/calls

PASO 8)
Para consultar una empleado a traves de un idEmployee en el ejemplo se consulta por el idEmployee=0 :

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employee/0

PASO 9)
Para consultar todos los empleados:
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employees

Paso 10)
Para parar el CallCenter y dejar de recibir llamadas, debemos hacer la invocacion por Postman o CURL de la siguiente manera:

$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/stop

Paso 11)
Para parar el contenedor ejecutamos.
$ docker stop image-id

------------------------------------------------------------------------------------------------------------------------------------------------------


Usando POSTMAN o CURL para ejecutar la aplicacion ya deploya en Amazon AWS.

Se creo una instancia EC2 free tier, con la aplicacion.
Salvedades:
.- La misma no esta en HA.
.- No tiene ALB, NLB.
.- No Tiene registro dns creado en Route53.
.- No hay Cache.


PASO 1)
Para inicializar el CallCenter y comenzar a recibir llamadas, debemos hacer la invocacion por Postman o CURL de la siguiente manera:

$ curl -XPOST -H 'Content-Type: application/json' http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/start

Con esto la aplicacion se encuentra lista oara recibir llamadas y poder consultar los estados de los empleados y las llamadas.

PASO 2)
Para crear llamadas, el formato y/o estructura del JSON deberia ser de la siguiente manera:

{
        "client": {
                                "name": "Marcelo",
                                "surname": "Martini",
                                "age": 32,
                                "dni": 2324234
        }
}

Para ejecutar la llamada ejecutamos el siguiente comando ya sea por CURL o POSTMAN

$ curl -XPOST -d '{"client": {"name": "Marcelo","surname": "Martini","age": 32,"dni": 2324234}}' -H 'Content-Type: application/json' http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/call

PASO 3)
Para consultar una llamada a traves de un idCall en el ejemplo se consulta por el idCall=0 :

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/call/0

PASO 4)
Para consultar todas las llamadas:

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/calls

PASO 5)
Para consultar una empleado a traves de un idEmployee en el ejemplo se consulta por el idEmployee=0 :

$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/employee/0

PASO 6)
Para consultar todos los empleados:
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/employees

Paso 7)
Para parar el CallCenter y dejar de recibir llamadas, debemos hacer la invocacion por Postman o CURL de la siguiente manera:

$ curl -XPOST -H 'Content-Type: application/json' http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/stop


