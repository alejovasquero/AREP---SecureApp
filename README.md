# Implementación de una arquitectura segura

Este proyecto consiste en la implementación de una arquitectura segura por medio del 
uso de llaves, y la validación de autenticación, autorización e integridad de datos.

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/c555b91484c64ec781fdf7b0832d80ec)](https://www.codacy.com/gh/alejovasquero/AREP---SecureApp/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=alejovasquero/AREP---SecureApp&amp;utm_campaign=Badge_Grade)
[![CircleCI](https://circleci.com/gh/alejovasquero/AREP---SecureApp.svg?style=svg)](https://circleci.com/gh/alejovasquero/AREP---SecureApp)
## Empezando

Estas instrucciones te utilizar la página web, compilar el proyecto y las pruebas.
Las instrucciones se limitan a compilación, ejecución y uso. Vamos a ver la manera de hacer que el software funcione
de manera local y por medio de Amazon Web Services.

### Prerrequisitos 

Para instalar y correr exitosamente este proyecto necesitamos:
* **Java**
* **Maven**
* **Git**

### Instalación

Primeramente vamos a descargar el repositorio en nuestra máquina local, y en la carpeta de 
nuestra preferencia. En consola vamos a digitar el siguiente comando para clonar el repositorio.

```console
git clone https://github.com/alejovasquero/AREP---SecureApp
```

Entremos a el directorio del proyecto

```console
cd AREP---SecureApp
```

Debemos compilar el proyecto, que contiene las clases necesarias para poder correr nuestro
proyecto. Por medio de maven vamos a crear todos los compilables **.class**. Desde consola, y ubicados en la carpeta donde se encuentra
nuestra configuración de maven.

```console
mvn package
```

Ahora que nuestras clases etan compiladas vamos a ejecutar la clase principal para
ver el código en acción : )

--------------------

### Despliegue en AWS


## Construido con

* [Maven](https://maven.apache.org/) - Manejo de dependencias
* [Git](https://git-scm.com/) - Control de versiones
* [Java](https://www.java.com/es/) - Lenguaje de programación
* [Spark](http://sparkjava.com/) - Framework de desarrollo web
* [MongoDB](https://www.mongodb.com/es) - Administración y manejo de bas de datos

## Autores

* **David Alejandro Vasquez Carreño** - *Trabajo inicial* - [alejovasquero](https://github.com/alejovasquero)

## Licencia

Este proyecto está licenciado bajo la licencia del MIT - Vea el [LICENSE](LICENSE) para más detalles

## Reconocimientos

* Daniel Benavides