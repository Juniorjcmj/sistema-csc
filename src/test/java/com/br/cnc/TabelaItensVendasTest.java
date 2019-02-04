package com.br.cnc;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.cnc.model.Produto;
import com.br.cnc.session.TabelaItensVendas;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TabelaItensVendasTest {

	private TabelaItensVendas tabelaItensVendas;
	
	@Before
	public void setUp() {
		this.tabelaItensVendas = new TabelaItensVendas();
	}
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
	   assertEquals(BigDecimal.ZERO, tabelaItensVendas.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem()throws Exception{		
		Produto produto = new Produto();		
		BigDecimal valor = new BigDecimal("8.90");
		produto.setValor(valor);
		
		tabelaItensVendas.adicionarItem(produto, 1);
		
		assertEquals(valor,  tabelaItensVendas.getValorTotal());
	}
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {
		Produto p1 = new Produto();		
		BigDecimal v1 = new BigDecimal("8.90");
		p1.setValor(v1);
		
		Produto p2 = new Produto();		
		BigDecimal v2 = new BigDecimal("4.99");
		p2.setValor(v2);
		
		tabelaItensVendas.adicionarItem(p1, 1);
		tabelaItensVendas.adicionarItem(p2, 2);
		
		assertEquals(new BigDecimal("18.88"),tabelaItensVendas.getValorTotal());
	}
}












