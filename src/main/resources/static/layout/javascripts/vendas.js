Brewer.Venda = (function(){

	function Venda(tabelaItens){
			this.tabelaItens = tabelaItens;
			this.valorTotalBox = $('.js-valor-total-box');
			//this.valorFreteInput = $('#valorFrete');
			//this.valorDescontoInput = $('#valorDesconto');			
			
	}
	Venda.prototype.iniciar = function(){
		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));
		

		
	}

	function onTabelaItensAtualizada(evento, valorTotalItens){
		this.valorTotalItens =  valorTotalItens == null ? 0 : valorTotalItens;
		this.valorTotalBox.html(valorTotalItens.toLocaleString('pt-BR'));
	}

		
	return Venda;
}());
		


$(function(){
	
	var autocomplete = new  Brewer.Autocomplete();
	autocomplete.iniciar();  
	
	var tabelaItens = new Brewer.TabelaItens(autocomplete);
	tabelaItens.iniciar();

	var venda = new Brewer.Venda(tabelaItens);
	venda.iniciar();
	
});
  





