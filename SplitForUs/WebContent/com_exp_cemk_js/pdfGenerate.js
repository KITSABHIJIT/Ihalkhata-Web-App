
Ext.BLANK_IMAGE_URL = '../com_exp_cemk_css/images/default/s.gif';
Ext.ns('Example');
Example.version = '1.0';

Ext.override(Ext.ux.form.LovCombo, {
	beforeBlur: Ext.emptyFn
});
var userRes = '[{ }]';
var userResData = Ext.util.JSON.decode(userRes);
var userResReader= new Ext.data.JsonReader(
			{						   
			},[					    		      		       
				{name    : 'userName',mapping : 'userName'},
				{name    : 'userId',mapping : 'userId'}    

			]
		);


var userStore = new Ext.data.Store({  
	fields: ['userName','userId'],
	data : userResData,
	reader: userResReader
});
function validate(){
	var monthCheck=Ext.getCmp('monthCheck').getValue();
	var dateRangeCheck=Ext.getCmp('dateRangeCheck').getValue();
	var profile=Ext.getCmp('pdfCustom').getValue().inputValue;
	var limitCheck=Ext.getCmp('limit').getValue();
	if(profile=="2"){
		if ((limitCheck=='' || limitCheck==null || limitCheck==undefined))
		{
			Ext.Msg.show({
				 title:'Sorry!',
				msg: 'You have to Enter a Highlighting limit.',
			   buttons: Ext.Msg.OK,
			   animEl: 'elId',
			   icon: Ext.MessageBox.ERROR
			}); 
			return false;
		}
		else{
			if (isNaN(limitCheck) || parseFloat(limitCheck)<=0 || (parseFloat(limitCheck)*1!=parseFloat(limitCheck)))
			{
				Ext.Msg.show({
					 title:'Sorry!',
					msg: 'Enter a valid Highlighting limit.',
				   buttons: Ext.Msg.OK,
				   animEl: 'elId',
				   icon: Ext.MessageBox.ERROR
				}); 
				return false;
			}
			
		}
	}
	
	if ((monthCheck=='' || monthCheck==null || monthCheck==undefined) && (dateRangeCheck=='' || dateRangeCheck==null || dateRangeCheck==undefined))
	{
		Ext.Msg.show({
			 title:'Sorry!',
			msg: 'You have to select a time Range.',
		   buttons: Ext.Msg.OK,
		   animEl: 'elId',
		   icon: Ext.MessageBox.ERROR
		}); 
		return false;
	}
	return true;
	}
function confirmCheck(){
	var monthNames = [ "January", "February", "March", "April", "May", "June",
	                    "July", "August", "September", "October", "November", "December" ];
	var dateRange=Ext.getCmp('dateRange').value;
	var month1=Ext.getCmp('month').value;
	var year1=Ext.getCmp('year').value;
	var user=Ext.getCmp('user').value;
	var monthCheck=Ext.getCmp('monthCheck').getValue();
	var dateRangeCheck=Ext.getCmp('dateRangeCheck').getValue();
	var chartsFlag="";
	var HighlightingLimit="";
	var titleFlag="";
	
	var profile=Ext.getCmp('pdfCustom').getValue().inputValue;
	if(profile=="1"){
		chartsFlag="Y";
		HighlightingLimit="100";
		titleFlag="Y";
	}
	else{
		if(Ext.getCmp('pdf').getValue())
			chartsFlag="Y";
		else 
			chartsFlag="N";
		
		if(Ext.getCmp('title').getValue())
			titleFlag="Y";
		else 
			titleFlag="N";
		
		HighlightingLimit=Ext.getCmp('limit').getValue();
		
	}
		
		var d=new Date();
		var day=d.getDate();
	    var month=d.getMonth()+1;
	    var year=d.getFullYear();
	    var date=day+'/'+(month+1)+'/'+year;
	 	var startMonth='';
	    var endMonth='';
	    var finalYear='';
	    var endFinalYear='';
	    
	if(monthCheck==true){
		startMonth=month1;
		endMonth=month1;
		finalYear=year1;
		endFinalYear=year1;
	}
	if(dateRangeCheck==true){
		
		 if(dateRange=='0'){
			 startMonth=month; 
			 endMonth=month;
			 finalYear=year;
			 endFinalYear=year;
		 }
		 else if(dateRange=='1'){
			 if(month===1){
				 startMonth=12; 
				 endMonth=12; 
				 finalYear=year-1; 
				 endFinalYear=year-1;
			 }
			 else{
				 startMonth=month-1; 
				 endMonth=month-1; 
				 finalYear=year; 
				 endFinalYear=year;
				
			 }
		 }
		else if(dateRange=='2'){
			if(month===1){
				 startMonth=11; 
				 endMonth=month; 
				 finalYear=year-1; 
				 endFinalYear=year;
				 }
			else if(month===2){
				startMonth=12; 
				endMonth=month; 
				finalYear=year-1; 
				endFinalYear=year;
			}
			else{
				startMonth=month-2; 
				endMonth=month; 
				finalYear=year; 
				endFinalYear=year;
			}		
		}
		else if(dateRange=='3'){
			 if(month===1){
				 startMonth=month; 
				 endMonth=12; 
				 finalYear=year-1; 
				 endFinalYear=year-1;
			 }
			 else{
				 startMonth=month; 
				 endMonth=month-1; 
				 finalYear=year-1; 
				 endFinalYear=year;
				
			 }		
		}
	  	
	}
	document.getElementById('userId').value=user;
	document.getElementById('start_Month').value=startMonth;
	document.getElementById("end_Month").value=endMonth;
	document.getElementById('Final_Year').value=finalYear;
	document.getElementById('END_Year').value=endFinalYear;
	//var url='../GeneratePDFServlet?userId='+user+'&userName='+Ext.getCmp('user').getRawValue()+'&startMonth='+startMonth+'&endMonth='+endMonth+'&finalYear='+finalYear+'&endYear='+endFinalYear+'&title='+titleFlag+'&charts='+chartsFlag+'&limit='+HighlightingLimit;
	var monthRange='';
	var yearRange='';
	if(startMonth==endMonth)
		monthRange=	monthNames[startMonth-1];
	else
		monthRange=	monthNames[startMonth-1]+' to '+monthNames[endMonth-1];
    	 
	
	if(finalYear==endFinalYear)
		yearRange=	finalYear;
	else
		yearRange=	finalYear+' to '+endFinalYear;
	
	
	
	var tabs = new Ext.Panel({
		 border:false,
			//height:380,
			autoHeight:true,
			width:'100%',
			style:'padding:10px 0px 10px 0px',
			layout:'column',
			items:[new Ext.ux.Image ({ 
											id: 'imgPreview',
											autoHeight:true,
											width:'40%',
											url: '../com_exp_cemk_css/images/PDF-logoBIG.gif' 
										 }),{
											xtype:'panel',
											border:false,
											autoHeight:true,
											width:'60%',
											items:[{
						   			   			xtype:'panel',
						   			   			border:false,
						   			   			autoHeight:true,
						   			   			width:'100%',
						   			   			style:'padding:0px 0px 10px 0px',
						   			   			layout:'column',
						   			   			items:[ {
						   			   					width:'100%',
						   			   					xtype:'displayfield',
						   			   					style:'font-size:14px',
						   			   					value:'<b>Are you sure to generate PDF for:- </b>'
						   			   			   		}	   			   			   		
						   			   			   		]},{
						   			   			   			xtype:'panel',
						   			   			   			border:false,
						   			   			   			autoHeight:true,
						   			   			   			width:'100%',
						   			   			   			style:'padding:0px 0px 10px 0px',
						   			   			   			layout:'column',
						   			   			   			items:[ 
						   			   			   			   		{
						   			   			   			   			width:'100%',
						   			   			   						xtype:'displayfield',
						   			   			   						style:'font-size:14px',
						   			   			   						value:'DateRange of:- <b>'+monthRange+'</b>'	  
						   			   			   				   		}]},
							   			   			   				{
											   			   			   			xtype:'panel',
											   			   			   			border:false,
											   			   			   			style:'padding:0px 0px 10px 0px',
											   			   			   			autoHeight:true,
											   			   			   			width:'100%',
											   			   			   			layout:'column',
											   			   			   			items:[ 
											   			   			   			   		{
											   			   			   			   			width:'40%',
											   			   			   						xtype:'displayfield',
											   			   			   						style:'font-size:14px',
											   			   			   						value:'Year:- <b>'+yearRange+'</b>'	
											   			   			   				   		}]}]

				} 
				
				],buttons:[{
					xtype:'button',
					text:'Generate PDF',
					columnWidth: .05,
					handler: function()
			        {
					//window.location = url;
					//window.open(url, '_blank');
						formSubmit();
					win.close();
	    	             }
				},{
					xtype:'button',
					text:'CANCEL',
					columnWidth: .05,
					 handler: function(){
					win.close();
								}
				}]
	});
	    var win = new Ext.Window({
	    	autoHeight:true,
           modal: true,
           title:'Confirm the Selection',
           frame:true,	
	        resizable:false,
	        closable:true,	
	         closable:true,
	         width:'30%',
	        items: new Ext.Panel(
	     	        {	
	     	           items: tabs
	     	        })
	     });
	     win.show(this);
	

}
function formSubmit(){
	Ext.getCmp('generatePdfForm').getForm().submit({reset:false,
  	  waitMsg:'Generating PDF...',
  	  timeout: 40000,
  	  params: {
  	  userId: document.getElementById('userId').value,
  	  startMonth : document.getElementById('start_Month').value,
  	  endMonth : document.getElementById("end_Month").value,
  	  finalYear:document.getElementById('Final_Year').value,
  	  endYear:document.getElementById('END_Year').value
		  },
  	  success: function(form, action)

        {

  		extWarningAlert(action.result.res);
  	 

        },

        failure: function(form,action)

        {
      	 
        	if(!isEmpty(action.result.error))
        		 extERRORAlert( action.result.error);
      	

        }
        });
}

var yearCombo=new Date().getFullYear();
var yearData="";
for(var i=0;i<5;i++){
	if(yearData=="")
		{yearData='{"key":"'+yearCombo+'","value":"'+yearCombo+'"}';}
	else
		{yearData=yearData+',{"key":"'+yearCombo+'","value":"'+yearCombo+'"}';}
	yearCombo--;
}
var finalYearData1='['+yearData+']';
//alert(finalYearData1);
var finalYearData = Ext.util.JSON.decode(finalYearData1);


var chart1Reader=	new Ext.data.JsonReader(
			{						   
				              
			},[		
			    {name    : 'key',mapping : 'key'},
			    {name    : 'value',mapping : 'value'}
			   
			]
		); 
var store1=	new Ext.data.Store({
	       
   //fields: ['month','New','Processed','Pending'],
   fields: ['key','value'],
   data: finalYearData,
	reader:chart1Reader,
	pruneModifiedRecords : true
  
});   

store1.loadData(finalYearData);

Ext.onReady(function(){
	//alert("you are inside Ext.onReady(function()");
	 Ext.QuickTips.init();
	 var viewport = new Ext.form.FormPanel({
		 layout:'form',
		 width : '100%',
		 height : 460,
		 id:'generatePdfForm',
		 url:'../GeneratePDFServlet',

		         items:[{
						 xtye:'panel',
						 width : '100%',
						 autoHeight : true,
						 standardSubmit:true,
						 border:false,
						  region:'center',
						  margins:'135 0 0 0',
						 items:[
						        
						     new Ext.TabPanel({
						        	id : 'content-panel',
						        	width : '100%',
									 autoHeight : true,
						             border:false,
						             title:'BCC Report',
						             fieldlabel:'BCC Report',
						            // defaults:{autoScroll:true}, 
						             id:'Tabs',
						             activeTab: 0,
						             style:'padding:0 0 0 0',
							         items:[
						
							                
							                {
							        	 	title: 'Generate PDF',
							                layout:'form',
							                width : '100%',
											autoHeight : true,
							                border:false,
							                style:'padding:10px 10px 10px 10px',
							                items:[ 
							{
								xtype:'panel',
								border:false,
								width : '100%',
								autoHeight : true,
								layout:'column',
								items:[new Ext.ux.Image ({ 
											id: 'imgPreview',
											width : '40%',
											height : 380,
											url: '../com_exp_cemk_css/images/pdf_logo.png' 
										 }),{
										xtype:'panel',
										border:false,
										width : '60%',
										height : 380,
										items:[  
	                
										       {
													
					                				xtype:'panel',
					                				border:false,
					                				width : '100%',
					                				hidden:true,
					                				style:'padding:20px 0px 40px 10px',
													autoHeight : true,
													layout:'column',
					                				items:[{
																	columnWidth: '.2',
																	xtype:'displayfield',
																	style:'font-size:14px',
																	value:'Select an user:'
					                				       		},
																new Ext.form.ComboBox({
																id:'user',
																name:'dimension',
																hideTrigger:false,
																editable:false,
																store:userStore,
																resizable:true,
																valueField:'userId',
																displayField:'userName',
																typeAhead: true,
																mode: 'local',
																columnWidth: '.3',
																triggerAction: 'all',
																selectOnFocus:true
																
																
																})
																
																
																
																
																
																]
													
												
												},{
																	
									                				xtype:'panel',
									                				border:false,
									                				width : '100%',
																	autoHeight : true,
																	style:'padding:0px 0px 10px 10px',
																	layout:'column',
									                				items:[{
																		xtype:'checkbox',        
																		 id:'dateRangeCheck',
																		 columnWidth: '.05',
																		 listeners:{
																        	 'check': function()
																        	 {
								                					if(Ext.getCmp('dateRangeCheck').getValue()==true){
								                					Ext.getCmp('monthCheck').disable();
								                					Ext.getCmp('month').disable();
								                					Ext.getCmp('year').disable();
								                					Ext.getCmp('monthDisplay').disable();
								                					Ext.getCmp('yearDisplay').disable();
								                					Ext.getCmp('month').setValue('');
								                					Ext.getCmp('year').setValue('');
								                					}else{
								                						Ext.getCmp('monthCheck').enable();
									                					Ext.getCmp('month').enable();
									                					Ext.getCmp('year').enable();
									                					Ext.getCmp('monthDisplay').enable();
									                					Ext.getCmp('yearDisplay').enable();
									                					Ext.getCmp('month').setValue('1');
									                					Ext.getCmp('year').setValue(new Date().getFullYear());
									                					
								                					}
																        	 }
								                					}
																	}, {
																					columnWidth: '.15',
																					xtype:'displayfield',
																					style:'font-size:14px',
																					value:'Select a Period:',
																					id:'periodDisplay'
									                				       		},
									                				       		new Ext.form.ComboBox({
									                				    			id:'dateRange',
									                				    			columnWidth: '.3'
									                				    			,hideOnSelect:false
									                				    			,maxHeight:200,
									                				    			hideTrigger:false,
									                				    			editable:false,
									                				    			store: new Ext.data.ArrayStore({
									                				    				fields: ['key', 'value'],
									                				    				data : [
									                				    						['0','Current Month'],
									                				    						['1','Last Month'],
									                				    						['2','Last Quarter'],
									                				    						['3','Last Year']
									                				    						]
									                				    			}),
									                				    			valueField:'key',
									                				    			displayField:'value',
									                				    			typeAhead: true,
									                				    			mode: 'local',
									                				    			triggerAction: 'all',
									                				    			hidden:false,
									                				    			selectOnFocus:true
									                				    		})
																				
																				
																				
																				
																				
																				]
																	
																
																},
																{

																	
									                				xtype:'panel',
									                				border:false,
									                				width : '100%',
									                				style:'padding:0px 0px 10px 10px',
																	autoHeight : true,
																	layout:'column',
									                				items:[{
																		xtype:'checkbox',        
																		 id:'monthCheck',
																		 columnWidth: '.05',
																		 listeners:{
																        	 'check': function()
																        	 {
								                					if(Ext.getCmp('monthCheck').getValue()==true){
								                					Ext.getCmp('dateRangeCheck').disable();
								                					Ext.getCmp('dateRange').disable();
								                					Ext.getCmp('periodDisplay').disable();
								                					
								                					
								                					Ext.getCmp('dateRange').setValue('');
								                					}else{
								                						Ext.getCmp('dateRangeCheck').enable();
									                					Ext.getCmp('dateRange').enable();
									                					Ext.getCmp('periodDisplay').enable();
									                					Ext.getCmp('dateRange').setValue('0');
									                					
								                					}
																        	 }
								                					}
																	},{
																					columnWidth: '.15',
																					xtype:'displayfield',
																					style:'font-size:14px',
																					value:'Select a Month:',
																					id:'monthDisplay'
									                				       		},
									                				       		new Ext.form.ComboBox({
									                				    			id:'month',
									                				    			columnWidth: '.3'
									                				    			,hideOnSelect:false
									                				    			,maxHeight:200,
									                				    			hideTrigger:false,
									                				    			editable:false,
									                				    			store: new Ext.data.ArrayStore({
									                				    				fields: ['key', 'value'],
									                				    				data : [
									                				    						['1','January'],
									                				    						['2','February'],
									                				    						['3','March'],
									                				    						['4','April'],
									                				    						['5','May'],
									                				    						['6','June'],
									                				    						['7','July'],
									                				    						['8','August'],
									                				    						['9','September'],
									                				    						['10','October'],
									                				    						['11','November'],
									                				    						['12','December']
									                				    						]
									                				    			}),
									                				    			valueField:'key',
									                				    			displayField:'value',
									                				    			typeAhead: true,
									                				    			mode: 'local',
									                				    			triggerAction: 'all',
									                				    			hidden:false,
									                				    			selectOnFocus:true
									                				    		})]},
									                				    		{

																					
													                				xtype:'panel',
													                				border:false,
													                				width : '100%',
													                				style:'padding:0px 0px 40px 45px',
																					autoHeight : true,
																					layout:'column',
													                				items:[
									                				    		{
																					columnWidth: '.15',
																					xtype:'displayfield',
																					style:'font-size:14px',
																					id:'yearDisplay',
																					value:'Year:'
									                				       		},
									                				       		new Ext.form.ComboBox({
									                				    			id:'year',
									                				    			columnWidth: '.2'
									                				    			,hideOnSelect:false
									                				    			,maxHeight:200,
									                				    			hideTrigger:false,
									                				    			editable:false,
									                				    			store: store1,
									                				    			valueField:'key',
									                				    			displayField:'value',
									                				    			typeAhead: true,
									                				    			mode: 'local',
									                				    			triggerAction: 'all',
									                				    			hidden:false,
									                				    			selectOnFocus:true
									                				    		})]}
									                				    		
									                				    		,{xtype:'panel',
									                				border:false,
									                				width : '100%',
																	autoHeight : true,
																	hidden:true,
																	style:'padding:0px 0px 10px 10px',
																	layout:'column',
									                				items:[{

																  		  xtype: 'radiogroup',
																  		  id:'pdfCustom',
																  		// disabled:true,
																  		  layout:'auto',
																  		  border:false,
																  		  //columns: 1,
																  		  //vertical: true,
																  		  columnWidth: '.6',
																  		  items: [
																  		  {boxLabel: 'Default PDF Generation', name: 'pdfCustomRadio', inputValue: 1, checked: true},
																  		  {boxLabel: 'Customised PDF Generation', name: 'pdfCustomRadio', inputValue: 2}],
																  		  listeners:{
																  			'change': function(key)
																  			{
																  			if(Ext.getCmp('pdfCustom').getValue().inputValue=='1'){
																  				Ext.getCmp('customPanel').setVisible(false);
																  				Ext.getCmp('pdf').setValue(false);
																  				Ext.getCmp('title').setValue(false);
																  				Ext.getCmp('limit').setValue('');
																  			}
																  			else if (Ext.getCmp('pdfCustom').getValue().inputValue=='2'){
																  				Ext.getCmp('customPanel').setVisible(true);
																  			}
																  			}}
																  		
																  		  


																  		  
																        }]
																	
																
																
																
																},{
																	xtype:'panel',
									                				border:false,
									                				width : '100%',
																	autoHeight : true,
																	id:'customPanel',
																	style:'padding:0px 0px 10px 10px',
									                				hidden:true,
									                				layout:'auto',
									                				items:[
																		  	{
																			xtype:'panel',
																			border:false,
																			width : '100%',
																			style:'padding:0px 0px 10px 10px',
																			autoHeight : true,
																			layout:'column',
																			items:[       
										                				       {
										                				    	   xtype:'checkbox', 
										                				    	   columnWidth: '.05',
										                				    	   id:'title'
										                				       },{
																						columnWidth: '.25',
																						xtype:'displayfield',
																						style:'font-size:14px',
																						value:'Add Title Page Report'
										                				       }]
										                				  	},{
																			xtype:'panel',
																			border:false,
																			width : '100%',
																			style:'padding:0px 0px 10px 10px',
																			autoHeight : true,
																			layout:'column',
																			items:[       
										                				       {
										                				    	   xtype:'checkbox', 
										                				    	   columnWidth: '.05',
										                				    	   id:'pdf'
										                				       },{
																						columnWidth: '.25',
																						xtype:'displayfield',
																						style:'font-size:14px',
																						value:'Add Charts to Report'
										                				       }]
										                				  	},
																			{xtype:'panel',
																			border:false,
																			width : '100%',
																			style:'padding:0px 0px 10px 10px',
																			autoHeight : true,
																			layout:'column',
																			items:[       
										                				       {
																						columnWidth: '.25',
																						xtype:'displayfield',
																						style:'font-size:14px',
																						value:'Highlighting Limit'
										                				       },{
																					columnWidth: '.05',
																					xtype:'displayfield',
																					style:'font-size:14px',
																					value:'Rs.'
									                				       },
										                				       {
																					xtype: 'textfield',
																					id: 'limit',
																					columnWidth: '.12',
																					value: '',
																					maskRe :/^[0-9]{0,}$/
																				}]}
										                				  	
																				
																				
																				]
																	
																
																
																
																}
																
																
										       
																]}
																
																
																
																
																				
												],
										        
										        buttons: [{
				            text: 'Generate PDF',
				            handler: function(){
										    if(validate())    	
										   confirmCheck();	
										        	
										        } }]}]
										 
							         		}
							         
							         
							         
							         ]
						        })]
						 }]
						 });
	 userRes = ajaxFunction('POST','../GetUserServlet',false,null);
	  // alert("response data" +individualPieRes);
	 userResData = Ext.util.JSON.decode(userRes);
	  // alert("pieBrokData.length = "+individualPieData.length);
	  // alert("pieBrkData = "+individualPieData);
	 userStore.removeAll(true);
	 userStore.loadData(userResData);

	 userStore.commitChanges(); 					 
	Ext.getCmp('user').setValue(userResData[0].userId);		
	Ext.getCmp('month').setValue('1');
	Ext.getCmp('year').setValue(new Date().getFullYear());
	Ext.getCmp('dateRange').setValue('0');
	
				 
						 
	 viewport.render('pdfGenerateDatacontent');
	 });
