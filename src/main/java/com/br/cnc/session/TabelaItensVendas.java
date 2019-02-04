package com.br.cnc.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.br.cnc.model.ItemVenda;
import com.br.cnc.model.Produto;

@Component
public class TabelaItensVendas {

	private List<ItemVenda>itens = new ArrayList<>();
	
	
	public BigDecimal getValorTotal() {
		
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Produto produto, Integer quantidade) {
		
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(quantidade);
		itemVenda.setValorUnitario(produto.getValor());
		
		itens.add(itemVenda);
	}
	
	public int total() {
		return itens.size();
	}
}
