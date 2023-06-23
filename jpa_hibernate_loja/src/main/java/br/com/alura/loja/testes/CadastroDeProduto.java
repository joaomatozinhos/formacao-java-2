package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JpaUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Categoria categoriaCelulares = new Categoria("CELULARES");

//		Produto celular = new Produto("iPhone 14 Pro Max 1TB", "O melhor de todos", new BigDecimal("12000"),
//				categoriaCelulares);

		EntityManager em = JpaUtil.getEntityManager();

//		CategoriaDAO categoriaDao = new CategoriaDAO(em);
//		ProdutoDAO produtoDao = new ProdutoDAO(em);

		em.getTransaction().begin();

		em.persist(categoriaCelulares);
		categoriaCelulares.setNome("XPTO");

//		categoriaDao.cadastra(categoriaCelulares);
//		produtoDao.cadastra(celular);

		em.flush();
		em.clear();

		categoriaCelulares = em.merge(categoriaCelulares);
		categoriaCelulares.setNome("1234");
		em.flush();
		em.clear();
		em.remove(categoriaCelulares);
		em.flush();
	}
}
