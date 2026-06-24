package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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

		Persona persona1 = Persona.builder().nombre("Maria").apellido1("lopez").apellido2("fernandes")
				.genero(Genero.MUJER).fechaNacimiento(LocalDate.of(1985, Month.OCTOBER, 12))
				.salario(new BigDecimal(4500)).build();

		Persona persona2 = Persona.builder().nombre("Maria").apellido1("flores").apellido2("Martinez")
				.genero(Genero.MUJER).fechaNacimiento(LocalDate.of(1966, Month.FEBRUARY, 02))
				.salario(new BigDecimal(6000)).build();

		Persona persona3 = Persona.builder().nombre("Luis").apellido1("Acaro").apellido2("Silva").genero(Genero.HOMBRE)
				.fechaNacimiento(LocalDate.of(1972, Month.DECEMBER, 22)).salario(new BigDecimal(6800)).build();
		Persona persona4 = Persona.builder().nombre("Luis").apellido1("Paredes").apellido2("Hernandez")
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

		/*
		 * /* Si la interface que se va a implementar con una clase anonima es una
		 * interfaz funcional, utilizar una clase anonima es todavia EXCESIVO, demasiado
		 * codigo, pues ¿Que se utiliza entonces? Se utiliza una EXPRESION LAMBDA
		 * 
		 * ¿Que es una expresion lambda?
		 * 
		 * Es como un metodo anonimo, se utiliza para pasar a un metodo la
		 * implementacion del unico metodo abstracto que hay que implementar.
		 * 
		 * A continuacion la sintaxis de una expresion lambda
		 */

		OptionalDouble optionalDelSalarioPromedio = flujoDePersonas.filter(p -> p.genero().equals(Genero.MUJER))
				.mapToDouble(persona -> persona.salario().doubleValue()).average();

		/*
		 * ¿Que es un Optional? El tipo de dato Optional es una de las maravillas de las
		 * versiones mas recientes de Java, surgue en la version 8 de Java. El tipo
		 * Optional te proteje del temido NullPointerException, es decir, que tu
		 * intentes trabajar con un objeto y este tome el valor NULL, y el problema es
		 * que cuando en una operacion interviene un null todo se vuelve NULL. *
		 * Finalmente, el tipo Optional hay que verlo como una cajita de sorpresa, donde
		 * puede venir el resultado esperado, que seria el salario promedio en este
		 * caso, o seria un valor NULL porque el promedio no se pudo calcular, porque
		 * ninguna de las personas del genero MUJER tenian salario
		 */

		if (optionalDelSalarioPromedio.isPresent()) {

// De la cajita del Optional puedo extraer el salario promedio
// sin ningun peligro

			double salarioPromedio = optionalDelSalarioPromedio.getAsDouble();

			System.out.println("El salario promedio es: " + salarioPromedio);
		}
		/*
		 * Ejemplo # 1 del Miercoles 24 de Junio.
		 * 
		 * Utilizando Operaciones de Agregado, recorrer las lista de personas y obtener
		 * una nueva coleccion que contenga solamente los nombres de las personas, pero
		 * sin duplicados
		 */
		Set<String> nombresSinDuplicados = personas.stream().map(p -> p.nombre()).collect(Collectors.toSet());
		/*
		 * Si al final de la tuberia se quiere obtener una nueva coleccion , la
		 * operacion terminal tiene que ser el metodo collect(), que recibe la
		 * implementacion de la interfaz Collector a traves de una clase que tiene el
		 * mismo nombre pero en plural, Collectors en este caso, que tendra a su vez
		 * metodos estaticos, en la propia clase Collectors para trabajar con los
		 * elementos que se colectan al final de la tuberia
		 */
		/*
		 * Cuando la expresion lambda lo unico que hace es llamar al metodo que es quien
		 * realmente hace el trabajo, utilizar una expresion lambda es poco eficiente y
		 * redundante, por lo cual lo mejor es que el propio metodo haga el trabajo, sin
		 * ningun intermediario, es decir que lo correcto es pasar el metodo por
		 * referencia
		 */

		Set<String> nombresSinDuplicados2 = personas.stream().map(Persona::nombre).collect(Collectors.toSet());

		// A continuacion vamos a imprimir los elementos de la coleccion
		// nombresSinDuplicados2
		System.out.println("Set de nombres sin duplicados: ");
		
		//nombresSinDuplicados2.stream().forEach(nombre -> System.out.println(nombre));
		System.out.println("Set de nombres sin duplicados: ");

		// nombresSinDuplicados2.stream().forEach(nombre -> System.out.println(nombre));

		/* En la sentencia anterior, la expresion lambda lo unico que hace es 
		* llamar al metodo println, por tanto se puede quitar la lambda y pasar el 
		* metodo por referencia 
		* 
		* En las ultimas versiones de Java, no hace falta el metodo stream() si directamente
		* se utiliza una operacion terminal a continuacion del origen de la tuberia
		* */

		nombresSinDuplicados2.forEach(System.out::println);
		
		

	}
}
