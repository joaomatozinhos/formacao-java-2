package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVO {

	private String nomeDoProduto;
	private Long quantidadeVendida;
	private LocalDate dataUltimaVenda;

	public RelatorioDeVendasVO(String nomeDoProduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
		this.nomeDoProduto = nomeDoProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}

	public String getNomeDoProduto() {
		return nomeDoProduto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}

	@Override
	public String toString() {
		return "RelatorioDeVendasVO [nomeDoProduto=" + nomeDoProduto + ", quantidadeVendida=" + quantidadeVendida
				+ ", dataUltimaVenda=" + dataUltimaVenda + "]";
	}

}
