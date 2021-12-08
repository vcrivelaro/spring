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

import com.example.lojafarmacia.model.Produto;
import com.example.lojafarmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll()
	{
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity <Produto> getById(@PathVariable Long id)
	{
		return produtoRepository.findById(id)
		.map(resposta -> ResponseEntity.ok(resposta))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome)
	{
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
		
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));	
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto)
	{
		return produtoRepository.findById(produto.getId())
		.map(altera -> ResponseEntity.ok().body(produtoRepository.save(produto)))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}") //deletar tema
	public ResponseEntity<?> deleteProduto(@PathVariable Long id)
	{
		/*return temaRepository.findById(id)
	    .map
	    ( delete -> {temaRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	    })
	    .orElse(ResponseEntity.notFound().build()); */
		
       Optional<Produto> delete= produtoRepository.findById(id);
		
		if(delete.isPresent()) 
		{
			produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
		else 
        {
         return ResponseEntity.notFound().build();
        } 
	} 
}
