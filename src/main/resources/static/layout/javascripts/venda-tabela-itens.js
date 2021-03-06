Brewer.TabelaItens =(function(){
	
	function TabelaItens(autocomplete){
		this.autocomplete = autocomplete;
		this.tabelaCervejaContainer = $('.js-tabela-cerveja-container');
		this.uuid = $('#uuid').val();
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
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
		
		var quantidadeItemInput = $('.js-tabela-cerveja-quantidade-item');
		quantidadeItemInput.on('change', onQuantidadeItemAlterado.bind(this));
		quantidadeItemInput.maskMoney({precision: 0, thousands: ''});
		
		var tabelaItem = $('.js-tabela-item');
		tabelaItem.on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
		
		this.emitter.trigger('tabela-itens-atualizada', tabelaItem.data('valor-total'));
	}
	function onQuantidadeItemAlterado(evento){
		var input = $(evento.target);
		var quantidade = input.val();
			if(quantidade<=0){
				input.val(1);
				quantidade = 1;
			}
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