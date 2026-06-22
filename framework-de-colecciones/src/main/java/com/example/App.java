package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {

	public static void main(String[] args) {

		/*
		 * Existen 3 formas de recorrer una coleccion, siendo una de ellas la unica que
		 * permite eliminar un elemento de la coleccion mientras se recorre Traversing
		 * Collections:
		 * https://docs.oracle.com/javase/tutorial/collections/interfaces/collection.
		 * html Formas de recorrer una coleccion /* Existen 3 formas de iterar o
		 * recorrer una coleccion, siendo una de ellas la unica que permite eliminar un
		 * elemento de la coleccion mientras se recorre
		 * 
		 * Traversing Collections:
		 * https://docs.oracle.com/javase/tutorial/collections/interfaces/collection.
		 * html
		 * 
		 * Formas de recorrer una coleccion
		 * 
		 * 1- Mediante un Iterador (Iterator en Ingles) 2- Mediante una sentencia for
		 * mejorada 3- Mediante Operaciones de Agregado (Metodos de la clase Stream,
		 * Expresiones Lambda, Metodos por referencia, en fin, PROGRAMACION FUNCIONAL)
		 * 
		 * Y la ultima, que no por se la ultima es la menos importante, sino todo lo
		 * contrario, es la sugerida, y por ello la dejamos para el final
		 */
		// Uso de la interfaz Interator para eliminar un elemento de la coleccion
		// mientras se recorre
		// Para ello como ejercicio numero 1 del viernes 19 de junio se pide.,
		/*
		 * Crear una lista de persona, donde cada persona sea un record con la seguintes
		 * propiedades
		 * 
		 * 
		 * - nombre - apellido1 - apellido2 - genero - fechaNacimiento - salario
		 * 
		 * La lista de Persona tiene que contener 5 personas
		 * 
		 * -- Para hacer con el Profesor Una vez creada la lista, la recorremos y
		 * eliminamos a todas las personas del genero HOMBRE, que tengan salario
		 * inferior a la media
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
		List<Persona> listaModificableDePersonas = new ArrayList<Persona>();

		// listaModificableDePersonas.addAll(listaInmutableDePersona);

		/* Otra variante de agregarle persona a la lista modificable */

		listaModificableDePersonas.add(persona1);
		listaModificableDePersonas.add(persona2);
		listaModificableDePersonas.add(persona3);
		listaModificableDePersonas.add(persona4);
		listaModificableDePersonas.add(persona5);

		System.out.println("lista modificable:" + listaModificableDePersonas);
		// Calculamos la media de los salarios ded la lista de personas
		// Calculamos la media de los salarios de la lista de personas

		BigDecimal sumaSalarios = BigDecimal.ZERO;

		for (Persona persona : listaModificableDePersonas) { 
		sumaSalarios = sumaSalarios.add(persona.salario());
		}

		BigDecimal mediaSalarios = sumaSalarios.divide(new BigDecimal(listaModificableDePersonas.size()));

		System.out.println("Media de salarios: " + mediaSalarios);

		// Eliminamos a todas las personas del genero HOMBRE, que tengan salario inferior a la media
		
		Iterator <Persona> it= listaModificableDePersonas. iterator();
		
		while (it.hasNext()) { 

			Persona p = it.next();

			/* El metodo next() cada vez que se invoca extrae una persona de la coleccion 
			* Por lo cual, para comparar el salario de una persona con el salario medio, 
			* estaria bien la sentencia siguiente: */
			if (p.salario().doubleValue() < mediaSalarios.doubleValue() && 
			p.genero().equals(Genero.HOMBRE)) {

			// Eliminamos a la persona
			it.remove();
			}
			}
			
			
		
	
		
		

	}
}
