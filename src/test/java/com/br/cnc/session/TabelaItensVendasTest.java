package com.br.cnc.session;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.br.cnc.session.TabelaItensVendas;
import com.br.cnc.model.Produto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TabelaItensVendasTest {

	private TabelaItensVendas tabelaItensVendas;
	
	@Before
	public void setUp() {
		this.tabelaItensVendas = new TabelaItensVendas("1");
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
		p1.setId(1);
		BigDecimal v1 = new BigDecimal("8.90");
		p1.setValor(v1);
		
		Produto p2 = new Produto();
		p2.setId(2);
		BigDecimal v2 = new BigDecimal("4.99");
		p2.setValor(v2);
		
		tabelaItensVendas.adicionarItem(p1, 1);
		tabelaItensVendas.adicionarItem(p2, 2);
		
		assertEquals(new BigDecimal("18.88"),tabelaItensVendas.getValorTotal());
	}
	
	@Test
	public void deveManterTamanhoDaListaParaMesmosProdutos() throws Exception {
		Produto p1 = new Produto();	
		p1.setId(1);
		p1.setValor(new BigDecimal("4.50"));
		
		tabelaItensVendas.adicionarItem(p1, 1);
		tabelaItensVendas.adicionarItem(p1, 1);
		
		assertEquals(1, tabelaItensVendas.getTotal());
		assertEquals(new BigDecimal("9.00"), tabelaItensVendas.getValorTotal());
	}
	@Test
	public void deveAlterarQuantidadeItem() throws Exception {
		Produto p1 = new Produto();	
		p1.setId(1);
		p1.setValor(new BigDecimal("4.50"));
		
		tabelaItensVendas.adicionarItem(p1, 1);
		tabelaItensVendas.alterarQuantidadeItens(p1, 3);
		
		assertEquals(new BigDecimal("13.50"), tabelaItensVendas.getValorTotal());
	}
	
	@Test
	public void deveEscluirItem() throws Exception {
		Produto p1 = new Produto();	
		p1.setId(1);
		BigDecimal v1 = new BigDecimal("8.90");
		p1.setValor(v1);
		
		Produto p2 = new Produto();
		p2.setId(2);
		BigDecimal v2 = new BigDecimal("4.99");
		p2.setValor(v2);
		
		Produto p3 = new Produto();
		p3.setId(3);
		BigDecimal v3 = new BigDecimal("2.00");
		p3.setValor(v3);
		
		tabelaItensVendas.adicionarItem(p1, 1);
		tabelaItensVendas.adicionarItem(p2, 2);
		tabelaItensVendas.adicionarItem(p3, 1);
		
		tabelaItensVendas.excluirItem(p2);
		
		assertEquals(2, tabelaItensVendas.getTotal());
		assertEquals(new BigDecimal("10.90"),tabelaItensVendas.getValorTotal());
	}
}












