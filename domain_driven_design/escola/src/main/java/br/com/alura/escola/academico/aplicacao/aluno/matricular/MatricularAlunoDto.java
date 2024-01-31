package br.com.alura.escola.academico.aplicacao.aluno.matricular;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.Email;
import br.com.alura.escola.shared.dominio.Cpf;

public class MatricularAlunoDto {

	private String nomeAluno;
	private Cpf cpfAluno;
	private String emailAluno;

	public MatricularAlunoDto(String nomeAluno, Cpf cpfAluno, String emailAluno) {
		this.nomeAluno = nomeAluno;
		this.cpfAluno = cpfAluno;
		this.emailAluno = emailAluno;
	}

	public Aluno criarAluno() {
		return new Aluno(cpfAluno, nomeAluno, new Email(emailAluno));
	}

}
