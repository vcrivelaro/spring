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

import br.org.generation.blogpessoal.model.Tema;
import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.repository.TemaRepository;
import br.org.generation.blogpessoal.service.TemaService;

@RestController
@RequestMapping ("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TemaController {
	 	
	
	@Autowired
	private TemaRepository temaRepository;
	
	@Autowired TemaService temaService;
	
	@GetMapping //Lista de temas
	public ResponseEntity<List<Tema>> getAll()
	{
		return ResponseEntity.ok(temaRepository.findAll()); // equivalente a select*from tbm_postagens
	}
	
	@GetMapping("/{id}") //Busca por id
	public ResponseEntity <Tema> getById(@PathVariable Long id)
	{
		return temaRepository.findById(id)
		.map(resposta -> ResponseEntity.ok(resposta))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tema/{tema}") // Busca por tema
	public ResponseEntity<List<Tema>> getByTema(@PathVariable String tema)
	{
		return ResponseEntity.ok(temaRepository.findAllByTemaContainingIgnoreCase(tema));
		
	}	
	
	@PostMapping //Adicionar tema
	public ResponseEntity<Tema> postTema(@Valid @RequestBody Tema tema)
	{
		return temaService.cadastrarTema(tema)	
		.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
		.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	} 
	
	@PutMapping //Editar tema
	public ResponseEntity<Tema> putTema(@Valid @RequestBody Tema tema)
	
	{
		 return temaService.atualizarTema(tema)
        .map(altera -> ResponseEntity.ok().body(temaRepository.save(tema)))
		.orElse(ResponseEntity.notFound().build());
	}
	
	/*Optional <Postagem> altera = postagemRepository.findById(postagem.getId());
	
	if (altera.isPresent()) 
	{
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
	} 
	else 
	{

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	} */
	
	@DeleteMapping("/{id}") //deletar tema
	public ResponseEntity<?> deleteTema(@PathVariable Long id)
	{
		/*return temaRepository.findById(id)
	    .map
	    ( delete -> {temaRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	    })
	    .orElse(ResponseEntity.notFound().build()); */
		
       Optional<Tema> delete= temaRepository.findById(id);
		
		if(delete.isPresent()) 
		{
            temaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
		else 
        {
         return ResponseEntity.notFound().build();
        } 
	}
	

}
