package br.com.alura.escola.dominio.aluno;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.shared.dominio.Cpf;

class CPFTest {

	@Test
	void naoDeveriaCriarCPFComNumerosInvalidos() {
		assertThrows(IllegalArgumentException.class,
				() -> new Cpf(null));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Cpf(""));
		
		assertThrows(IllegalArgumentException.class,
				() -> new Cpf("12345678900"));
	}

	@Test
	void deveriaPermitirCriarCPFComNumeroValido() {
		String numero = "123.456.789-00";
		Cpf cpf = new Cpf(numero);
		assertEquals(numero, cpf.getNumero());
	}

}
