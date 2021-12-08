package com.example.lojafarmacia.controller;

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

import com.example.lojafarmacia.model.Categoria;
import com.example.lojafarmacia.repository.CategoriaRepository;


@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List <Categoria>> getAll()
	{
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Categoria> getById(@PathVariable Long id)
	{
		return categoriaRepository.findById(id)
		.map(resposta -> ResponseEntity.ok(resposta))
		.orElse(ResponseEntity.notFound().build());	
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Categoria>> getByCategoria(@PathVariable String categoria)
	{
		return ResponseEntity.ok(categoriaRepository.findAllByCategoriaContainingIgnoreCase(categoria));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria tipo)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(tipo));	
	}
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria) 
	{
		return categoriaRepository.findById(categoria.getId())
		.map(altera -> ResponseEntity.ok().body(categoriaRepository.save(categoria)))
		.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}") //deletar tema
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id)
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