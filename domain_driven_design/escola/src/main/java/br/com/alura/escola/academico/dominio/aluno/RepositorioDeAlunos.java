package br.com.alura.escola.academico.dominio.aluno;

import java.util.List;

import br.com.alura.escola.shared.dominio.Cpf;

public interface RepositorioDeAlunos {
	
	void matricular(Aluno aluno);
	
	Aluno buscarPorCPF(Cpf cpf);
	
	List<Aluno> listarTodosAlunosMatriculados();
	
	//...

}
