package br.com.alura.escola.dominio.aluno;

import java.util.List;

public interface AlunoRepository {

	void matricular(Aluno aluno);

	Aluno buscarPorCPF(Cpf cpf);

	List<Aluno> listarTodosAlunosMatriculados();

}
