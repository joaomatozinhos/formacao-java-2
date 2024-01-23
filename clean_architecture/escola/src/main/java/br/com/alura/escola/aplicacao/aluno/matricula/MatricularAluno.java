package br.com.alura.escola.aplicacao.aluno.matricula;

import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.AlunoRepository;

public class MatricularAluno {

	private final AlunoRepository repositorio;

	public MatricularAluno(AlunoRepository repositorio) {
		this.repositorio = repositorio;
	}

	public void executa(MatricularAlunoDto dados) {
		Aluno novo = dados.criarAluno();
		repositorio.matricular(novo);
	}

}
