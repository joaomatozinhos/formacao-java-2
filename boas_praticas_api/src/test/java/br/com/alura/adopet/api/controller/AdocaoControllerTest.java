package br.com.alura.adopet.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.adopet.api.service.AdocaoService;

@SpringBootTest
@AutoConfigureMockMvc
class AdocaoControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AdocaoService service;

	@Test
	void deveriaDevolverCodigo400ParaSolicitacaoDeAdocaoComErros() throws Exception {
		String json = "{}";

		MockHttpServletResponse response = mvc
				.perform(post("/adocoes").content(json).contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		assertEquals(400, response.getStatus());
	}

	@Test
	void deveriaDevolverCodigo200ParaSolicitacaoDeAdocaoSemErros() throws Exception {
		String json = """
				{
				    "idPet": 1,
				    "idTutor": 1,
				    "motivo": "Motivo qualquer"
				}
				""";

		MockHttpServletResponse response = mvc
				.perform(post("/adocoes").content(json).contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		assertEquals(200, response.getStatus());
	}

	@Test
	void deveriaDevolverCodigo200ParaRequisicaoDeAprovarAdocao() throws Exception {
		// ARRANGE
		String json = """
				{
				    "idAdocao": 1
				}
				""";

		// ACT
		MockHttpServletResponse response = mvc
				.perform(put("/adocoes/aprovar").content(json).contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		// ASSERT
		assertEquals(200, response.getStatus());
	}

	@Test
	void deveriaDevolverCodigo400ParaRequisicaoDeAprovarAdocaoInvalida() throws Exception {
		// ARRANGE
		String json = """
				{

				}
				""";

		// ACT
		MockHttpServletResponse response = mvc
				.perform(put("/adocoes/aprovar").content(json).contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		// ASSERT
		assertEquals(400, response.getStatus());
	}

	@Test
	void deveriaDevolverCodigo200ParaRequisicaoDeReprovarAdocao() throws Exception {
		// ARRANGE
		String json = """
				{
				    "idAdocao": 1,
				    "justificativa": "qualquer"
				}
				""";

		// ACT
		MockHttpServletResponse response = mvc
				.perform(put("/adocoes/reprovar").content(json).contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		// ASSERT
		assertEquals(200, response.getStatus());
	}

	@Test
	void deveriaDevolverCodigo400ParaRequisicaoDeReprovarAdocaoInvalido() throws Exception {
		// ARRANGE
		String json = """
				{

				}
				""";

		// ACT
		MockHttpServletResponse response = mvc
				.perform(put("/adocoes/reprovar").content(json).contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		// ASSERT
		assertEquals(400, response.getStatus());
	}

}
