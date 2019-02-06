var Brewer = Brewer || {};

Brewer.Autocomplete = (function() {
	
	
	function Autocomplete() {
		this.descricaoInput = $('.js-descricao-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-produto').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(descricao) {
				return this.descricaoInput.data('url')+'?descricao='+descricao;
			}.bind(this),
			getValue:'descricao',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template:{
				type: 'custom',
				method:template.bind(this)
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this)				
			}				
		};		
		this.descricaoInput.easyAutocomplete(options);
	}
	function onItemSelecionado(){
		this.emitter.trigger('item-selecionado',this.descricaoInput.getSelectedItemData());
		this.descricaoInput.val('');
		this.descricaoInput.focus();
		
	}

	function template(descricao, produto){
			return this.template(produto);			
		}	
	return Autocomplete	
}());

