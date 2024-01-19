package br.com.alura.escola.aluno;

import java.util.ArrayList;
import java.util.List;

public class Aluno {

	private Cpf cpf;
	private String nome;
	private Email email;
	private List<Telefone> telefones = new ArrayList<>();

	public Aluno(Cpf cpf, String nome, Email email) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
	}

	public void adicionarTelefone(String ddd, String numero) {
		this.telefones.add(new Telefone(ddd, numero));
	}

	public Cpf getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public Email getEmail() {
		return email;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public static void main(String[] args) {
		Aluno a = new Aluno(new Cpf("123"), "Fulano da Silva", new Email("email@email.com"));
	}

}
