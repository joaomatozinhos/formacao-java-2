package br.com.alura.adopet.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

	@Autowired
	private AbrigoService abrigoService;

	@Autowired
	private PetService petService;

	@GetMapping
	public ResponseEntity<List<AbrigoDto>> listar() {
		List<AbrigoDto> abrigos = abrigoService.listarTodos();
		return ResponseEntity.ok(abrigos);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroAbrigoDto dto) {
		try {
			abrigoService.cadastrar(dto);
			return ResponseEntity.ok().build();
		} catch (ValidacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{idOuNome}/pets")
	public ResponseEntity<List<PetDto>> listarPets(@PathVariable String idOuNome) {
		try {
			List<PetDto> petsDoAbrigo = abrigoService.listarPetsDoAbrigo(idOuNome);
			return ResponseEntity.ok(petsDoAbrigo);
		} catch (ValidacaoException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{idOuNome}/pets")
	@Transactional
	public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastroPetDto dto) {
		try {
			Abrigo abrigo = abrigoService.carregarAbrigo(idOuNome);
			petService.cadastrar(abrigo, dto);
			return ResponseEntity.ok().build();
		} catch (ValidacaoException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
