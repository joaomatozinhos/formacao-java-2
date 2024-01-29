package br.com.alura.escola.aplicacao.aluno.matricular;

import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.CPF;
import br.com.alura.escola.dominio.aluno.Email;

public class MatricularAlunoDto {

	private String nomeAluno;
	private CPF cpfAluno;
	private String emailAluno;

	public MatricularAlunoDto(String nomeAluno, CPF cpfAluno, String emailAluno) {
		this.nomeAluno = nomeAluno;
		this.cpfAluno = cpfAluno;
		this.emailAluno = emailAluno;
	}

	public Aluno criarAluno() {
		return new Aluno(cpfAluno, nomeAluno, new Email(emailAluno));
	}

}
