package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.DadosAtualizacaoPaciente;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	@PostMapping("/cadastro")
	@Transactional
	public void cadastro(@RequestBody @Valid DadosCadastroPaciente dados) {
		Paciente paciente = new Paciente(dados);
		repository.save(paciente);
	}

//	@GetMapping("/listagem")
//	public List<DadosListagemPaciente> lista() {
//		return repository.findAll().stream().map(DadosListagemPaciente::new).toList();
//	}
	
	@GetMapping
	public Page<DadosListagemPaciente> listar(@PageableDefault(page = 0, size = 10, sort = { "nome" }) Pageable paginacao) {
	    return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
	}

	@PutMapping("/edicao")
	@Transactional
	public void atualiza(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		Paciente paciente = repository.getReferenceById(dados.id());
		paciente.atualiza(dados);
	}

//	@DeleteMapping("/exclusao/{id}")
//	@Transactional
//	public void exclui(@PathVariable Long id) {
//		repository.deleteById(id);
//	}

	@DeleteMapping("/exclusao/{id}")
	@Transactional
	public void exclui(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		paciente.exclui();
	}
}
