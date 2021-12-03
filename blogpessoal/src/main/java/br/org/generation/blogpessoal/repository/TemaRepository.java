package br.org.generation.blogpessoal.repository;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.blogpessoal.model.Tema;

public interface TemaRepository extends JpaRepository <Tema, Long>{
	
	public List <Tema> findAllByTemaContainingIgnoreCase (String Tema);


}
