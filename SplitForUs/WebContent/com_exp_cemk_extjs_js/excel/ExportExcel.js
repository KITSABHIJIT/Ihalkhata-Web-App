function exportGridToExcel(grid)

{

// if data is in JSON format

//	var jsonData = gridDataToJson(grid);

//	initiateFileDownloadPost(uriprefix+'/jsp/ExportToExcel.jsp',jsonData)



	var title = "";

	var gridHeader = grid.title;

	try

	{

		var parentTabPanel = grid.findParentByType('tabpanel');

		while(parentTabPanel)

		{

			var parentTab = parentTabPanel.getActiveTab();

			if(title =="")

			{

				title = parentTab.title

			}

			else

			{

				title = parentTab.title + "-"+ title;

			}

			parentTabPanel = parentTabPanel.findParentByType('tabpanel');

		}

		if(gridHeader != undefined)

		{

			title = title + "-" + gridHeader

		}

	}

	catch(e){}

	var htmlTableData = gridDataToHTMLTable(grid,title);

	initiateFileDownloadPost(uriPrefix+'/com_exp_cemk_jsp/ExportToExcel.jsp',title,htmlTableData);

}

function	initiateFileDownloadPost(uri,title,data)

{	

	var iframe;

	try

	{

	  iframe = document.createElement('<iframe name="iframed" id="iframed">');

	}

	catch (ex)

	{

	  iframe = document.createElement('iframe');

	}

	//iframe.src = uri;

	iframe.setAttribute('name','iframed');

	iframe.setAttribute('id','iframed');

	iframe.style.display = "none";

	document.body.appendChild(iframe);
	
	var form = document.createElement("form");

	form.setAttribute('action',uri);

	form.setAttribute('method','post');

	form.setAttribute('target','iframed');

	var titleFld = document.createElement("input");

	titleFld.setAttribute('type','text');

	titleFld.setAttribute('name','title');

	titleFld.setAttribute('value',title);

	form.appendChild(titleFld);

	var input = document.createElement("input");

	input.setAttribute('type','text');

	input.setAttribute('name','data');

	input.setAttribute('value',data);

	form.appendChild(input);

	var submit = document.createElement("input");

	submit.setAttribute('type','submit');

	form.appendChild(submit);

	form.style.display = "none";

	document.body.appendChild(form);

    form.submit();

    //document.body.removeChild(form);

}

function gridDataToHTMLTable(grid,title)

{

	var htmlTableData = "<table border=\"1\">";

	var headers = new Array();

	var rows = new Array();

	var invalidColumnIndices = new Array();

	var store = grid.getStore();

	var dataIndices = new Array();

	var recordsCount = store.getTotalCount();

	var columnModel = grid.getColumnModel();

	var columnCount = columnModel.getColumnCount();

	for(var c=0; c<columnCount; c++)

	{

		var header = columnModel.getColumnHeader(c);

		if(header.indexOf("x-grid3-hd-checker") > -1)

		{

			invalidColumnIndices.push(c);

		}

		else

		{

			headers.push(columnModel.getColumnHeader(c));

		}

		dataIndices.push(columnModel.getDataIndex(c));

	}

	

	try

	{

		for(var rowNum=0 ; rowNum<recordsCount; rowNum++)

		{

			var row = new Array();

			var rec = store.getAt(rowNum);

			for(var ci=0; ci<columnCount; ci++)

			{

				var renderer = columnModel.getRenderer(ci);

				row.push(renderer(rec.get(dataIndices[ci]),null,rec,rowNum,store));

				//row.push(rec.get(dataIndices[ci]));

			}

			for(var ici=0; ici<invalidColumnIndices.length; ici++)

			{

				row.splice(invalidColumnIndices[ici],invalidColumnIndices[ici]+1);

			}

			rows.push(row);

		}

	}

	catch(e){}
	var gridTitle=title+'-Exported Data';
	var colspan=headers.length;
	htmlTableData+="<tr><td colspan='"+colspan+"'><center><h3><u><font color='blue'>"+gridTitle+"</font></u></h3></center></td><tr/><tr><tdcolspan='"+colspan+"'> </td></tr>";

	

	//header row

	htmlTableData+="<tr>";

	for(var h=0;h<headers.length;h++)

	{

		htmlTableData = htmlTableData + "<th><font color='green'>" + headers[h] + "</font></th>";

	}

	htmlTableData+="</tr>";

	

	//rows

	for(var r=0;r<rows.length;r++)

	{

		htmlTableData+="<tr>";

		var row = rows[r];

		for(var c=0;c<row.length;c++)

		{

			htmlTableData = htmlTableData + "<td>" + row[c] + "</td>";

		}

		htmlTableData+="</tr>";

	}

	

	htmlTableData+="</table>";

	

	return htmlTableData;

}







//---------------------------Other Util Functions:-------------------








function	openNewTab(parentTab,title,child,id, forceReload, notClosable)

{


	if(forceReload)

	{

		var existingTab=parentTab.findById(id);

		var existingTabItems;

		if(existingTab)

		{

			parentTab.remove(existingTab, false);

		}		

	}

	else

	{

		var existingTab=parentTab.getItem(id);

		if(existingTab)

		{

			parentTab.activate(existingTab);

		return;

		}

	}

		

	var childN = parentTab.add({

		title		: 	title

		,id			:	id

		,layout		:	'fit'

		,iconCls	: 	'tabs'

		,items 		: 	[child]

		,closable	:	!notClosable

		,autoDestroy : true

	}).show();

	

	parentTab.doLayout();

	

	return childN;

}



function	makeClickableText(text)

{

	return	"<span style='color:blue;cursor:pointer;'>"+text+"</span>";	

}



function	makeTextInactive(text)

{

	return	"<span style='color:gray;font-style:italic'>"+text+"</span>";

}

function isStringEmpty(str)

{

	if(str == undefined)

		return true;

	if(str == null)

		return true;

	if(str.trim() == "")

		return true;

	return false;

}

function	initiateFileDownload(uri)

{

	var iframe = document.createElement("iframe");

	iframe.src = uri;

	iframe.style.display = "none";

	document.body.appendChild(iframe);

}
function	handleBlankString(item)

{

	if(item)

	{

		return	"<span>"+item+"</span>";

	}

	else

	{

		return	"<span style='color:gray;font-style:italic'>Unavailable</span>";

	}		

}

function	getScreenHeight()

{

    var D = document;

    return Math.max(

        Math.max(D.body.scrollHeight, D.documentElement.scrollHeight),

        Math.max(D.body.offsetHeight, D.documentElement.offsetHeight),

        Math.max(D.body.clientHeight, D.documentElement.clientHeight)

    );

}
function getMaxHeight()
{

	var D = document;

    return Math.max(

        Math.max(D.body.scrollHeight, D.documentElement.scrollHeight),

        Math.max(D.body.offsetHeight, D.documentElement.offsetHeight),

        Math.max(D.body.clientHeight, D.documentElement.clientHeight)

    ) - 106;

}

function getMaxWidth()

{

	// not tested ... just replaced 'Height' with 'Width'

    var D = document;

    return Math.max(

        Math.max(D.body.scrollWidth, D.documentElement.scrollWidth),

        Math.max(D.body.offsetWidth, D.documentElement.offsetWidth),

        Math.max(D.body.clientWidth, D.documentElement.clientWidth)

    ) - 106;



}



Ext.apply(Ext.form.VTypes, {

	daterange : function(val, field) {

	    var date = field.parseDate(val);

	

	    if(!date){

	        return false;

	    }

	    if (field.startDateField) {

	        var start = Ext.getCmp(field.startDateField);

	        if (!start.maxValue || (date.getTime() != start.maxValue.getTime())) {

	            start.setMaxValue(date);

	            start.validate();

	        }

	    }

	    else if (field.endDateField) {

	        var end = Ext.getCmp(field.endDateField);

	        if (!end.minValue || (date.getTime() != end.minValue.getTime())) {

	            end.setMinValue(date);

	            end.validate();

	        }

	    }

	}

});

function sleep(milliseconds)

{

	var start = new Date().getTime();

	for (var i = 0; i < 1e7; i++)

	{

		if ((new Date().getTime() - start) > milliseconds)

		{

			break;

		}

	}

}

function gridDataToJson(grid)
{

	var excelData = {};

	var headers = new Array();

	var rows = new Array();

	

	var invalidColumnIndices = new Array();

	var store = grid.getStore();

	var dataIndices = new Array();

	var recordsCount = store.getTotalCount();

	var columnModel = grid.getColumnModel();

	var columnCount = columnModel.getColumnCount();

	for(var c=0; c<columnCount; c++)

	{

		var header = columnModel.getColumnHeader(c)

		if(header.indexOf("x-grid3-hd-checker") > -1)

		{

			invalidColumnIndices.push(c);

		}

		else

		{

			headers.push(columnModel.getColumnHeader(c));

		}

		dataIndices.push(columnModel.getDataIndex(c));

	}

	

	try

	{

		for(var rowNum=0 ; rowNum<recordsCount; rowNum++)

		{

			var row = new Array();

			var rec = store.getAt(rowNum);

			for(var ci=0; ci<columnCount; ci++)

			{

				var renderer = columnModel.getRenderer(ci);

				row.push(renderer(rec.get(dataIndices[ci]),null,rec,rowNum,store));

				//row.push(rec.get(dataIndices[ci]));

			}

			for(var ici=0; ici<invalidColumnIndices.length; ici++)

			{

				row.splice(invalidColumnIndices[ici],invalidColumnIndices[ici]+1);

			}

			rows.push(row);

		}

	}

	catch(e){}

	var header="";

	if(grid.header == undefined)

	{

		

	}

	excelData['title'] = header;

	excelData['headers'] = headers;

	excelData['rows'] = rows;

	

	return Ext.util.JSON.encode(excelData);

}