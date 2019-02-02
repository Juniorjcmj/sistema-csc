package com.br.cnc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.br.cnc.venda.TabelaItensVendas;

public class TabelaItensVendasTest {

	private TabelaItensVendas tabelaItensVendas;
	
	@Before
	public void setUp() {
		this.tabelaItensVendas = new TabelaItensVendas();
	}
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
	   assertEquals(BigDecimal.ZERO,tabelaItensVendas.getValorTotal());
	}
}
