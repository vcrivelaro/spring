package br.org.generation.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/objetivos-de-aprendizagem")

public class HelloWorldExercicio02 {
	
	@GetMapping
	public String objetivoAprendizagem() {
		return "O Objetivo dessa semana é não ficar para trás e absorver 200% do que vai ser passado nessa semana. Hype!!";
	}
}
