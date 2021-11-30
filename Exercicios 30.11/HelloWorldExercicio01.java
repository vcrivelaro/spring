package br.org.generation.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/habilidades-e-mentalidades")

public class HelloWorldExercicio01 {
	@GetMapping
	public String habiliadesMentalidades() {
		return "Hoje, a habilidade e mentalidade que foram usadas para que eu pudesse fazer esse foram: Mentalidade de Crescimento, "
				+ "persistência e de habilidades: Atenção aos detalhes.";
	}


}
