package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Tema;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemaRepositoryTest {
	
	
	@Autowired 
	private TemaRepository temaRepository;
	
	@BeforeAll
	void start()
	{
		temaRepository.save(new Tema(0L,"Filmes de terror"));
		temaRepository.save(new Tema(0L, "Filmes da Marvel"));
		temaRepository.save(new Tema(0L, "Filmes da DC"));
		temaRepository.save(new Tema(0L, "Miranha"));
	}
	
	@Test
	@DisplayName("Buscar um tema 🤷‍♂️ ")
	public void deveRetornarUmTema() 
	{    
		
		Optional <Tema> tema = temaRepository.findByTema("Filmes da Marvel");
		assertTrue(tema.get().getTema().equals("Filmes da Marvel"));
		
	}
	
	
	@Test
	@DisplayName("Buscar 3 temas")
	public void deveRetonarTresTemas()
	{
		List<Tema> listaDeTemas = temaRepository.findAllByTemaContainingIgnoreCase("Filme");
		assertEquals(3, listaDeTemas.size());
		assertTrue(listaDeTemas.get(0).getTema().equals("Filmes de terror"));
		assertTrue(listaDeTemas.get(1).getTema().equals("Filmes da Marvel"));
		assertTrue(listaDeTemas.get(2).getTema().equals("Filmes da DC"));
	}	


}
