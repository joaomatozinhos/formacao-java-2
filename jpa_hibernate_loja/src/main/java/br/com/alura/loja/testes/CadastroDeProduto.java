package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JpaUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Produto celular = new Produto("iPhone 14 Pro Max 1TB", "O melhor de todos", new BigDecimal("12000"),
				Categoria.CELULARES);

		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);

		em.getTransaction().begin();
		produtoDao.cadastra(celular);
		em.getTransaction().commit();
		em.close();
	}
}
