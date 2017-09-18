var outerWidth = window.screen.width - 50;

// decimal determination function.....

function roundNumber(num, dec) {
	var result = Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec);
	return result;
}

function isEmpty(data) {
	if (data == null || data == undefined || data == "")
		return true;
	else
		return false;
}
if (!String.prototype.trim) {
	 String.prototype.trim = function() {
	  return this.replace(/^\s+|\s+$/g,'');
	 }
	}
function getStartMonth(selectionType) {
	var result = 0;
	var d = new Date();
	var month = d.getMonth() + 1;
	if (selectionType == '0')
		result = month;
	else if (selectionType == '1') {
		result = (month === 1) ? 12 : (month - 1);
	} else if (selectionType == '2') {
		if (month === 1)
			result = 11;
		else if (month === 2)
			result = 12;
		else
			result = month - 2;

	} else if (selectionType == '3')
		result = month;
	return result;
}
function getEndMonth(selectionType) {
	var result = 0;
	var d = new Date();
	var month = d.getMonth() + 1;
	if (selectionType == '0')
		result = month;
	else if (selectionType == '1') {
		result = (month === 1) ? 12 : (month - 1);
	} else if (selectionType == '2')
		result = month;
	else if (selectionType == '3')
		result = (month === 1) ? 12 : (month - 1);
	return result;

}
function getStartYear(selectionType) {
	var result = 0;
	var d = new Date();
	var month = d.getMonth() + 1;
	var year = d.getFullYear();
	if (selectionType == '0')
		result = year;
	else if (selectionType == '1') {
		result = (month === 1) ? (year - 1) : year;
	} else if (selectionType == '2') {
		if (month === 1)
			result = (year - 1);
		else if (month === 2)
			result = (year - 1);
		else
			result = year;
	} else if (selectionType == '3')
		result = (year - 1);
	return result;

}
function getEndYear(selectionType) {
	var result = 0;
	var d = new Date();
	var month = d.getMonth() + 1;
	var year = d.getFullYear();
	if (selectionType == '0')
		result = year;
	else if (selectionType == '1') {
		result = (month === 1) ? (year - 1) : year;
	} else if (selectionType == '2')
		result = year;
	else if (selectionType == '3')
		result = (month === 1) ? (year - 1) : year;
	return result;

}
function limiter(count,textCmp,limitCmp,type){
	var tex = document.getElementById(textCmp).value;
	var len = tex.length;
	if(len > count){
	        tex = tex.substring(0,count);
	        document.getElementById(textCmp).value =tex;
	        return false;
	}
	var limitValue=count-len;
	var limitText="";
	limitText=(limitValue>1)?"<font color='blue'><i>"+ limitValue+" Characters left</i></font>":"<font color='red'><b>"+limitValue+" Character left</b></font>";
	
	if(type==='html')
		document.getElementById(limitCmp).innerHTML =limitText;
	else
		Ext.getCmp(limitCmp).setValue(limitText);

	}

function roundNumberGrid(val, meta, record) {
    if(isEmpty(val))
    	return '0.00';
    else
    	return parseFloat(Math.round(val * 100) / 100).toFixed(2);
}
