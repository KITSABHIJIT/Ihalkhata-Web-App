// /Ajax function

function ajaxFunction(htmlMethod, urlString, boolAsync, params1) {

	var xmlhttp;
	var response = null;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (boolAsync) {
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				response = xmlhttp.responseText;
			}
		};

	}

	if (htmlMethod == "GET") {
		xmlhttp.open(htmlMethod, urlString + "?" + params1, boolAsync);
		xmlhttp.send();
	} else {
		xmlhttp.open(htmlMethod, urlString, boolAsync);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(params1);
	}

	if (!boolAsync) {
		response = xmlhttp.responseText;
	}

	return response;
}
// alert("aft ajaxfunf2");
