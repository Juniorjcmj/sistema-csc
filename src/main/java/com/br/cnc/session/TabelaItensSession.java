package com.br.cnc.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.br.cnc.model.ItemVenda;
import com.br.cnc.model.Produto;

@SessionScope
@Component
public class TabelaItensSession {
	
	private Set<TabelaItensVendas>tabelas = new HashSet<>();
	
	public void adicionarItem(String uuid, Produto produto, int quantidade) {
		TabelaItensVendas tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(produto, quantidade);
		tabelas.add(tabela);
		
	}	

	public void alterarQuantidadeItens(String uuid, Produto produto, Integer quantidade) {
		TabelaItensVendas tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItens(produto, quantidade);
		
	}

	public void excluirItem(String uuid, Produto produto) {
		TabelaItensVendas tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(produto);
	}

	public List<ItemVenda> getItens(String uuid) {		
		return buscarTabelaPorUuid(uuid).getItens();
	}

	public Object getValorTotal(String uuid) {		
		return buscarTabelaPorUuid(uuid).getValorTotal();
	}
	
	
	private TabelaItensVendas buscarTabelaPorUuid(String uuid) {
		TabelaItensVendas tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensVendas(uuid));
		return tabela;
	}



	

	

}
