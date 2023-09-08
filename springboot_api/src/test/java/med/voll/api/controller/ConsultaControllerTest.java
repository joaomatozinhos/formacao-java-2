package med.voll.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsultaControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
	@WithMockUser
	void agendaCenario1() throws Exception {
		MockHttpServletResponse response = mvc
				.perform(post("/consultas").content("{}").contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

}
