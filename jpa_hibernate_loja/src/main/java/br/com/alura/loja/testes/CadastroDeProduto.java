package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JpaUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
//		cadastraProduto();
		buscaProdutos();
	}

	private static void buscaProdutos() {
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);

		Long id = 1l;
		Produto p = produtoDao.buscaPorId(id);
		System.out.println(p.getPreco());

		List<Produto> todosProdutos = produtoDao.buscaTodos();
		todosProdutos.forEach(produto -> System.out.println(produto.getNome()));

		List<Produto> produtosPorNome = produtoDao.buscaPorNome("iPhone 12");
		produtosPorNome.forEach(produto -> System.out.println(produto.getNome()));

		List<Produto> produtosPorNomeEPreco = produtoDao.buscaPorNomeEPreco("iPhone 14 Pro Max", new BigDecimal(12000));
		produtosPorNomeEPreco.forEach(produto -> System.out.println(produto.getNome()));

		List<Produto> produtosPorCategoria = produtoDao.buscaPorNomeDaCategoria("CELULARES");
		produtosPorCategoria.forEach(produto -> System.out.println(produto.getNome()));

		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("iPhone 14 Pro");
		System.out.println(precoDoProduto);
	}

	private static void cadastraProduto() {
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
