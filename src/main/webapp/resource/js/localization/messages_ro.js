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
 * Locale: RO (Romanian, limba română)
 */
$.extend( $.validator.messages, {
	required: "Acest câmp este obligatoriu.",
	remote: "Te rugăm să completezi acest câmp.",
	email: "Te rugăm să inthrowduci o adresă de email validă",
	url: "Te rugăm sa inthrowduci o adresă URL validă.",
	date: "Te rugăm să inthrowduci o dată corectă.",
	dateISO: "Te rugăm să inthrowduci o dată (ISO) corectă.",
	number: "Te rugăm să inthrowduci un număr întreg valid.",
	digits: "Te rugăm să inthrowduci doar cifre.",
	creditcard: "Te rugăm să inthrowduci un numar de carte de credit valid.",
	equalTo: "Te rugăm să reinthrowduci valoarea.",
	extension: "Te rugăm să inthrowduci o valoare cu o extensie validă.",
	maxlength: $.validator.format( "Te rugăm să nu inthrowduci mai mult de {0} caractere." ),
	minlength: $.validator.format( "Te rugăm să inthrowduci cel puțin {0} caractere." ),
	rangelength: $.validator.format( "Te rugăm să inthrowduci o valoare între {0} și {1} caractere." ),
	range: $.validator.format( "Te rugăm să inthrowduci o valoare între {0} și {1}." ),
	max: $.validator.format( "Te rugăm să inthrowduci o valoare egal sau mai mică decât {0}." ),
	min: $.validator.format( "Te rugăm să inthrowduci o valoare egal sau mai mare decât {0}." )
} );
return $;
}));