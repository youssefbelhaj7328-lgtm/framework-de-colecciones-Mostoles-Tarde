package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) {

		/*
		 * Operaciones de Agregado, para recorrer y operar con los elementos de una
		 * coleccion * En el siguiente enlace se hace una breve introduccion a las
		 * Operaciones de Agregado
		 * https://docs.oracle.com/javase/tutorial/collections/interfaces/collection.
		 * html
		 * 
		 * Y en el link siguiente ya se abordan con mas profundidad:
		 * https://docs.oracle.com/javase/tutorial/collections/streams/index.html Las
		 * Operaciones de Agregado implican convertir la coleccion en un flujo (Stream)
		 * de elementos que comienzan a circular por una tuberia (pipeline), que podemos
		 * entenderla como una linea de produccion, por ejemplo, una cadena donde se
		 * rellenan latas de conserva, que en cada punto de la cadena se hace una
		 * operacion diferente, sobre la lata de conserva, es decir, un operador la
		 * rellena, otro le pone la etiqueta otro la cierra, otro la mete en una caja,
		 * etc.
		 * 
		 * Concretamente, la tuberia es una secuencia de operaciones de agregado, es
		 * decir, operaciones que agrupan los elementos que van circulando por la
		 * tuberia para hacer algun tipo de operacion. /* R
		 */

		Persona persona1 = Persona.builder().nombre("Maria ").apellido1("lopez").apellido2("fernandes")
				.genero(Genero.MUJER).fechaNacimiento(LocalDate.of(1985, Month.OCTOBER, 12))
				.salario(new BigDecimal(4500)).build();

		Persona persona2 = Persona.builder().nombre("Carmen").apellido1("flores").apellido2("Martinez")
				.genero(Genero.MUJER).fechaNacimiento(LocalDate.of(1966, Month.FEBRUARY, 02))
				.salario(new BigDecimal(6000)).build();

		Persona persona3 = Persona.builder().nombre("luis ").apellido1("Acaro").apellido2("Silva").genero(Genero.HOMBRE)
				.fechaNacimiento(LocalDate.of(1972, Month.DECEMBER, 22)).salario(new BigDecimal(6800)).build();
		Persona persona4 = Persona.builder().nombre("Medardo ").apellido1("Paredes").apellido2("Hernandez")
				.genero(Genero.HOMBRE).fechaNacimiento(LocalDate.of(1997, Month.MARCH, 05))
				.salario(new BigDecimal(5800)).build();

		Persona persona5 = Persona.builder().nombre("Wilson ").apellido1("Ruiz").apellido2("Alvares")
				.genero(Genero.HOMBRE).fechaNacimiento(LocalDate.of(1989, Month.AUGUST, 15))
				.salario(new BigDecimal(6500)).build();
//List<Persona> listaInmutableDePersona = List.of(persona1,persona1,persona3,persona4, persona5);
		List<Persona> personas = new ArrayList<Persona>();

		// personas.addAll(listaInmutableDePersona);

		/* Otra variante de agregarle persona a la lista modificable */

		personas.add(persona1);
		personas.add(persona2);
		personas.add(persona3);
		personas.add(persona4);
		personas.add(persona5);

		/*
		 * Obtener, utilizando Operaciones de Agreagado, el salario promedio de todas
		 * las personas del genero MUJER
		 */
		// Paso numero 1. Convertir la coleccion de personas, en este caso, en un flujo
		// (stream)
		// de elementos Persona, que va comenzar a circular por una tuberia (pipeline),
		// es decir, una secuencia de operaciones, metodos de la clase Stream

		/*
		 * La coleccion se puede convertir en un flujo, utilizando el metodo stream() o
		 * parallelStream(), este ultimo permitiria procesar los elementos de la
		 * coleccion en un flujo paralelo, haciendo uso de todos los nucleos y los hilos
		 * de ejecucion del procesador del equipo. No obstante, si la lista no es de
		 * muchos elementos no se a notar gran diferencia entre utilizar el metodo
		 * stream() o parallelStream()
		 */
		/*
		 * las operaciones de agregado conjuntamente con las expresiones lambda , los
		 * metodos de la clase Stream y los metodos por referencia es lo que llama
		 * tambien LA PROGRAMACION FUNCIONAL, que unnque java no es un leguaje funcional
		 * puro la implementa conjuntamente con la programacion orietada a objetos. /*
		 * Las Operaciones de Agregado, conjuntamente con las expresiones Lambda, los
		 * metodos de la clase Stream, y los metodos por referencia es lo que se llama
		 * tambien, la PROGRAMACION FUNCIONAL, que aunque Java no es un lenguaje
		 * Funcional puro, la implementa conjutamente con la programacion orientada a
		 * objetos.
		 * 
		 * En la PROGRAMACION FUNCIONAL, las funciones, los metodos se convierten en
		 * "ciudadanos de primera clase", es decir, que al igual que con las variables,
		 * se pueden pasar, las funciones, como parametro a los metodos y se pueden
		 * devolver tambien. La PROGRAMACION FUNCIONAL, entre otras cosas, permite pasar
		 * funcionalidad a los metodos, es un tipo de programacion declarativa, no
		 * imperativa, si pide que es lo que se desea y el metodo responde a esa
		 * peticion (request) de una forma concreta
		 */
		/*
		 * El metodo filter esta gritando que le pasen como parametro "algo" que
		 * implemente la interfaz Predicate, y de momento lo unico que implementa una
		 * interfaz es una clase
		 * 
		 * Y vamos a comenzar implementando una clase externa llamada Filtro
		 */

		Stream<Persona> flujoDePersonas = personas.stream();
		/* El metodo filter recibe lo que esta entre parentesis */

		Predicate<? super Persona> predicate;

		/*
		 * Predicate es una interfaz funcional. ¿Que es una Interfaz Funcional? Es una
		 * interface que tiene varios metodos (metodos con cuerpo, implementados, no
		 * abstractos que pueden ser static, private, etc.), pero solamente UN METODO
		 * ABSTRACTO
		 * 
		 * Concretamente la interfaz funcional Predicate tiene solamente un metodo
		 * Abstracto, de todos los que tiene, dicho metodo se llama test(T t) y prueba
		 * que el objeto que recibe como parametro cumpla una condicion determinada, y
		 * si la cumple, el objeto pasara el siguiente nivel de la tuberia
		 * 
		 * A Continuacion explico lo que esta dentro del diamante <? super Persona>
		 * 
		 * Una lista que en el diamente tenga ? significa que es una coleccion que
		 * admite cualquier tipo de elemento. El caracter ? se le llama comodin
		 * 
		 * List<?> listaDeCualquierCosa; Pero este lista es dificil de manejar
		 * posteriormente, por lo que una coleccion que utilice el signo ? debe estar
		 * acotada, por ejemplo
		 */
		/*
		 * IMPORTANTE!!! Crear una clase externa para satisfacer la condicion del metodo
		 * filter, es una exageracion, porque el objetivo de dicha clase es utilizarla
		 * en el metodo filter nada mas.
		 * 
		 * Entoces ¿Que se sugiere hacer? Se necesita un tipo de clase que se utilice en
		 * el mismo lugar donde se necesita, no fuera de ahí, es decir, se necesita una
		 * denominada CLASE ANONIMA, que es un tipo de clase que permite instanciar un
		 * objetos en el mismo lugar que se implementa la clase
		 * 
		 * La clase anonima es como una expresion de clase, que se utiliza para
		 * implementar una interfaz, aunque tambien se puede utilizar para implementar
		 * una clase abstracta.
		 * 
		 * Antes de utilizar una clase anonima para implementar la interfaz Predicate
		 * vamos a crear una clase anonima para implemtar una sencilla interfaz llamada
		 * saludo Una clase anonima es una expresion de clase, por lo cual tiene que
		 * terminar en punto y coma
		 */

		Saludar saludar = new Saludar() {

			@Override
			public void misSaludosParaTi(String nombre) {
				System.out.println("Hola " + nombre);

			}

		};

		saludar.misSaludosParaTi("Maria");

		/*
		 * Ejercicio # 1 del martes 23 de junio.
		 * 
		 * Utilizando una clase anonima implementar la interfaz Predicate que necesita
		 * el metodo filter, es decir, pasar el criterio de filtro al metodo filter
		 * mediante una expresion de clase anonima
		 */
		// Variantes de solucion prefirida

		// Variante de solucion preferida
		flujoDePersonas.filter(new Predicate<Persona>() {

			@Override
			public boolean test(Persona persona) {
				// TODO Auto-generated method stub
				return persona.genero().equals(Genero.MUJER);
			}
		});

	}
}
