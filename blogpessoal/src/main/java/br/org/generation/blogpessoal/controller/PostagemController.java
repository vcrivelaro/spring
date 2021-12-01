package br.org.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.repository.PostagemRepository;

@RestController //declarar classe como controle
@RequestMapping("/postagens") //end-point
@CrossOrigin(origins = "*", allowedHeaders = "*") 

public class PostagemController {
	
	@Autowired // implementa o conceito injeção de independencia, ele cria o objeto
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity <List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll()); // equivalente a select*from tbm_postagens

	}

}
