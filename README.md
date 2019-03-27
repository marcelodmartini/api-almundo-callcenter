# API-ALMUNDO-CALLCENTER

### DOCUMENTATION:
To access the documentation click here [Documentation](https://marcelodmartini.github.io/api-almundo-callcenter/)

### REQUIREMENTS:
* Java 8 +
* Maven 3.3 +

### Execution and Local Installation:
STEP 1)
Clone the repository

```sh
$ git clone git@github.com:marcelodmartini/api-almundo-callcenter.git
```

STEP 2)
Then enter the project api-almundo-callcenter and execute the following command:

```sh
$ cd api-almundo-callcenter/
$ mvn clean install
```
STEP 3)
Then we enter the directory api-almundo-callcenter/target and to execute the application we execute the command:

```sh
$ cd api-almundo-callcenter/target/
$ java -jar api-almundo-callcenter-0.0.1-SNAPSHOT.jar
```

STEP 4)
To initialize the CallCenter and start receiving calls, we must invoke Postman or CURL as follows:

```sh
$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/start
```
With this the application is ready to receive calls and be able to consult the statements of employees and calls.

STEP 5)
To create calls, the format and / or structure of the JSON should be as follows:
```sh
{
	"client": {
	 			"name": "Marcelo",
	 			"surname": "Martini",
	 			"age": 32,
	 			"dni": 2324234
	}
}
```

To execute the call we execute the following command either by CURL or POSTMAN

```sh
$ curl -XPOST -d '{"client": {"name": "Marcelo","surname": "Martini","age": 32,"dni": 2324234}}' -H 'Content-Type: application/json' http://localhost:8090/api/call
```

STEP 6)
To consult a call through an idCall in the example, consult the idCall = 0:

```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/call/0
```

STEP 7)
To consult all calls:

```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/calls
```

STEP 8)
To consult an employee through an idEmployee in the example, consult the idEmployee = 0:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employee/0
```

STEP 9)
To consult all employees:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employees
```

STEP 10)
To stop the CallCenter and stop receiving calls, we must make the invocation by Postman or CURL as follows:
```sh
$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/stop
```
STEP 11)
To check the status of the Call Center:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/api/status
```

------------------------------------------------------------------------------------------------------------------------------------------------

### Execution with DOCKER:
STEP 1)
Clone the repository

```sh
$ git clone git@github.com:marcelodmartini/api-almundo-callcenter.git
```

STEP 2)
Then enter the project api-almundo-callcenter and execute the following command:

```sh
$ cd api-almundo-callcenter/
$ mvn clean install
```

STEP 3)
Within the project api-almundo-callcenter we execute the following command to create the image:
```sh
$ docker build -t api-almundo-callcenter .
```

STEP 4)
Run container in detach mode and public ports 

```sh
$ docker run -d -p 8080:8090 -p 80:8090 -p 8090:8090 api-almundo-callcenter
```

STEP 5)
To initialize the CallCenter and start receiving calls, we must invoke Postman or CURL as follows:

```sh
$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/start
```
With this the application is ready to receive calls and be able to consult the statements of employees and calls.

STEP 6)
To create calls, the format and / or structure of the JSON should be as follows:
```sh
{
	"client": {
	 			"name": "Marcelo",
	 			"surname": "Martini",
	 			"age": 32,
	 			"dni": 2324234
	}
}
```

To execute the call we execute the following command either by CURL or POSTMAN

```sh
$ curl -XPOST -d '{"client": {"name": "Marcelo","surname": "Martini","age": 32,"dni": 2324234}}' -H 'Content-Type: application/json' http://localhost:8090/api/call
```

STEP 7)
To consult a call through an idCall in the example, consult the idCall = 0:

```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/call/0
```

STEP 8)
To consult all calls:

```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/calls
```

STEP 9)
To consult an employee through an idEmployee in the example, consult the idEmployee = 0:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employee/0
```

STEP 10)
To consult all employees:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employees
```

STEP 11)
To stop the CallCenter and stop receiving calls, we must make the invocation by Postman or CURL as follows:
```sh
$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/stop
```
STEP 13)
To check the status of the Call Center:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/api/status
```

STEP 13)
To stop the container we execute.
```sh
$ docker stop image-id
```
---------------------------------------------------------------------------------------------------------------------------------------------------

### Doing the build based on an image already uploaded in the registry TREESCALE

STEP 1)
Login (Username and password will be sent by mail):
```sh
$ docker login repo.treescale.com
```
STEP 2)
Within the project api-almundo-callcenter we execute the following command to create the image:
```sh
$ docker build -t api-almundo-callcenter -f Dockerfile.production .
```
STEP 3)
Run container in detach mode and public ports 
```sh
$ docker run -d -p 8080:8090 -p 80:8090 -p 8090:8090 api-almundo-callcenter
```
STEP 4)
To initialize the CallCenter and start receiving calls, we must invoke Postman or CURL as follows:

```sh
$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/start
```
With this the application is ready to receive calls and be able to consult the statements of employees and calls.

STEP 5)
To create calls, the format and / or structure of the JSON should be as follows:
```sh
{
	"client": {
	 			"name": "Marcelo",
	 			"surname": "Martini",
	 			"age": 32,
	 			"dni": 2324234
	}
}
```

To execute the call we execute the following command either by CURL or POSTMAN

```sh
$ curl -XPOST -d '{"client": {"name": "Marcelo","surname": "Martini","age": 32,"dni": 2324234}}' -H 'Content-Type: application/json' http://localhost:8090/api/call
```

STEP 6)
To consult a call through an idCall in the example, consult the idCall = 0:

```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/call/0
```

STEP 7)
To consult all calls:

```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/calls
```

STEP 8)
To consult an employee through an idEmployee in the example, consult the idEmployee = 0:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employee/0
```

STEP 9)
To consult all employees:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/employees
```

STEP 10)
To stop the CallCenter and stop receiving calls, we must make the invocation by Postman or CURL as follows:
```sh
$ curl -XPOST -H 'Content-Type: application/json' http://localhost:8090/api/stop
```
STEP 11)
To check the status of the Call Center:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8090/api/api/status
```

STEP 12)
To stop the container we execute.
```sh
$ docker stop image-id
```

------------------------------------------------------------------------------------------------------------------------------------------------------


### Using POSTMAN or CURL to run the application already deploys on Amazon AWS.

An EC2 free tier instance was created, with the application.
Qualifications:
- It is not in HA.
- It does not have ALB, NLB.
- It has no dns record created in Route53.
- There is no Cache.


STEP 1)
To initialize the CallCenter and start receiving calls, we must invoke Postman or CURL as follows:

```sh
$ curl -XPOST -H 'Content-Type: application/json' http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/start
```
With this the application is ready to receive calls and be able to consult the statements of employees and calls.

STEP 2)
To create calls, the format and / or structure of the JSON should be as follows:
```sh
{
	"client": {
	 			"name": "Marcelo",
	 			"surname": "Martini",
	 			"age": 32,
	 			"dni": 2324234
	}
}
```

To execute the call we execute the following command either by CURL or POSTMAN

```sh
$ curl -XPOST -d '{"client": {"name": "Marcelo","surname": "Martini","age": 32,"dni": 2324234}}' -H 'Content-Type: application/json' http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/call
```

STEP 3)
To consult a call through an idCall in the example, consult the idCall = 0:

```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/call/0
```

STEP 4)
To consult all calls:

```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/calls
```

STEP 5)
To consult an employee through an idEmployee in the example, consult the idEmployee = 0:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/employee/0
```

STEP 6)
To consult all employees:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/employees
```

STEP 7)
To stop the CallCenter and stop receiving calls, we must make the invocation by Postman or CURL as follows:
```sh
$ curl -XPOST -H 'Content-Type: application/json' http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/stop
```

STEP 8)
To check the status of the Call Center:
```sh
$ curl -XGET -i -H "Accept: application/json" -H "Content-Type: application/json" http://ec2-18-191-146-2.us-east-2.compute.amazonaws.com:80/api/status
```

Architecture
----
In the following [link](architecture.md) you will find the Architecture Documentation.

License
----


**Free Software, Marcelo Martini !**
