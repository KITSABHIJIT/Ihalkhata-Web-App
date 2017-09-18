
//Ext.chart.Chart.CHART_URL = '../com_exp_cemk_css/images/charts/charts.swf';


  Ext.onReady(function(){
	 Ext.QuickTips.init();
      var ExpensePieRes = '[{ }]';
   var ExpensePieData = Ext.util.JSON.decode(ExpensePieRes);
   var ExpensePieReader= new Ext.data.JsonReader(
			{						   
			},[					    		      		       
		        {name    : 'display',mapping : 'display'},
		        {name    : 'value',mapping : 'value'}		       
		        
			]
		);
		
 
 
   
   
   var ExpensePiechart = new Ext.data.Store({  
   fields: ['display','value'],
   	data : ExpensePieData,
   	reader: ExpensePieReader
   });
   
   
   
  
   ExpensePieRes = ajaxFunction('POST','../GridPieServlet',false,null);
  // alert("response data" +ExpensePieRes);
   ExpensePieData = Ext.util.JSON.decode(ExpensePieRes);
  // alert("pieBrokData.length = "+ExpensePieData.length);
  // alert("pieBrkData = "+ExpensePieData);
   ExpensePiechart.removeAll(true);
   ExpensePiechart.loadData(ExpensePieData);

   ExpensePiechart.commitChanges(); 

    // create the Grid
  
    
	 new Ext.Panel({ 
        width: 400, 
         height: 400, 
         title: 'Pie Chart with Legend - Favorite Season', 
         renderTo: 'container', 
         items:[
         {
       	  xtype: 'piechart',
          store: ExpensePiechart,   
       		dataField: 'value', //information to display in the chart   
      		 categoryField : 'display',
      		  extraStyle: {  

             legend:{  

                 display: 'right',  

                padding: 10,  

                 border:{  

                    color: '#CBCBCB',  

                    size: 1  

                 }  

             }  
             }
         }
         
         
         ]
     }); 
	
							 		
		
  });




