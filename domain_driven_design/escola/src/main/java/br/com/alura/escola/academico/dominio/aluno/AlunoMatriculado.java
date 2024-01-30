package br.com.alura.escola.academico.dominio.aluno;

import java.time.LocalDateTime;

import br.com.alura.escola.academico.dominio.Evento;

public class AlunoMatriculado implements Evento {

	private final CPF cpf;
	private final LocalDateTime momento;

	public AlunoMatriculado(CPF cpf) {
		this.cpf = cpf;
		this.momento = LocalDateTime.now();
	}

	@Override
	public LocalDateTime momento() {
		return this.momento;
	}

	public CPF getCpf() {
		return cpf;
	}
}
