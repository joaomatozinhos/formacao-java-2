package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDAO {

	private EntityManager em;

	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}

	public void cadastra(Produto produto) {
		this.em.persist(produto);
	}

	public Produto buscaPorId(Long id) {
		return em.find(Produto.class, id);
	}

	public List<Produto> buscaTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscaPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
		return em.createQuery(jpql, Produto.class).setParameter(1, nome).getResultList();
	}

	public List<Produto> buscaPorNomeEPreco(String nome, BigDecimal preco) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1 AND p.preco = ?2";
		return em.createQuery(jpql, Produto.class).setParameter(1, nome).setParameter(2, preco).getResultList();
	}

	public List<Produto> buscaPorNomeDaCategoria(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
		return em.createQuery(jpql, Produto.class).setParameter(1, nome).getResultList();
	}

}
