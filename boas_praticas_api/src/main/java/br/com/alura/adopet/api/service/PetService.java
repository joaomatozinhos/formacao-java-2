package br.com.alura.adopet.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;

@Service
public class PetService {

	@Autowired
	private PetRepository repository;

	public List<PetDto> buscarPetsDisponiveis() {
		return repository.findAllByAdotadoFalse().stream().map(PetDto::new).toList();
	}

	public void cadastrar(Abrigo abrigo, CadastroPetDto dto) {
		repository.save(new Pet(dto, abrigo));
	}

}
