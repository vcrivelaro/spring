package br.org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@GetMapping("/{id}")
	public ResponseEntity <Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
        .map(resp -> ResponseEntity.ok(resp))
		.orElse(ResponseEntity.notFound().build());
	}
	
		
	@GetMapping ("/titulo/{titulo}")
	public ResponseEntity <List<Postagem>> getByTiulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		// método personalizado, precisou criar na interface
	}

	@PostMapping
	public ResponseEntity <Postagem> postPostagem(@Valid @RequestBody Postagem postagem){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));

	}
	
	@PutMapping
	public ResponseEntity<Postagem>  putPostagem(@Valid @RequestBody Postagem postagem){
		//return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
		
		Optional <Postagem> altera = postagemRepository.findById(postagem.getId());
				
		if (altera.isPresent()) 
		{
			return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
		} 
		else 
		{

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <Postagem> deletePostagem(@PathVariable Long id) {
		//postagemRepository.deleteById(id);

		Optional<Postagem> delete= postagemRepository.findById(id);
		
		if(delete.isPresent()) 
		{
            postagemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
		else 
        {
         return ResponseEntity.notFound().build();
        } 

	}

}
