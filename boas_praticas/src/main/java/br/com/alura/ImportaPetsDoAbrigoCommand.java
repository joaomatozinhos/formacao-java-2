package br.com.alura;

import java.io.IOException;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;

public class ImportaPetsDoAbrigoCommand implements Command {

	@Override
	public void execute() {
		ClientHttpConfiguration client = new ClientHttpConfiguration();
		PetService petService = new PetService(client);

		try {
			petService.importaPetsDoAbrigo();
		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
