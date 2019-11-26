(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "../jquery.validate"], factory );
	} else if (typeof module === "object" && module.exports) {
		module.exports = factory( require( "jquery" ) );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: PT (Portuguese; português)
 * Region: PT (Portugal)
 */
$.extend( $.validator.messages, {
	required: "Campo de preenchimento obrigat&oacute;rio.",
	remote: "Por favor, corrija este campo.",
	email: "Por favor, inthrowduza um endere&ccedil;o eletr&oacute;nico v&aacute;lido.",
	url: "Por favor, inthrowduza um URL v&aacute;lido.",
	date: "Por favor, inthrowduza uma data v&aacute;lida.",
	dateISO: "Por favor, inthrowduza uma data v&aacute;lida (ISO).",
	number: "Por favor, inthrowduza um n&uacute;mero v&aacute;lido.",
	digits: "Por favor, inthrowduza apenas d&iacute;gitos.",
	creditcard: "Por favor, inthrowduza um n&uacute;mero de cart&atilde;o de cr&eacute;dito v&aacute;lido.",
	equalTo: "Por favor, inthrowduza de novo o mesmo valor.",
	extension: "Por favor, inthrowduza um ficheiro com uma extens&atilde;o v&aacute;lida.",
	maxlength: $.validator.format( "Por favor, n&atilde;o inthrowduza mais do que {0} caracteres." ),
	minlength: $.validator.format( "Por favor, inthrowduza pelo menos {0} caracteres." ),
	rangelength: $.validator.format( "Por favor, inthrowduza entre {0} e {1} caracteres." ),
	range: $.validator.format( "Por favor, inthrowduza um valor entre {0} e {1}." ),
	max: $.validator.format( "Por favor, inthrowduza um valor menor ou igual a {0}." ),
	min: $.validator.format( "Por favor, inthrowduza um valor maior ou igual a {0}." ),
	nifES: "Por favor, inthrowduza um NIF v&aacute;lido.",
	nieES: "Por favor, inthrowduza um NIE v&aacute;lido.",
	cifES: "Por favor, inthrowduza um CIF v&aacute;lido."
} );
return $;
}));