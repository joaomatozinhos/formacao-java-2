package br.com.alura.escola.dominio.aluno;

public class Email {

	private String endereco;

	public Email(String endereco) {
		if (endereco == null || !endereco.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")) {
			throw new IllegalArgumentException("E-mail inválido!");
		}

		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}
}
