# -------------------------------------------------------------------
# PRUEBA TÉCNICA
# Miguel Ángel Sánchez Armero
# -------------------------------------------------------------------

Descripción
------------
En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) 
y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. 

Propósito
-----------
Se pide:
	Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:
		Acepte como parámetros de entrada: 
				fecha de aplicación, 
				identificador de producto, 
				identificador de cadena.
		Devuelva como datos de salida: 
				identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Solución implementada
------------------------
Finalmente se han implementado 3 tipos de end points diferentes, según cada cuanto tiempo se pueda pedir la información requerida. 
Todos mantienen una respuesta con la estructura requerida.

Caso 1: 
	Entrada: fecha, producto y cadena; 
	Respuesta: devuelve el precio de mayor prioridad para esa hora en concreto.
	Notas: Este caso es idóneo si se tiene la opción de llamar a este endpoint cada vez que sea necesaria.
Caso 2: 
	Entrada: fecha, producto y cadena; 
	Respuesta: devuelve la lista de precios válidos, a partir de la fecha dada
	Notas: De haber un solapamiento, se elimina, devuelve todos los precios válidos a partir de la fecha dada permitiendo ver qué precio y a qué hora 
			concreta se aplica cada uno de estos precios. Estos precios van ordenados por fecha de inicio de aplicación.
			Esta opción permite proveer al usuario cuales serán los próximos precios a aplicar; sin necesidad de tener que hacer otra request.
Caso 3: Se piden todos los precios
	Entrada: sin parámetros. 
	Respuesta: listado de precios almacenados en la base de datos, ordenados por prioridad y habiendo eliminado solapamientos en las fechas de aplicación de cada precio.
	Notas: Este nos permite ver las fluctuaciones del precio de un producto en una cadena concreta a lo largo del tiempo.


Detalle técnico
-----------------
- JDK 1.8+
- Apache Maven 3.6.3+


Documentación
---------------
Memoria: doc/Memoria.docx
Colección de Postman: doc/Inditex_amaris.postman_collection
