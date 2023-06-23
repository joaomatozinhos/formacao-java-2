package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDAO {

	private EntityManager em;

	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}

	public void cadastra(Categoria categoria) {
		this.em.persist(categoria);
	}

	public void atualiza(Categoria categoria) {
		this.em.merge(categoria);
	}

	public void remove(Categoria categoria) {
		categoria = this.em.merge(categoria);
		this.em.remove(categoria);
	}

}
