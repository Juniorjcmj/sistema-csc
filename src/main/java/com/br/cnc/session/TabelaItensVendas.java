package com.br.cnc.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.br.cnc.model.ItemVenda;
import com.br.cnc.model.Produto;


class TabelaItensVendas {

	private String uuid;
	private List<ItemVenda>itens = new ArrayList<>();
	private int total;
	
	public TabelaItensVendas(String uuid) {
		this.uuid = uuid;
	}
	
	
	public BigDecimal getValorTotal() {
		
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Produto produto, Integer quantidade) {
		Optional<ItemVenda> itemVendaOptional = buscarItemProduto(produto);
		
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

	
	public void alterarQuantidadeItens(Produto produto, Integer quantidade) {
		ItemVenda itemVenda = buscarItemProduto(produto).get();
		itemVenda.setQuantidade(quantidade);
	}
	
	public void excluirItem(Produto produto) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getProduto().getId().equals(produto.getId()))
				.findAny().getAsInt();
		itens.remove(indice);
	}

	public int getTotal() {
		return itens.size();
	}

	public List<ItemVenda> getItens() {		
		return itens;
	}
	private Optional<ItemVenda> buscarItemProduto(Produto produto) {
		Optional<ItemVenda>itemVendaOptional = itens.stream()
			.filter(i -> i.getProduto().getId().equals(produto.getId()))
			.findAny();
		return itemVendaOptional;
	}
	

	public String getUuid() {
		return uuid;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensVendas other = (TabelaItensVendas) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	
	
	
}
