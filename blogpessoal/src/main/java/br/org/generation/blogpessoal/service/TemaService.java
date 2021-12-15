package br.org.generation.blogpessoal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.blogpessoal.model.Tema;
import br.org.generation.blogpessoal.repository.TemaRepository;

@Service
public class TemaService {

	@Autowired
	private TemaRepository temaRepository;

	public Optional<Tema> cadastrarTema(Tema tema)
	{
		if (temaRepository.findByTema(tema.getTema()).isPresent())
			return Optional.empty();

		return Optional.of(temaRepository.save(tema));
	}

	public Optional<Tema> atualizarTema(Tema tema)
	{

		if (temaRepository.findById(tema.getId()).isPresent())
		{

			Optional<Tema> buscaTema = temaRepository.findByTema(tema.getTema());

			if ((buscaTema.isPresent()) && (buscaTema.get().getId() != tema.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema já existe!", null);

			return Optional.ofNullable(temaRepository.save(tema));
		}
		return Optional.empty();
	}

}
