package br.com.alura.adopet.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.service.AdocaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

	@Autowired
	private AdocaoService service;

	@PostMapping
	@Transactional
	public ResponseEntity<String> solicitar(@RequestBody @Valid Adocao adocao) {
		try {
			service.solicitar(adocao);
			return ResponseEntity.ok("Adoção solicitada com sucesso!");
		} catch (ValidacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/aprovar")
	@Transactional
	public ResponseEntity<String> aprovar(@RequestBody @Valid Adocao adocao) {
		service.aprovar(adocao);
		return ResponseEntity.ok("Adoção aprovada com sucesso!");
	}

	@PutMapping("/reprovar")
	@Transactional
	public ResponseEntity<String> reprovar(@RequestBody @Valid Adocao adocao) {
		service.reprovar(adocao);
		return ResponseEntity.ok("Adoção reprovada!");
	}

}
