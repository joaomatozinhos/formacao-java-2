package br.com.alura.escola.aluno;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.aluno.Email;

class EmailTest {

	@Test
	void naoDeveriaCriarEmailsComEnderecosInvalidos() {
		assertThrows(IllegalArgumentException.class, () -> new Email(null));

		assertThrows(IllegalArgumentException.class, () -> new Email(""));

		assertThrows(IllegalArgumentException.class, () -> new Email("emailinvalido"));
	}

}
