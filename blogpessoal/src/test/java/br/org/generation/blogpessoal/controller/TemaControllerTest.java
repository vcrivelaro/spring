package br.org.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.org.generation.blogpessoal.model.Tema;
import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.service.TemaService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TemaControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;	
	
	@Autowired
	private TemaController temaController;
	
	@Autowired
	private TemaService temaService;
	
	@Test
	@Order(1)
	@DisplayName("Criar um tema")
	public void deveCriarUmTema() 
	{
		temaController.postTema(new Tema(0L, "Viagem"));
		
		HttpEntity<Tema> requisicao = new HttpEntity<Tema>(new Tema(0L, "Viagem"));

			ResponseEntity <Tema> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/temas", HttpMethod.POST, requisicao, Tema.class);
			
			assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
			assertEquals(requisicao.getBody().getTema(), resposta.getBody().getTema());
			
	}
	
	@Test
	@Order(2)
	@DisplayName("Alterar um tema")
	public void deveAlterarUmTema() 
	{
		Optional<Tema> temaCreate = temaService.cadastrarTema(new Tema (0L,"teste"));

			Tema temaUpdate = new Tema(temaCreate.get().getId(),"teste");
			
			HttpEntity<Tema> requisicao = new HttpEntity<Tema>(temaUpdate);

			ResponseEntity<Tema> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/temas", HttpMethod.PUT, requisicao, Tema.class);

			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(temaUpdate.getTema(), resposta.getBody().getTema());
		
			
	}


}
