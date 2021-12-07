package br.org.generation.lojagames.controller;

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

import br.org.generation.lojagames.model.Categoria;
import br.org.generation.lojagames.model.Categoria;
import br.org.generation.lojagames.repository.CategoriaRepository;

@RestController
@RequestMapping ("/tipos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping // Lita de tipos, pegar t
	public ResponseEntity <List<Categoria>> getAll()
	{
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}") //Buscar por id
	public ResponseEntity <Categoria> getById(@PathVariable Long id)
	{
		return categoriaRepository.findById(id)
		.map(resposta -> ResponseEntity.ok(resposta))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tipo{tipo}")
	public ResponseEntity <List<Categoria>> getByTipo(@PathVariable String tipo)
	{
		return ResponseEntity.ok(categoriaRepository.findAllByTemaContainingIgnoreCase(tipo));
	}
	
	@PostMapping //Adicionar tipo
	public ResponseEntity<Categoria> postTipo(@Valid @RequestBody Categoria tipo)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(tipo));
	}
	
	@PutMapping //Alterar Titulo
	public ResponseEntity <Categoria> putTipo(@Valid @RequestBody Categoria tipo)
	{
		return categoriaRepository.findById(tipo.getId())
		.map(altera -> ResponseEntity.ok().body(categoriaRepository.save(tipo)))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}") //deletar tema
	public ResponseEntity<?> deleteTema(@PathVariable Long id)
	{
		/*return temaRepository.findById(id)
	    .map
	    ( delete -> {temaRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	    })
	    .orElse(ResponseEntity.notFound().build()); */
		
       Optional<Categoria> delete= categoriaRepository.findById(id);
		
		if(delete.isPresent()) 
		{
            categoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
		else 
        {
         return ResponseEntity.notFound().build();
        } 
	}

}
