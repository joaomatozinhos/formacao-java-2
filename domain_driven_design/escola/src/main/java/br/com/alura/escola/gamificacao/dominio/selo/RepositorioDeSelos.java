package br.com.alura.escola.gamificacao.dominio.selo;

import java.util.List;

import br.com.alura.escola.shared.dominio.Cpf;

public interface RepositorioDeSelos {

	void adicionar(Selo selo);

	List<Selo> selosDoAlunoDeCPF(Cpf cpf);

}
