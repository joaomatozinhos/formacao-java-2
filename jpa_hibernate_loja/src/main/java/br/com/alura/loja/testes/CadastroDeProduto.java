package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JpaUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Categoria categoriaCelulares = new Categoria("CELULARES");
		Produto celular = new Produto("iPhone 14 Pro Max 1TB", "O melhor de todos", new BigDecimal("12000"),
				categoriaCelulares);

		EntityManager em = JpaUtil.getEntityManager();
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		ProdutoDAO produtoDao = new ProdutoDAO(em);

		em.getTransaction().begin();
		categoriaDao.cadastra(categoriaCelulares);
		produtoDao.cadastra(celular);
		em.getTransaction().commit();
		em.close();
	}
}
