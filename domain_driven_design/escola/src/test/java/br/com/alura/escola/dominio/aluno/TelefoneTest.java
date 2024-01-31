package br.com.alura.escola.dominio.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.Email;
import br.com.alura.escola.academico.dominio.aluno.Telefone;
import br.com.alura.escola.shared.dominio.Cpf;

class TelefoneTest {

	@Test
	void naoDeveriaCriarTelefoneComDDDsInvalidos() {
		assertThrows(IllegalArgumentException.class, () -> new Telefone(null, "123456789"));

		assertThrows(IllegalArgumentException.class, () -> new Telefone("", "123456789"));

		assertThrows(IllegalArgumentException.class, () -> new Telefone("1", "123456789"));
	}

	@Test
	void naoDeveriaCriarTelefoneComNumerosInvalidos() {
		assertThrows(IllegalArgumentException.class, () -> new Telefone("11", null));

		assertThrows(IllegalArgumentException.class, () -> new Telefone("11", ""));

		assertThrows(IllegalArgumentException.class, () -> new Telefone("11", "123"));
	}

	@Test
	void deveriaPermitirCriarTelefoneComDDDENumeroValidos() {
		String ddd = "11";
		String numero = "123456789";
		Telefone telefone = new Telefone(ddd, numero);
		assertEquals(ddd, telefone.getDdd());
		assertEquals(numero, telefone.getNumero());
	}

	@Test
	void naoDeveriaPermitirAdicionar3Telefones() throws Exception {
		Cpf cpf = new Cpf("123.456.789-00");
		Email email = new Email("fulano@gmail.com");
		Aluno aluno = new Aluno(cpf, "fulano", email);

		assertThrows(IllegalArgumentException.class, () -> {
			aluno.adicionarTelefone("11", "123456789");
			aluno.adicionarTelefone("11", "223456789");
			aluno.adicionarTelefone("11", "323456789");
		});

	}

}
