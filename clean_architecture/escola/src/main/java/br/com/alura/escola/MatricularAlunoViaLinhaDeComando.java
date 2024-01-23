package br.com.alura.escola;

import br.com.alura.escola.aplicacao.aluno.matricula.MatricularAluno;
import br.com.alura.escola.aplicacao.aluno.matricula.MatricularAlunoDto;
import br.com.alura.escola.infra.aluno.AlunoRepositoryEmMemoria;

public class MatricularAlunoViaLinhaDeComando {
	public static void main(String[] args) {
		String nome = "Fulano da Silva";
		String cpf = "123.456.789-00";
		String email = "fulano@email.com";

		MatricularAluno matricular = new MatricularAluno(new AlunoRepositoryEmMemoria());
		matricular.executa(new MatricularAlunoDto(nome, cpf, email));
	}
}
