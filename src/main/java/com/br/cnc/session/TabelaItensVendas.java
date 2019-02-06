package com.br.cnc.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.br.cnc.model.ItemVenda;
import com.br.cnc.model.Produto;

@SessionScope
@Component
public class TabelaItensVendas {

	private List<ItemVenda>itens = new ArrayList<>();
	private int total;
	
	
	public BigDecimal getValorTotal() {
		
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Produto produto, Integer quantidade) {
		Optional<ItemVenda>itemVendaOptional = itens.stream()
			.filter(i -> i.getProduto().getId().equals(produto.getId()))
			.findAny();
		
		ItemVenda itemvenda = null;
		if(itemVendaOptional.isPresent()) {
			itemvenda = itemVendaOptional.get();
			itemvenda.setQuantidade(itemvenda.getQuantidade() + quantidade);
		}else {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(quantidade);
			itemVenda.setValorUnitario(produto.getValor());			
			itens.add(0,itemVenda);
		}
		
		
	}
	
	

	public int getTotal() {
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		
		return itens;
	}
}
