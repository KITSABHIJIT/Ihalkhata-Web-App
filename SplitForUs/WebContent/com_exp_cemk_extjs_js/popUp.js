function Connect() {

	  
	   var req = null; 

	   try {

	   if (window.XMLHttpRequest) {

	   req = new XMLHttpRequest();

	   if (req.overrideMimeType) {

	   req.overrideMimeType('text/xml');

	   }

	   } 

	   else if (window.ActiveXObject) {

	   try {

	   req = new ActiveXObject("Msxml2.XMLHTTP");

	   } catch (e) {

	   try {

	   req = new ActiveXObject("Microsoft.XMLHTTP");

	   } catch (e) {}

	   }

	   }

	   } catch(e) {

	   errHandler(e,'Connect()','commonAgent.js');

	   }

	   return req;

	   }

	
	   
	   ///Ajax function  
	   
	   function ajaxFunction(htmlMethod,string,method,params1) { 

			 
		 // alert("in ajaxfun2");
		   var req = Connect(); 

		   
		   var response;

		   req.onreadystatechange = function() {

		   if(req.readyState == 4) {


		   if(req.status == 200) {

			//  alert("bk frm servlet");
		   response=req.responseText;
//alert("response = "+response);
		   }

		   else {

		   response=null;

		   }

		   }

		   };

		   req.open(htmlMethod,string+"?"+params1,method); 

		   req.send(null);

		   // Added for Mozilla compatibility

		   if(req.readyState == 4) {

		   if(req.status == 200) {

		   response=req.responseText;

		   }

		   else { 

		   response=null;

		   }

		   }  
		   

		   if(response != undefined){ 
		//	   alert("returning response");

			  return response;

		   }
	}

Ext.onReady(function(){
    Ext.QuickTips.init();

    // NOTE: This is an example showing simple state management. During development,
    // it is generally best to disable state management as dynamically-generated ids
    // can change across page loads, leading to unpredictable results.  The developer
    // should ensure that stable state ids are set for stateful components in real apps.    
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

    // sample static data for the store
    

    // create the Grid
    
    var actionRes = '[{ }]';
    var actionData = Ext.util.JSON.decode(actionRes);
    
    var actionReader= new Ext.data.JsonReader(
			{						   
				              
			},[		
			    {name    : 'actid',mapping : 'actid'},
		        {name    : 'acttext',mapping : 'acttext'},
		        {name    : 'actdate',mapping : 'actdate'},
		        {name    : 'actcreated',mapping : 'actcreated'}
			]
		);
    
    
    
    
    var actionchart = new Ext.data.Store({  
    	data : actionData,
    	reader: actionReader
    });
    
    
    actionRes = ajaxFunction('POST','../../AllActionItems',false,null);
    //alert("response data" +actionRes);
    actionData = Ext.util.JSON.decode(actionRes);
    //alert("pieBrokData.length = "+actionData.length);
    //alert("pieBrkData = "+actionData);
    actionchart.removeAll(true);
    actionchart.loadData(actionData);
 
    actionchart.commitChanges();
    
    
    
    
    
    var gridpop = new Ext.grid.GridPanel({
        store: actionchart,
        columns: [
            {
                id       :'company',
                header   : 'CaseId', 
                width    : 65, 
                sortable : true            },
            {
                header   : 'Description', 
                width    : 150, 
                sortable : true            },
            {
                header   : 'Date', 
                width    : 70, 
                sortable : true            },
            {
                header   : 'Owner', 
                width    : 75, 
                sortable : true            }
        ],
        stripeRows: true,
        height: 230,
        width: 450,
        title: 'Action Items',
        // config options for stateful behavior
        stateful: true,
        stateId: 'grid'
    });
    
    
    gridpop.render('grid-exampleseven');
    
});