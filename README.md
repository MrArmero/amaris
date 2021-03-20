# --------------------------------------------------------------------------------------
# PRUEBA TÉCNICA
# Miguel Ángel Sánchez Armero
# --------------------------------------------------------------------------------------


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
Finalmente se han implementado 3 tipos de end points diferentes, según cada cuanto tiempo se pueda pedir la información requerida. Todos mantienen una respuesta con la estructura requerida.

Caso 1: 
	Entrada: fecha, producto y cadena; 
	Respuesta: devuelve el precio de mayor prioridad para esa hora en concreto.
	Notas: Este caso es idóneo si se tiene la opción de llamar a este endpoint cada vez que sea necesaria.
Caso 2: 
	Entrada: fecha, producto y cadena; 
	Respuesta: devuelve un array de los precios válidos, ordenado por prioridad. En esta lista se habrán eliminado los solapamientos en las fechas entre precios en la base de datos.
	Notas: De haber un solapamiento, devuelve todos los precios válidos para la fecha dada permitiendo ver a qué hora concreta se aplica cada uno de estos precios.
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
Memoria: Memoria.docx

