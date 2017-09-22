/**
 * Handles FaQ page
 */

function about(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"width: 100%;\">" + "Mom&Pop is a rapidly developing brick-and-mortar company." +
				"Mom&Pop online bookstore is a place where you can buy your " +
				"favorite books with a coupe of clicks! The company takes pride in " +
				"low book prices and fast (and free) delivery." + "</div>";
	else
		x.innerHTML = "";
	
	return false;
}

function eLib(x) {
	
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"width: 100%;\">" + "eLibrary Development Team is a team of four aspiring sleepy students, who sacrificed their sleep to deliver a scalable and reliable product which takes advantage of modern technologies and design patterns.</div>";
	else
		x.innerHTML = "";
	return false;
}

function pay(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"width: 100%;\">Books must be purchased with a valid credit card (Mastercard or Visa)</div>";
	else
		x.innerHTML = "";
	return false;
}


function reg(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"width: 100%;\">You must be a regsitered user to be able to purchase books from Mom&Pop. This is done to protect clients.</div>";
	else
		x.innerHTML = "";
	return false;
}

function ship(x) {
	var a = x.innerHTML == "";

	if (a)
		x.innerHTML = "<div style=\"width: 100%;\">Shipment will take no more than 72 hours.</div>";
	else
		x.innerHTML = "";
	return false;
}