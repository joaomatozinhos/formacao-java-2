package br.com.alura.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;

import br.com.alura.client.ClientHttpConfiguration;

public class PetServiceTest {

	private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
	private PetService petService = new PetService(client);
	private HttpResponse<String> response = mock(HttpResponse.class);

	@Test
	public void deveVerificarSeDispararRequisicaoPostSeraChamado() throws IOException, InterruptedException {
		String userInput = String.format("Teste%spets.csv", System.lineSeparator());
		ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(bais);

		when(client.disparaRequisicaoPost(anyString(), any())).thenReturn(response);

		petService.importaPetsDoAbrigo();
		verify(client.disparaRequisicaoPost(anyString(), anyString()), atLeast(1));
	}

}
