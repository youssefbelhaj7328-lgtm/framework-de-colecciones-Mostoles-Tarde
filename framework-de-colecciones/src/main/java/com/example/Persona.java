package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

@Builder
public record Persona(String nombre, String apellido1, String apellido2, Genero genero, LocalDate fechaNacimiento,
		BigDecimal salario) implements Comparable<Persona> {

	@Override
	public int compareTo(Persona persona) {
		// El criterio de comparacion, para poder comparar dos personas de la lista de
		// personas va a ser: primero por el primer apellido, segundo, si son iguales
		// por
		// el primer apellido, que decida el segundo Apellido, y si tambien por el
		// segundo
		// Apellido fueran iguales, pues que decida el nombre
		// La variable siguiente, va a almacenar el resultado de la comparacion
		// del primer apellido de dos personas, una es la persona correspondiente al
		// objeto
		// Persona actual, al cual se accede mediante la palabra reservada this, y la
		// otra
		// persona es la que se recibe como parametro del metodo compareTo

		int cmpPrimerApellido = this.apellido1.compareTo(persona.apellido1());
		int cmpSegundoApellido = this.apellido2.compareTo(persona.apellido2());
		int cmpNombre = this.nombre.compareTo(persona.nombre());

		return cmpPrimerApellido != 0 ? cmpPrimerApellido : cmpSegundoApellido != 0 ? cmpSegundoApellido :

				cmpNombre;
		// Recordando cual es la sintaxis del operador ternario

		/*
		 * int x = 4; int y = 5;
		 * 
		 * String resultado;
		 * 
		 * resultado = (x != y && x > 2 || y >= 5) ? "Si, se cumple" :
		 * "No, no se cumple";
		 */

	}

}