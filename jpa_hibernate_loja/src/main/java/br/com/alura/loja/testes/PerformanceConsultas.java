package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JpaUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();

		Pedido pedido = em.find(Pedido.class, 3l);
		System.out.println(pedido.getCliente().getNome());
	}
}
