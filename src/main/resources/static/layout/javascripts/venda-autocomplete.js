Autocomplete = (function() {
	
	function Autocomplete() {
		this.descricaoInput = $('.js-descricao-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-produto').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(descricao) {
				return  '/produto?descricao=' + descricao;
			},
			getValue:'descricao',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template:{
				type: 'custom',
				method:function(nome, produto){
					return this.template(produto);
				}.bind(this)
			}
				
		};
		
		this.descricaoInput.easyAutocomplete(options);
	}
	
	return Autocomplete
	
}());
$(function(){
	var autocomplete = new Autocomplete();
	autocomplete.iniciar();
	
})

