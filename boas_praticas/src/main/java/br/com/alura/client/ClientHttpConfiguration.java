package br.com.alura.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class ClientHttpConfiguration {

	public HttpResponse<String> disparaRequisicaoGet(String uri) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri))
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		return client.send(request, HttpResponse.BodyHandlers.ofString());
	}

	public HttpResponse<String> disparaRequisicaoPost(String uri, Object object)
			throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).header("Content-Type", "application/json")
				.method("POST", HttpRequest.BodyPublishers.ofString(new Gson().toJson(object))).build();

		return client.send(request, HttpResponse.BodyHandlers.ofString());
	}
}
