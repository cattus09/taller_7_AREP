# TALLER 7: APLICACIÓN DISTRIBUIDA SEGURA EN TODOS SUS FRENTES

## Descripción de la aplicación 

Este laboratorio explora la implementación de un certificado SSL para garantizar la seguridad en la comunicación entre el cliente y el servidor mediante el protocolo HTTPS. La aplicación desarrollada permite a los usuarios autenticarse. Para su desarrollo, se emplea el framework [SparkJava](https://sparkjava.com/), conocido por su facilidad de uso y eficiencia.

La infraestructura de la aplicación se despliega en el entorno de [AWS](https://aws.amazon.com/) (Amazon Web Services), utilizando dos máquinas virtuales EC2. Una de ellas aloja el servidor de autenticación, encargado de gestionar la seguridad de acceso, mientras que la otra máquina aloja el servidor de la aplicación.

### Prerequisitos

- [Git](https://git-scm.com/) - Control de versiones
- [Maven](https://maven.apache.org/) - Gestor de dependencias
- [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación

### Instalacion prerequisitos AWS (EN LAS DOS INSTANCIAS)

#### Instalacion de git:

```bash
sudo yum install -y git
```
#### Instalacion de java:
```bash
sudo yum install -y java-17-amazon-corretto-devel
```

#### Instalación del repositorio

```bash
git clone https://github.com/cattus09/taller_7_AREP.git
```

### Ejecutando la aplicación 

En la raiz del proyecto ejecute:

```bash
mvn clean install
```

Para ejecutar el proyecto se debe ejecutar el siguiente comando:

En la primera instancia ejecute:

```bash
java -cp "target/classes:target/dependency/*" org.example.controllers.Login
```

En la siegunda instancia ejecute:

```bash
java -cp "target/classes:target/dependency/*" org.example.controllers.MainPage
```

En el navegador ejecute la URL de la instancia en la que uso el login, SI SE ESTA HACIENDO DE FORMA LOCAL ENTE A: [https://localhost:5000](https://localhost:5000).

**Usuario:** ***admin***

**Contraseña:** ***admin***


## Generando Javadoc 

Para generar la documentación de la aplicación, ejecute el siguiente comando, los archivos Javadoc se generarán en el directorio `target/site/apidocs` dentro del proyecto.

```bash
mvn site
```

Despues de ejecutar el comando anterior, abra el archivo `index.html` que se encuentra en el directorio `target/site/` con su navegador de preferencia luego búsque la sección **project reports** y haga click en la opción que dice `Project Javadoc` para ver la documentación de la aplicación.

## Arquitecura de la aplicación 

**LoginApp**

La aplicación de autenticación es un servidor que recibe peticiones `HTTPS` en el puerto `5000`. Tiene un certificado `SSL` que le permite recibir las peticiones. El servidor recibe peticiones `POST` en la ruta `/login`. Se comunica con **MainPage**.

![image](https://github.com/ELS4NTA/AREP-LAB-07/assets/99996670/e2f11edb-23fc-4563-98e1-69ceb8c84a2c)

## Video de despliegue en AWS
https://youtu.be/Xqm0liUGgdk