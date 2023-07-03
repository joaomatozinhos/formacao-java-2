package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JpaUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		// populaBancoDeDados();
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		ClienteDAO clienteDao = new ClienteDAO(em);
		Produto produto = produtoDao.buscaPorId(3l);
		Cliente cliente = clienteDao.buscaPorId(1l);

		em.getTransaction().begin();

		Pedido pedido = new Pedido(cliente);
		pedido.adicionaItem(new ItemPedido(10, pedido, produto));

		PedidoDAO pedidoDao = new PedidoDAO(em);
		pedidoDao.cadastra(pedido);

		em.getTransaction().commit();

		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("VALOT TOTAL VENDIDO: " + totalVendido);

		em.close();
	}

	private static void populaBancoDeDados() {
		Categoria categoriaRelogio = new Categoria("RELOGIOS");
		Produto relogio = new Produto("Apple Watch Ultra", "A aventura te espera.", new BigDecimal("10000"),
				categoriaRelogio);
		Cliente cliente = new Cliente("Rodolfo", "58961120389");

		EntityManager em = JpaUtil.getEntityManager();
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		ClienteDAO clienteDao = new ClienteDAO(em);

		em.getTransaction().begin();

		categoriaDao.cadastra(categoriaRelogio);
		produtoDao.cadastra(relogio);
		clienteDao.cadastra(cliente);

		em.getTransaction().commit();
		em.close();
	}
}
