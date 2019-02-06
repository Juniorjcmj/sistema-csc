Brewer.TabelaItens =(function(){
	
	function TabelaItens(autocomplete){
		this.autocomplete = autocomplete;
		this.tabelaCervejaContainer = $('.js-tabela-cerveja-container');
	}	
	TabelaItens.prototype.iniciar = function(){
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
		
	}	
	function onItemSelecionado(evento, item){
		
			var resposta = $.ajax({
				url: 'item',
				method: 'POST',
				data: {
					idProduto: item.id
				}
			});
			
			resposta.done(onItemAdicionadoNoServidor.bind(this));		
			
	     }
	function onItemAdicionadoNoServidor(html){
		this.tabelaCervejaContainer.html(html);
		$('.js-tabela-cerveja-quantidade-item').on('change', onQuantidadeItemAlterado.bind(this));
		
	}
	function onQuantidadeItemAlterado(evento){
		var input = $(evento.target);
		var quantidade = input.val();
		var idProduto = input.data('codigo-produto');
		
		var resposta = $.ajax({
			url: 'item/' + idProduto,
			method:'PUT',
			data:{
				quantidade: quantidade
			}
		});
		resposta.done(onItemAdicionadoNoServidor.bind(this));
	}
	
	return TabelaItens
}());

$(function(){
	
	var autocomplete = new  Brewer.Autocomplete();
	autocomplete.iniciar();  
	
	var tabelaItens = new Brewer.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
});