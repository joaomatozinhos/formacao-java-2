package br.com.alura.escola.academico.dominio.aluno;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.escola.shared.dominio.Cpf;

public class Aluno {

	private Cpf cpf;
	private String nome;

	private Email email;

	private List<Telefone> telefones = new ArrayList<>();

	private String senha;

	public Aluno(Cpf cpf, String nome, Email email) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
	}

	public void adicionarTelefone(String ddd, String numero) throws Exception {
		if (telefones.size() == 2) {
			throw new IllegalArgumentException("Número máximo de telefones ja atingido!");
		}

		this.telefones.add(new Telefone(ddd, numero));
	}

	public Cpf getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email.getEndereco();
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public String getSenha() {
		return senha;
	}

}
