Brewer.TabelaItens =(function(){
	
	function TabelaItens(autocomplete){
		this.autocomplete = autocomplete;
		this.tabelaCervejaContainer = $('.js-tabela-cerveja-container');
		this.uuid = $('#uuid').val();
	}	
	TabelaItens.prototype.iniciar = function(){
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
		
	}	
	function onItemSelecionado(evento, item){
		
			var resposta = $.ajax({
				url: 'item',
				method: 'POST',
				data: {
					idProduto: item.id,
					uuid: this.uuid 
				}
			});
			
			resposta.done(onItemAdicionadoNoServidor.bind(this));		
			
	     }
	function onItemAdicionadoNoServidor(html){
		this.tabelaCervejaContainer.html(html);
		$('.js-tabela-cerveja-quantidade-item').on('change', onQuantidadeItemAlterado.bind(this));
		$('.js-tabela-item').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
	}
	function onQuantidadeItemAlterado(evento){
		var input = $(evento.target);
		var quantidade = input.val();
		var idProduto = input.data('codigo-produto');
		
		var resposta = $.ajax({
			url: 'item/' + idProduto,
			method:'PUT',
			data:{
				quantidade: quantidade,
				uuid: this.uuid 
			}
		});
		resposta.done(onItemAdicionadoNoServidor.bind(this));
	}
	function onDoubleClick(evento){
		$(this).toggleClass('solicitando-exclusao');
	}
	function onExclusaoItemClick(evento){
		var idProduto = $(evento.target).data('codigo-produto');
		
		var resposta = $.ajax({
			url: 'item/'+ this.uuid+ '/' + idProduto,
			method:'DELETE'			
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