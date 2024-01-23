package br.com.alura.escola;

import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.AlunoRepository;
import br.com.alura.escola.dominio.aluno.Cpf;
import br.com.alura.escola.dominio.aluno.Email;
import br.com.alura.escola.infra.aluno.AlunoRepositoryEmMemoria;

public class MatricularAluno {
	public static void main(String[] args) {
		String nome = "Fulano da Silva";
		Cpf cpf = new Cpf("123.456.789-00");
		Email email = new Email("fulano@email.com");

		Aluno aluno = new Aluno(cpf, nome, email);

		AlunoRepository repositorio = new AlunoRepositoryEmMemoria();
		repositorio.matricular(aluno);
	}
}
