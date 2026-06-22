package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

@Builder
public record Persona(String nombre, String apellido1, String apellido2, Genero genero, LocalDate fechaNacimiento,
		BigDecimal salario) {

}
