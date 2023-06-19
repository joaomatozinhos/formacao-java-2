package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.modelo.Produto;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Produto celular = new Produto();
		celular.setNome("iPhone 14 Pro Max 1TB");
		celular.setDescricao("O melhor de todos");
		celular.setPreco(new BigDecimal("12000"));

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_hibernate_loja");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();
		em.persist(celular);
		em.getTransaction().commit();
		em.close();
	}
}
