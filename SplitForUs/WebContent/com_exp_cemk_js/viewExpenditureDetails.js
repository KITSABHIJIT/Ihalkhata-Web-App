Ext.BLANK_IMAGE_URL = '../com_exp_cemk_css/images/default/s.gif';
Ext.ns('Example');
Example.version = '1.0';

Ext.override(Ext.ux.form.LovCombo, {
	beforeBlur: Ext.emptyFn
});Ext.onReady(function(){
	
	
	
	 Ext.QuickTips.init();
	 Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	 
     userRes = ajaxFunction('POST', '../GetUserServlet', false, null);
     userResData = Ext.util.JSON.decode(userRes);	 
	 var userCategory="";
     for(var i=0;i<userResData.length;i++){
    	 if(userCategory==="")
    		 userCategory="'"+userResData[i].userName+"'";
    	 else
    		 userCategory=userCategory+",'"+userResData[i].userName+"'"; 
     }
     tempUserCategory="["+userCategory+"]";
     finalUserCategory = Ext.util.JSON.decode(tempUserCategory);
      var ExpenditureRes = '[{ }]';
   var ExpenditureData = Ext.util.JSON.decode(ExpenditureRes);
   
   var ExpenditureReader= new Ext.data.JsonReader(
			[					    		      		       
		        {name    : 'userId',mapping : 'userId'},
		        {name    : 'amount',type:'float', mapping : 'amount'}
		       
		        
		        
		        
			]
		);
   
   var ExpensePieRes = '[{ }]';
   var ExpensePieData = Ext.util.JSON.decode(ExpensePieRes);
   var ExpensePieReader= new Ext.data.JsonReader(
			{						   
			},[					    		      		       
		        {name    : 'userId',mapping : 'userId'},
		        {name    : 'amount',type:'float',mapping : 'amount'}		       
		        
			]
		);
		
 
 
   
   
  /* var ExpensePiechart = new Ext.data.Store({  
   fields: ['userId','amount'],
   	data : ExpensePieData,
   	reader: ExpensePieReader
   });*/
   
   
   var ExpensePiechart= new Ext.ux.HighChart({
       titleCollapse: true,
       layout:'fit',
       width:'50%',
       height:400,
       border: true,
       id: 'highPie',
       chartConfig: {
           chart: {
               id: 'highPie',
               plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false
			},
			title: {
				text:'Current Month Expenses'
			},
			
			tooltip: {
				formatter: function() {
					return 'User:<b>'+ this.point.name +'</b><br>Expense:<b>Rs.'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2) +'</b><br>Per:<b>'+ roundNumber(this.percentage,2) +' %</b>';
				}
			},
			legend: {
				layout: 'vertical',
				align: 'right',
				//verticalAlign: 'top',
				//x: -10,
				//y: 50,
				borderWidth: 0
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					/*cursor: 'pointer',
					point: {
					events: {click: function() {
					console.log(this); 
					alert('Name: '+ this.name +', value: '+ this.y);
					}
					}},*/

					dataLabels: {
						enabled: false
					},
					showInLegend: true
				}
			},
	    series: []
	}});
   
	  
   var offeringPieChart= new Ext.ux.HighChart({
       titleCollapse: true,
       layout:'fit',
       width:'49.9%',
       height:400,
       border: true,
       id: 'highOfferingPie',
       chartConfig: {
           chart: {
               id: 'highOfferingPie',
               plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false
			},
			title: {
				text:'Current Month Offerings'
			},
			
			tooltip: {
				formatter: function() {
					return 'User:<b>'+ this.point.name +'</b><br>Expense:<b>Rs.'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2) +'</b><br>Per:<b>'+ roundNumber(this.percentage,2) +' %</b>';
				}
			},
			legend: {
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'bottom',
				//x: -10,
				//y: 50,
				borderWidth: 0
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					/*cursor: 'pointer',
					point: {
					events: {click: function() {
					console.log(this); 
					alert('Name: '+ this.name +', value: '+ this.y);
					}
					}},*/

					dataLabels: {
						enabled: false
					},
					showInLegend: true
				}
			},
	    series: []
	}}); 
	  
	  
   
  
 
   //added for pagination.....
   
    // manually load local data
  
   var result="../GridExpenditureServlet?&month=null&endmonth=null&year=null&endYear=null&type=null&start=0&limit=0";
   var Expenditurechart = new Ext.data.JsonStore({
		root: 'items',
        totalProperty: 'totalCount',
		
        //idProperty: 'threadid',
       // remoteSort: true,
        proxy:new Ext.data.HttpProxy({ url: result, timeout: 300000 }), 
       // url: '../GridExpenditureServlet',
		       fields: [
				 {name    : 'userId',mapping : 'userId'},
		        {name    : 'amount',mapping : 'amount'}
       ]
	  
      
      

   });
   
   
   
   var offeringResult="../GridExpenditureServlet?&month=null&endmonth=null&year=null&endYear=null&type=null&start=0&limit=0";
   var offeringChart = new Ext.data.JsonStore({
		root: 'items',
        totalProperty: 'totalCount',
		
        //idProperty: 'threadid',
       // remoteSort: true,
        proxy:new Ext.data.HttpProxy({ url: result, timeout: 300000 }), 
       // url: '../GridExpenditureServlet',
		       fields: [
				 {name    : 'userId',mapping : 'userId'},
		        {name    : 'amount',mapping : 'amount'}
       ]
	  
      
      

   });


      var columnChart= new Ext.ux.HighChart({
         titleCollapse: true,
         layout:'fit',
         width:'49.9%',
         height:420,
         border: true,
         id: 'highColumn',
         chartConfig: {
             chart: {
                 id: 'highColumn',
                 defaultSeriesType: 'column',
                 margin: [50, 150, 60, 80]
             },
             title: {
					text: 'Current Month Expense of All Users'
				},
			xAxis: {
					categories: finalUserCategory
					
						},
				yAxis: {
					min: 0,
					title: {
						text: 'Expense(Rs)'
					}
				},
				legend: { enabled: false },
				tooltip: {
					formatter: function() {
						return 'Expense: Rs.'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'<br>User: '+this.x;
					}
				},
				plotOptions: {
					/*series: {
			          cursor: 'pointer',
			          point: {events: {click: function() {console.log(this); alert('Category: '+ this.category +', value: '+ this.y + 'Series: ' +  this.series.name);}}}
			         
			       },*/
					column: {
						pointPadding: 0.2,
						borderWidth: 0
					}
				},
			    
			    series: []
			    	
         }
     });
     
      
      var lineChart= new Ext.ux.HighChart({
          titleCollapse: true,
          layout:'fit',
          width:'50%',
          height:420,
          border: true,
          id: 'highLine',
          chartConfig: {
              chart: {
                  id: 'highLine',
                  defaultSeriesType: 'line',
                  margin: [50, 150, 60, 80]
              },
              title: {
 					text: 'Current Month Expense of All Users'
 				},
 				xAxis: {
 					categories: finalUserCategory
 					
 						},
 				yAxis: {
 					min: 0,
 					title: {
 						text: 'Expense(Rs)'
 					}
 				},
 				
 				tooltip: {
 					formatter: function() {
 						return 'Expense: Rs.'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'<br>User: '+this.x;
 					}
 				},
 				plotOptions: {
 					/*series: {
 			          cursor: 'pointer',
 			          point: {events: {click: function() {console.log(this); alert('Category: '+ this.category +', value: '+ this.y + 'Series: ' +  this.series.name);}}}
 			         
 			       },*/
 					column: {
 						pointPadding: 0.2,
 						borderWidth: 0
 					}
 				},
 			    
 			    series: []
 			    	
          }
      });
      
    // create the Grid
    var gridtwo = new Ext.grid.GridPanel({
        store: Expenditurechart,
        height: 215,
        title:'Expense History',
        trackMouseOver:false,
        disableSelection:true,
        loadMask: true,
        width:'100%',
        viewConfig:{forceFit:true,emptyText: 'No Expenses available'},
        columns: [
            {
                id       :'date',
                header   : '<b>Respective Users</b>',
                dataIndex: 'userId',
                sortable : true            },
            {
                header   : '<b>Expenditure Amount (Rs.)</b>', 
                dataIndex: 'amount',
                sortable : true            }
                
        ],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 8,
            store: Expenditurechart,
            //height: 30,
			displayInfo: true,
            //plugins: new Ext.ux.ProgressBarPager(),
            //displayMsg: 'Displaying notifications {0} - {1} of {2}',
            emptyMsg: "Loading..."
        }) 
    });
	    
     
     var offeringGrid = new Ext.grid.GridPanel({
         store: offeringChart,
         title:'Offerings History',
         height: 215,
         trackMouseOver:false,
         disableSelection:true,
         loadMask: true,
         width:'100%',
         viewConfig:{forceFit:true,emptyText: 'No Offerings available'},
         columns: [
             {
                 id       :'date',
                 header   : '<b>Respective Users</b>',
                 dataIndex: 'userId',
                 sortable : true            },
             {
                 header   : '<b>Offering Amount (Rs.)</b>', 
                 dataIndex: 'amount',
	             sortable : true            }
                 
         ],
         
         bbar: new Ext.PagingToolbar({
             pageSize: 8,
             store: offeringChart,
             //height: 30,
 				displayInfo: true,
            // plugins: new Ext.ux.ProgressBarPager(),
             //displayMsg: 'Displaying notifications {0} - {1} of {2}',
             emptyMsg: "Loading..."
         }) 
     });
 	
 	  
     var d=new Date();
	 var year=d.getFullYear();
	 var params="Year="+year;
	 chart1data1 = ajaxFunction('POST', '../GetStackedColumnChart', false, params);
	
	
	 //alert(chart1data1);
	 chart1data = Ext.util.JSON.decode(chart1data1);
	  
	

	 
	// chart1Store.loadData(chart1data); // loading the information in the store  
	 
	 var category="";
	 for(var i=0;i<chart1data.length;i++){
		 if(category==="")
			 category="'"+chart1data[i].Month+"'";
		 else
			 category=category+",'"+chart1data[i].Month+"'";
	 	}
	 var tempCategory="["+category+"]";
	//alert(tempCategory); 
	 finalCategory = Ext.util.JSON.decode(tempCategory); 
var highSeries="";	 
 for(var i=0;i<userResData.length;i++){
	 var userDataArray=new Array();
	 for(var j=0;j<chart1data.length;j++){
		 userDataArray[j]=chart1data[j][userResData[i].userName];
	 }
	 if(highSeries==="")
		 highSeries="{name: '"+userResData[i].userName+"',data: ["+userDataArray.toString()+"]}";
	 else
		 highSeries=highSeries+",{name: '"+userResData[i].userName+"',data: ["+userDataArray.toString()+"]}"; 
}
// alert(highSeries); 
 var tempHighSeries="["+highSeries+"]";
 finalHighSeries = Ext.util.JSON.decode(tempHighSeries); 
	     
	 var chartCfg= new Ext.ux.HighChart({
         titleCollapse: true,
         layout:'fit',
         width:'100%',
         height:420,
         border: true,
         id: 'thechart',
         chartConfig: {
             chart: {
                 id: 'thechart',
                 defaultSeriesType: 'column',
                 margin: [50, 150, 60, 80]
             },
             /*title: {
					text: 'Yearly Expense of All Users'
				},
				subtitle: {
					text: 'of '+new Date().getFullYear()
				},*/
				xAxis: {
					categories: finalCategory
					
						},
				yAxis: {
					min: 0,
					title: {
						text: 'Expense(Rs)'
					}
				},
				
				tooltip: {
					formatter: function() {
						return 'Expense: Rs.'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'<br>User: '+this.series.name+'<br>On '+this.x;
					}
				},
				plotOptions: {
					/*series: {
			          cursor: 'pointer',
			          point: {events: {click: function() {console.log(this); alert('Category: '+ this.category +', value: '+ this.y + 'Series: ' +  this.series.name);}}}
			         
			       },*/
					column: {
						pointPadding: 0.2,
						borderWidth: 0
					}
				},
			    
			    series: finalHighSeries
			    	
         }
     });
 
     
     
     
     
     
     
     //gridtwo.render('viewData123');	 
		 var viewport2 = new Ext.form.FormPanel({
			 layout:'form',
			 id:'Myform',
			 width:'100%',
			 height:460,
			 //renderTo: Ext.getBody(),
			 url:'',

			         items:[{
							 xtye:'panel',
							 width:'100%',
							 standardSubmit:true,
							 border:false,
							  region:'center',
							  margins:'135 0 0 0',
							 items:[
new Ext.TabPanel({
	id : 'content-panel',
	 width:'100%',
	 autoHight:true,
    // border:false,
     title:'BCC Report',
     fieldlabel:'BCC Report',
   //  defaults:{autoScroll:true}, 
     id:'Tabs',
     activeTab: 0,
     style:'padding:0 0 0 0',
     items:[

            
            {
    	 	title: 'Expenditure & Offering of All Users ',
    	 	layout:'form',
            autoHeight:true,
            width:'100%',
           // border:false,
            style:'padding:0px 0px 0px 0px',
            items:[{
           		xtype:'panel',
           		border:false,
           		autoHight:true,
           		width:'100%',
           		layout:'column',
           		items:[{
           		xtype:'panel',
           		border:false,
           		autoHight:true,
           		width:'70%',
           		layout:'column',
           		items:[{
   		xtype:'panel',
   		border:false,
   		autoHight:true,
   		width:'100%',
   		style:'padding:10px 0px 0px 0px',
   		layout:'column',
   		items:[ 
			{
				columnWidth: '.15',
				xtype:'displayfield',
				style:'font-size:14px',
				style:'padding:0px 0px 0px 30px',
				value:'Select a Period:'
					},
					new Ext.form.ComboBox({
						id:'dateRange',
						name:'dimension',
						hideTrigger:false,
						store: new Ext.data.ArrayStore({
							fields: ['display','value'],
							data : [
							[ 'Current Month','0'],
							[ 'Last Month','1'],
							['Last Quarter','2'],
							[ 'Last Year','3']
							] 
							}),
							displayField:'display',
							valueField:'value',
						typeAhead: true,
						mode: 'local',
						editable: false,
						columnWidth: '.15',
						triggerAction: 'all',
						selectOnFocus:true,
						listeners:{
				'select': function(key){
					 var dateRange=key.value;
					 var startMonth=getStartMonth(dateRange);
					 var endMonth=getEndMonth(dateRange);
					 var finalYear=getStartYear(dateRange);
					 var endFinalYear=getEndYear(dateRange);
					 
					 var result="../GridExpenditureServlet?&month="+startMonth+"&endmonth="+endMonth+"&year="+finalYear+"&endYear="+endFinalYear+"&type=1";
					 Expenditurechart.proxy.setUrl(result,true);
					 Expenditurechart.reload();
					 
					 var offeringResult="../GridExpenditureServlet?&month="+startMonth+"&endmonth="+endMonth+"&year="+finalYear+"&endYear="+endFinalYear+"&type=2";
					 offeringChart.proxy.setUrl(offeringResult,true);
					 offeringChart.reload();
					 
					 
					  var parameter="month="+startMonth+"&endmonth="+endMonth+"&year="+finalYear+"&endYear="+endFinalYear+"&type=1";
					  ExpensePieRes = ajaxFunction('POST','../GridPieServlet',false,parameter);
					  ExpensePieData = Ext.util.JSON.decode(ExpensePieRes);
					  
					  var offeringParameter="month="+startMonth+"&endmonth="+endMonth+"&year="+finalYear+"&endYear="+endFinalYear+"&type=2";
					  offeringPieRes = ajaxFunction('POST','../GridPieServlet',false,offeringParameter);
					  offeringPieData = Ext.util.JSON.decode(offeringPieRes);
					  
					  
					  var userCategoryData="";
					  
					  for(var i=0;i<ExpensePieData.length;i++){
							if(userCategoryData==="")
								 userCategoryData=ExpensePieData[i].amount;
							else
								userCategoryData=userCategoryData+","+ExpensePieData[i].amount;
								
						 } 
						userCategoryData= "[{ name: 'Expense',data: ["+userCategoryData+"]}]";
						userCategoryData = Ext.util.JSON.decode(userCategoryData);
						Ext.getCmp('highLine').chartConfig.series=userCategoryData;
						Ext.getCmp('highColumn').chartConfig.series=userCategoryData;
						
						var userPieData="";
						for(var i=0;i<ExpensePieData.length;i++){
							if(userPieData==="")
								userPieData="['"+ExpensePieData[i].userId+"',"+ExpensePieData[i].amount+"]";
							else
								userPieData=userPieData+",['"+ExpensePieData[i].userId+"',"+ExpensePieData[i].amount+"]";
								
						 } 
						userPieData= "[{type: 'pie',name: 'Expense',data:["+userPieData+"]}]";
						userPieData = Ext.util.JSON.decode(userPieData);
						Ext.getCmp('highPie').chartConfig.series=userPieData;
						
						
						var userOfferingPieData="";
						for(var i=0;i<offeringPieData.length;i++){
							if(userOfferingPieData==="")
								userOfferingPieData="['"+offeringPieData[i].userId+"',"+offeringPieData[i].amount+"]";
							else
								userOfferingPieData=userOfferingPieData+",['"+offeringPieData[i].userId+"',"+offeringPieData[i].amount+"]";
								
						 } 
						userOfferingPieData= "[{type: 'pie',name: 'Offering',data:["+userOfferingPieData+"]}]";
						userOfferingPieData = Ext.util.JSON.decode(userOfferingPieData);
						Ext.getCmp('highOfferingPie').chartConfig.series=userOfferingPieData;
						
						
						if(isEmpty(ExpensePieRes) || ExpensePieRes=='[]')
							Ext.getCmp('highLine').setTitle('<br/><br/><br/>Sorry<br/>No Data Available<br/> for '+Ext.getCmp('dateRange').getRawValue()+' of all users Expense');
						else
							Ext.getCmp('highLine').setTitle(Ext.getCmp('dateRange').getRawValue()+' Expenses of all users');
						
						if(isEmpty(ExpensePieRes) || ExpensePieRes=='[]')
							Ext.getCmp('highColumn').setTitle('<br/><br/><br/>Sorry<br/>No Data Available<br/> for '+Ext.getCmp('dateRange').getRawValue()+' of all users Expense');
						else
							Ext.getCmp('highColumn').setTitle(Ext.getCmp('dateRange').getRawValue()+' Expenses of all users');

						if(isEmpty(ExpensePieRes) || ExpensePieRes=='[]')
							Ext.getCmp('highPie').setTitle('<br/><br/><br/>Sorry<br/>No Expense Data Available<br/> for '+Ext.getCmp('dateRange').getRawValue()+' of all users');
						else
							Ext.getCmp('highPie').setTitle(Ext.getCmp('dateRange').getRawValue()+' Expenses of all users');

						if(isEmpty(offeringPieRes) || offeringPieRes=='[]')
							Ext.getCmp('highOfferingPie').setTitle('<br/><br/><br/>Sorry<br/>No Offering Data Available<br/> for '+Ext.getCmp('dateRange').getRawValue()+' of all users');
						else
							Ext.getCmp('highOfferingPie').setTitle(Ext.getCmp('dateRange').getRawValue()+' Offerings of all users');

	
				}}
						
						})
   		        
   		        ]},   
			{
				xtype:'panel',
				border:false,
				autoHeight:true,
				width:'100%',
				layout:'column',
				items:[       
				       ExpensePiechart,offeringPieChart
               		]}]},{
        				xtype:'panel',
        				border:false,
        				layout:'auto',
        				autoHeight:true,
        				width:'30%',
        				items:[gridtwo,offeringGrid]}]}
                             ]},
                     {
                 	 	title: 'Expenditure Graphs of All Users ',
                         layout:'form',
                         autoHeight:true,
                         width:'100%',
                        // border:false,
                         style:'padding:0px 0px 10px 0px',
                         items:[  
             {
             	xtype:'panel',
             	border:false,
             	autoHeight:true,
             	width:'100%',
             	layout:'column',
             	items:[ columnChart, lineChart]}
							       
							  ]},
							  {

			                 	 	title: 'Yearly Expenditure Graphs of All Users Month wise',
			                 	 	layout:'form',
			                 	 	autoHeight:true,
			                      	width:'100%',
			                        // border:false,
			                         style:'padding:0px 0px 10px 0px',
			                         items:[ chartCfg]
								  
								  
							  }
							  
							  
							  
							  
							  ]})      
							        
							        
							        ]}]});
		 Ext.getCmp('dateRange').setValue('0');
		 var dateRange=Ext.getCmp('dateRange').getValue();
		 var startMonth=getStartMonth(dateRange);
		 var endMonth=getEndMonth(dateRange);
		 var finalYear=getStartYear(dateRange);
		 var endFinalYear=getEndYear(dateRange);
		 var result="../GridExpenditureServlet?&month="+startMonth+"&endmonth="+endMonth+"&year="+finalYear+"&endYear="+endFinalYear+"&type=1";
		 Expenditurechart.proxy.setUrl(result,true);
		 Expenditurechart.load.defer(20,Expenditurechart,[{params:{start:0, limit:8}}]);
		 
		 var offeringResult="../GridExpenditureServlet?&month="+startMonth+"&endmonth="+endMonth+"&year="+finalYear+"&endYear="+endFinalYear+"&type=2";
		 offeringChart.proxy.setUrl(offeringResult,true);
		 offeringChart.load.defer(20,offeringChart,[{params:{start:0, limit:8}}]);
		 
		 
		  var parameter="month="+startMonth+"&endmonth="+endMonth+"&year="+finalYear+"&endYear="+endFinalYear+"&type=1";
		  ExpensePieRes = ajaxFunction('POST','../GridPieServlet',false,parameter);
		  ExpensePieData = Ext.util.JSON.decode(ExpensePieRes);
		  
		  var offeringParameter="month="+startMonth+"&endmonth="+endMonth+"&year="+finalYear+"&endYear="+endFinalYear+"&type=2";
		  offeringPieRes = ajaxFunction('POST','../GridPieServlet',false,offeringParameter);
		  offeringPieData = Ext.util.JSON.decode(offeringPieRes);
		  
		  
		  var userCategoryData="";
		  
		  for(var i=0;i<ExpensePieData.length;i++){
				if(userCategoryData==="")
					 userCategoryData=ExpensePieData[i].amount;
				else
					userCategoryData=userCategoryData+","+ExpensePieData[i].amount;
					
			 } 
			userCategoryData= "[{ name: 'Expense',data: ["+userCategoryData+"]}]";
			userCategoryData = Ext.util.JSON.decode(userCategoryData);
			Ext.getCmp('highLine').chartConfig.series=userCategoryData;
			Ext.getCmp('highColumn').chartConfig.series=userCategoryData;
			
			var userPieData="";
			for(var i=0;i<ExpensePieData.length;i++){
				if(userPieData==="")
					userPieData="['"+ExpensePieData[i].userId+"',"+ExpensePieData[i].amount+"]";
				else
					userPieData=userPieData+",['"+ExpensePieData[i].userId+"',"+ExpensePieData[i].amount+"]";
					
			 } 
			userPieData= "[{type: 'pie',name: 'Expense',data:["+userPieData+"]}]";
			userPieData = Ext.util.JSON.decode(userPieData);
			Ext.getCmp('highPie').chartConfig.series=userPieData;
			
			
			var userOfferingPieData="";
			for(var i=0;i<offeringPieData.length;i++){
				if(userOfferingPieData==="")
					userOfferingPieData="['"+offeringPieData[i].userId+"',"+offeringPieData[i].amount+"]";
				else
					userOfferingPieData=userOfferingPieData+",['"+offeringPieData[i].userId+"',"+offeringPieData[i].amount+"]";
					
			 } 
			userOfferingPieData= "[{type: 'pie',name: 'Expense',data:["+userOfferingPieData+"]}]";
			userOfferingPieData = Ext.util.JSON.decode(userOfferingPieData);
			Ext.getCmp('highOfferingPie').chartConfig.series=userOfferingPieData;
			
			if(isEmpty(ExpensePieRes) || ExpensePieRes=='[]')
				Ext.getCmp('highLine').setTitle('<br/><br/><br/>Sorry<br/>No Data Available<br/> for Current Month of all users Expense');
			else
				Ext.getCmp('highLine').setTitle('Current Month Expenses of all users');
			
			if(isEmpty(ExpensePieRes) || ExpensePieRes=='[]')
				Ext.getCmp('highColumn').setTitle('<br/><br/><br/>Sorry<br/>No Data Available<br/> for Current Month of all users Expense');
			else
				Ext.getCmp('highColumn').setTitle('Current Month Expenses of all users');

			if(isEmpty(ExpensePieRes) || ExpensePieRes=='[]')
				Ext.getCmp('highPie').setTitle('<br/><br/><br/>Sorry<br/>No Expense Data Available<br/> for Current Month of all users');
			else
				Ext.getCmp('highPie').setTitle('Current Month Expenses of all users');

			if(isEmpty(offeringPieRes) || offeringPieRes=='[]')
				Ext.getCmp('highOfferingPie').setTitle('<br/><br/><br/>Sorry<br/>No Offering Data Available<br/> for Current Month of all users');
			else
				Ext.getCmp('highOfferingPie').setTitle('Current Month Offerings of all users');
			
			if(isEmpty(chart1data1) || chart1data1=='[]')
				Ext.getCmp('thechart').setTitle('<br/><br/><br/>Sorry<br/>No Expense Data Available<br/> for '+new Date().getFullYear()+' All Users');
			else
				Ext.getCmp('thechart').setTitle('Yearly Expense of All Users<br/>of '+new Date().getFullYear());
	
			viewport2.render('viewExpenditurecontent');
  });
	