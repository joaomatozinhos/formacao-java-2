package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@PostMapping("/cadastro")
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> cadastra(@RequestBody @Valid DadosCadastroMedico dados,
			UriComponentsBuilder uriBuilder) {
		Medico medico = new Medico(dados);
		repository.save(medico);

		URI uri = uriBuilder.path("/medicos/cadastro/{id}").buildAndExpand(medico.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}

	@GetMapping("/listagem")
	public ResponseEntity<Page<DadosListagemMedico>> listaTodos(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		Page<DadosListagemMedico> page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

		return ResponseEntity.ok(page);
	}

	@GetMapping("/detalhes/{id}")
	public ResponseEntity<DadosDetalhamentoMedico> detalha(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);

		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@PutMapping("/edicao")
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> atualiza(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		Medico medico = repository.getReferenceById(dados.id());
		medico.atualizaDados(dados);

		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@DeleteMapping("/exclusao/{id}")
	@Transactional
	public ResponseEntity<Void> exclui(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		medico.exclui();

		return ResponseEntity.noContent().build();
	}

}
