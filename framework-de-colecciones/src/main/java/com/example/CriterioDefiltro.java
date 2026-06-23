package com.example;

import java.util.function.Predicate;

public class CriterioDefiltro implements Predicate<Persona> {

	@Override
	public boolean test(Persona persona) {
		// TODO Auto-generated method stub
		return persona.genero().equals(Genero.MUJER);
		
	}

}
