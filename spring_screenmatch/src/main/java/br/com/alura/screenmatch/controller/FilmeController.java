package br.com.alura.screenmatch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.alura.screenmatch.domain.filme.Filme;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

	private List<Filme> listaDeFilmesCadastrados = new ArrayList<>();

	@GetMapping("/cadastro")
	public String carregaPaginaFormulario() {
		return "filmes/formulario";
	}

	@PostMapping("/cadastro")
	public String cadastraFilme(DadosCadastroFilme dados) {
		Filme filme = new Filme(dados);
		listaDeFilmesCadastrados.add(filme);
		System.out.println(listaDeFilmesCadastrados);

		return "filmes/formulario";
	}
}
