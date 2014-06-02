iAmbiental
==========

Casa domótica con inteligencia.

Tabla de contenido
==================

  - [Descarga](https://github.com/RaulMoyaReyes/iAmbiental#descarga)
  - [Instalación](https://github.com/RaulMoyaReyes/iAmbiental#instalaci%C3%B3n)
  - [Configuración](https://github.com/RaulMoyaReyes/iAmbiental#configuraci%C3%B3n)
  - [Ejecución](https://github.com/RaulMoyaReyes/iAmbiental#ejecuci%C3%B3n)

Descarga
========

Descarga la última versión estable del proyecto.
 - [Descargar](https://github.com/RaulMoyaReyes/iAmbiental/archive/master.zip)

Instalación
===========

El proyecto está preparado para ser ejecutado en Netbeans 8. Una vez descargado, lo descomprimimos y lo abrimos con Netbeans. Tenemos que abrir todos lo proyectos.

Configuración
=============

Hay que configurar dos proyectos. En servidorWeb, vamos al directorio WEB-INF y buscamos el archivo applicationcontext.xml. En el configuramos la base de datos con el usuario y contraseña. En clienteUso, debemos configuara la ip del servidor donde lo estamos ejecutando. Para ello, vamos al controlador principal dentro del paquete es.ujaen.iambiental.clienteuso.controladores. Además, dentro de la carpeta web, en el archivo main.js también debemos cambiar la ip.

Ejecución
=========

La primera aplicación que debemos ejecutar es servidorWeb. Este genera la base de datos. A continuación ejecutamos el sql con los datos de ejemplo. A continuación, podemos ejecutar el resto de proyectos.
