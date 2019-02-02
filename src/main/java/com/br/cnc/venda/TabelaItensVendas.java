package com.br.cnc.venda;

import java.math.BigDecimal;
import java.util.List;

import com.br.cnc.model.ItemVenda;

public class TabelaItensVendas {

	private List<ItemVenda>itens;
	
	public BigDecimal getValorTotal() {
		
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
}
