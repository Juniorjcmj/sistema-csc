var Brewer = Brewer || {};

Brewer.MaskMoney = (function() {
	
	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
		
	}
	
	MaskMoney.prototype.enable = function() {
		//this.decimal.maskMoney({ decimal: ',', thousands: '.' });
		//this.plain.maskMoney({ precision: 0, thousands: '.' });
		this.decimal.maskNumber({ decimal: '.', thousands: '.' });
		this.plain.maskNumber({ integer: true, thousands: '.' });
	}
	
	return MaskMoney;
	
}());



Brewer.formatarMoeda = function(valor) {		
	return numeral(valor).format('0.0.00');
}

Brewer.recuperarValor = function(valorFormatado) {	
	return valorFormatado;
}

$(function() {
	var maskMoney = new Brewer.MaskMoney();
	maskMoney.enable();
	
	
	
	
});