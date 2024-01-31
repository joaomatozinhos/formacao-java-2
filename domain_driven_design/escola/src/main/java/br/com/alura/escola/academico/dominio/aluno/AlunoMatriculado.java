package br.com.alura.escola.academico.dominio.aluno;

import java.time.LocalDateTime;
import java.util.Map;

import br.com.alura.escola.shared.dominio.Cpf;
import br.com.alura.escola.shared.dominio.evento.Evento;
import br.com.alura.escola.shared.dominio.evento.TipoDeEvento;

public class AlunoMatriculado implements Evento {

	private final Cpf cpf;
	private final LocalDateTime momento;

	public AlunoMatriculado(Cpf cpf) {
		this.cpf = cpf;
		this.momento = LocalDateTime.now();
	}

	@Override
	public LocalDateTime momento() {
		return this.momento;
	}

	public Cpf getCpf() {
		return cpf;
	}

	@Override
	public TipoDeEvento tipo() {
		return TipoDeEvento.ALUNO_MATRICULADO;
	}

	@Override
	public Map<String, Object> informacoes() {
		return Map.of("cpf", cpf);
	}
}
