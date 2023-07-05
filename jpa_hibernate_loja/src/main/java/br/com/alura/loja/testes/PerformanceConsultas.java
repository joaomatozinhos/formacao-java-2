package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JpaUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		PedidoDAO pedidoDao = new PedidoDAO(em);
		Pedido pedido = pedidoDao.buscaPedidoComCliente(6l);
		em.close();
		System.out.println(pedido.getCliente().getNome());
	}
}
