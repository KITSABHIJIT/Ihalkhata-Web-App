
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
Ext.onReady(function(){
	//alert("you are inside Ext.onReady(function()");
	 Ext.QuickTips.init();
	 function roundNumber(num, dec) {
			var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
			return result;
		}
function confirmCheck(shareholder,cost,count,date,items,giver,desc){
	var total_Cost=0;
	total_Cost=parseFloat(cost);
	var perHead=total_Cost/count;
	perHead=roundNumber(perHead,2);
	document.getElementById('perHead').value=perHead;
	//document.getElementById('totalExpense').value=total_Cost;
	 var tabs = new Ext.Panel({
		 						border:false,
								//height:380,
								autoHeight:true,
								width:'100%',
								layout:'column',
								items:[new Ext.ux.Image ({ 
											id: 'imgPreview',
											width:'40%',
											autoHeight:true,
											url: '../com_exp_cemk_css/images/confirm.png' 
										 }),{
											xtype:'panel',
											border:false,
											autoHeight:true,
											style:'padding:10px 10px 10px 10px',
											width:'55%',
											items:[
{
	xtype:'panel',
	border:false,
	width:'100%',
	style:'padding:0px 0px 5px 0px',
	autoHeight:true,
	layout:'column',
	items:[ {
			width: '40%',
			xtype:'displayfield',
			style:'font-size:14px',
			value:'Date:'
	   		}, 
	   		{
	   			width: '60%',
				xtype:'displayfield',
				style:'font-size:14px',
				value:'<b>'+date+'</b>'
		   		}
	   		
	   		]},{
	xtype:'panel',
	border:false,
	width:'100%',
	style:'padding:0px 0px 5px 0px',
	autoHeight:true,
	layout:'column',
	items:[ {
			width: '40%',
			xtype:'displayfield',
			style:'font-size:14px',
			value:'No Of Shareholders:'
	   		}, 
	   		{
	   			width: '60%',
				xtype:'displayfield',
				style:'font-size:14px',
				value:'<b>'+count+'</b>'
		   		}
	   		
	   		]}, {
	   			xtype:'panel',
	   			border:false,
	   			width:'100%',
	   			style:'padding:0px 0px 5px 0px',
	   			autoHeight:true,
	   			layout:'column',
	   			items:[ {
	   					width: '40%',
	   					xtype:'displayfield',
	   					style:'font-size:14px',
	   					value:'Shareholders Name:'
	   			   		},{
	   			   					width: '60%',
			   						xtype:'displayfield',
			   						style:'font-size:14px',
			   						value:'<b>'+shareholder+'</b>'
			   						}	   			   		
	   			   		]},{
	   			   			xtype:'panel',
	   			   			border:false,
	   			   			width:'100%',
	   			   			style:'padding:0px 0px 5px 0px',
	   			   			autoHeight:true,
	   			   			layout:'column',
	   			   			items:[ {
	   			   					width: '40%',
	   			   					xtype:'displayfield',
	   			   					style:'font-size:14px',
	   			   					value:'Category:'
	   			   			   		},{
	   			   			   					width: '60%',
			   			   						xtype:'displayfield',
			   			   						style:'font-size:14px',
			   			   						value:'<b>'+items+'</b>'
			   			   				   		}	   			   			   		
	   			   			   		]},
	   			   			  {
	   			   			   			xtype:'panel',
	   			   			   			border:false,
	   			   			   			style:'padding:0px 0px 5px 0px',
	   			   			   			autoHeight:true,
	   			   			   			width:'100%',
	   			   			   			layout:'column',
	   			   			   			items:[{
	   		   			   					width: '40%',
	   		   			   					xtype:'displayfield',
	   		   			   					style:'font-size:14px',
	   		   			   					value:'Description:'
	   		   			   			   		},
	   			   			   				{
	   		   			   			   					width: '60%',
		   			   			   						xtype:'displayfield',
		   			   			   						style:'font-size:14px',
		   			   			   						value:'<b>'+desc+'</b>'
		   			   			   				   		}
	   			   			   			   		
	   			   			   			
	   			   			   			   		
	   			   			   			   		]},{
	   			   			   			xtype:'panel',
	   			   			   			border:false,
	   			   			   			autoHeight:true,
	   			   			   			width:'100%',
	   			   			   			style:'padding:0px 0px 5px 0px',
	   			   			   			layout:'column',
	   			   			   			items:[ {
	   			   			   					width: '40%',
	   			   			   					xtype:'displayfield',
	   			   			   					style:'font-size:14px',
	   			   			   					value:'Total Cost:'
	   			   			   			   		}, 
	   			   			   			   		{
	   			   			   			   			width: '60%',
	   			   			   						xtype:'displayfield',
	   			   			   						style:'font-size:14px',
	   			   			   						value:'<b>Rs. '+total_Cost+'</b>'
	   			   			   				   		}
	   			   			   			   		
	   			   			   			   		]},
	   			   			   			{
	   			   			   			   			xtype:'panel',
	   			   			   			   			border:false,
	   			   			   			   			width:'100%',
	   			   			   			   			style:'padding:0px 0px 5px 0px',
	   			   			   			   			autoHeight:true,
	   			   			   			   			layout:'column',
	   			   			   			   			items:[ {
	   			   			   			   					width: '40%',
	   			   			   			   					xtype:'displayfield',
	   			   			   			   					style:'font-size:14px',
	   			   			   			   					value:'Paid By:'
	   			   			   			   			   		}, 
	   			   			   			   			   		{
	   			   			   			   			   			width: '60%',
	   			   			   			   						xtype:'displayfield',
	   			   			   			   						style:'font-size:14px',
	   			   			   			   						value:'<b>'+giver+'</b>'
	   			   			   			   				   		}
	   			   			   			   			   		
	   			   			   			   			   		]},{
	   			   			   			   			xtype:'panel',
	   			   			   			   			border:false,
	   			   			   			   			width:'100%',
	   			   			   			   			autoHeight:true,
	   			   			   			   			style:'padding:0px 0px 5px 0px',
	   			   			   			   			layout:'column',
	   			   			   			   			items:[ {
	   			   			   			   					width: '40%',
	   			   			   			   					xtype:'displayfield',
	   			   			   			   					style:'font-size:14px',
	   			   			   			   					value:'Cost/Head:'
	   			   			   			   			   		}, 
	   			   			   			   			   		{
	   			   			   			   			   			width: '60%',
	   			   			   			   						xtype:'displayfield',
	   			   			   			   						style:'font-size:14px',
	   			   			   			   						value:'<b>Rs. '+perHead+'</b>'
	   			   			   			   				   		}
	   			   			   			   			   		
	   			   			   			   			   		]}
											       
											       
											       
										]}],buttons:[      
														{
															xtype:'button',
															text:'I ACCEPT',
															columnWidth: '.05',
															 handler: function(){
															win.close();
															formSubmit();
																		}
														},
														{
															xtype:'button',
															text:'CANCEL',
															columnWidth: '.05',
															 handler: function(){
															win.close();
																		}
														}
											       
											       ]

				
	});
	    var win = new Ext.Window({
	    	autoHeight:true,
            modal: true,
            title:'Confirm the Selection',
            frame:true,	
	        resizable:false,
	        closable:true,	
	         closable:true,
	         width:'45%',
	        items: new Ext.Panel(
	     	        {	
	     	           items: tabs
	     	        })
	     });

	     win.show(this);
	
}

function dateCheck(shareholder,cost,count,date,items,giver,desc){

var dates=new Date();
//alert(dates.getDate()+"/"+(dates.getMonth() +1)+"/"+dates.getFullYear());
var systemDate=dates.getDate()+"/"+(dates.getMonth() +1)+"/"+dates.getFullYear();
var givenDate=parseFloat(date.split("/")[0])+"/"+parseFloat(date.split("/")[1])+"/"+parseFloat(date.split("/")[2]);
var systemTime=dates.getHours()+1;
	
	 var tabs = new Ext.Panel({
		 region: 'center',
		 border:false,
			autoHeight:true,
			width:'100%',
			layout:'column',
			items:[
                	{
								xtype:'panel',
								border:false,
								autoHeight:true,
								width:'100%',
								layout:'column',
								items:[new Ext.ux.Image ({ 
											id: 'imgPreview',
											autoHeight:true,
											width:'50%',
											url: '../com_exp_cemk_css/images/TimeIsMoney.png' 
										 }),{
											xtype:'panel',
											border:false,
											autoHeight:true,
											width:'50%',
											items:[{
	   			   			   			   			xtype:'panel',
	   			   			   			   			border:false,
	   			   			   			   			autoHeight:true,
	   			   			   			   			width:'100%',
	   			   			   			   			layout:'column',
	   			   			   			   			items:[{
	   			   			   			   					
	   			   			   			   					xtype:'displayfield',
	   			   			   			   					width:'100%',
	   			   			   			   					style:'font-size:14px',
	   			   			   			   					value:'You are in the next Day: <br>Date:<font color="red"><b>'+date+'</b></font><br>Are you sure to proceed'
	   			   			   			   			   		}]}]}],
	   			   			   			   			   		buttons:[{
																xtype:'button',
																text:'PROCEED',
																columnWidth: '.05',
																 handler: function(){
																win.close();
																confirmCheck(shareholder,cost,count,date,items,giver,desc);
																			}
															},{
																xtype:'button',
																text:'CANCEL',
																columnWidth: '.05',
																 handler: function(){
																win.close();
																			}
															}]

				} 
				
				]
	});
	    var win = new Ext.Window({
	         
	    	autoHeight:true,
            modal: true,
            title:'Confirm the Selection',
            frame:true,	
	        resizable:false,
	        closable:true,	
	         closable:true,
	         width:'20%',
	        items: new Ext.Panel(
	     	        {	
	     	           items: tabs
	     	        })
	     });
	     
if(systemDate===givenDate && systemTime<=6){
	 
	win.show(this);

}else{
	confirmCheck(shareholder,cost,count,date,items,giver,desc);
}
	    
	

}
function addItems(){
	var item=Ext.getCmp('itemCombo').getValue();
	Ext.getCmp('dataEntryform').getForm().submit({reset:false,
  	  waitMsg:'adding items...',
  	  timeout: 4000,
  	  url:'../AddItemsServlet',
  	  params: {
  	  item:item
  	  },
  	  success: function(form, action)

        {
  		extINFOAlert(action.result.res);
  	 
        },

        failure: function(form,action)

        {
      	 
        	extWarningAlert(action.result.res);
      	}
        });
}


function formSubmit(){
	
	Ext.getCmp('dataEntryform').getForm().submit({reset:false,
  	  waitMsg:'Inserting Data',
  	  timeout: 4000,
  	  params: {
  	  shareholders: document.getElementById('shareholders').value,
  	  shareholderCount : document.getElementById('shareholderCount').value,
  	  totalExpense : document.getElementById("totalExpense").value,
  	  paidBy:document.getElementById('paidBy').value,
  	  itemsList:document.getElementById('itemsList').value,
  	  dates:document.getElementById('dates').value,
  	  perHead:document.getElementById('perHead').value,
  	  desc:document.getElementById('description').value
  	 
		  },
  	  success: function(form, action)

        {

  		extINFOAlert(action.result.res);
  	 

        },

        failure: function(form,action)

        {
      	 
        	extERRORAlert(action.result.res);
      	

        }
        });
}
function validate(){
var share=Ext.getCmp('user').getValue();
var shareAll=Ext.getCmp('all').getValue();

var desc=Ext.getCmp('desc').getValue();
var donner=Ext.getCmp('paidBy1').value;
var auto_Expense=Ext.getCmp('itemCombo').value;
var auto_Price=Ext.getCmp('rs10Price').getValue();
var flag=true;

if(auto_Expense=='' || auto_Expense==null || auto_Expense==undefined){}
else{

	if (isNaN(auto_Price) || auto_Price.trim()=="" || auto_Price==null || auto_Price=="0"){
	 
				extERRORAlert('Price must be valid.');
				Ext.getCmp('rs10Price').setValue('0');
				

	flag=false;

	}
	else{flag=true;}
	}


if (donner=="" || donner==null || donner==undefined){
			extERRORAlert('Please select a Creditor.');
			flag=false;

}

if (desc=="" || desc==null || desc==undefined){
 
				extERRORAlert('Description is mandatory.');
				flag=false;

	}

if (shareAll===false && (share==="" || share===null || share===undefined)){
 
			extERRORAlert('Please select a ShareHolder.');
			flag=false;

}

if (auto_Expense=='' || auto_Expense==null || auto_Expense==undefined){
 
			extERRORAlert('Please select an Item .');
			flag=false;

}


if(flag==true)
	{
		return true;
	}
if(flag==false)
	{
		return false;
	}
}



function calculate(){
var total=0;
var itemsList="";
	itemsList=Ext.getCmp('itemCombo').value;
	total=Ext.getCmp('rs10Price').getValue();

document.getElementById('totalExpense').value=total;
document.getElementById('itemsList').value=itemsList;
document.getElementById('dates').value=Ext.getCmp('dates').getRawValue();

}

function shareholderChecking(){
var count=0;
var shareHolders="";

if(Ext.getCmp('all').getValue()===true){
	for(var i=0;i<userResData.length;i++){
	if(shareHolders==""){
		shareHolders=userResData[i].userId;
	}
	else{
		shareHolders=shareHolders+','+userResData[i].userId;
	
	}}
count=userResData.length;
}
else{
	
	shareHolders=Ext.getCmp('user').getValue();
	count=Ext.getCmp('user').getValue().split(',').length;
	
}
document.getElementById('shareholders').value=shareHolders;
document.getElementById('shareholderCount').value=count;



}


	 var viewport = new Ext.form.FormPanel({
		 layout:'form',
		 width:'80%', 	
		 id:'dataEntryform',
		 url:'../DataEntryServlet',

		         items:[{
						 xtye:'panel',
						 width:'100%',
						 height:390,
						 standardSubmit:true,
						 border:false,
						  region:'center',
						  margins:'135 0 0 0',
						 items:[
						        
						     new Ext.TabPanel({
						        	id : 'content-panel',
						            autoHeight:true,
						             width:'100%',
						             border:false,
						             title:'BCC Report',
						             fieldlabel:'BCC Report',
						            // defaults:{autoScroll:true}, 
						             id:'Tabs',
						             activeTab: 0,
						             style:'padding:0 0 0 0',
							         items:[
						
							                
							                {
							        	 	title: 'Entry Items',
							                layout:'form',
							                autoHeight:true,
							                width:'100%',
							                border:false,
							                style:'padding:10px 10px 10px 10px',
							                items:[ 
							{
								xtype:'panel',
								border:false,
								 autoHeight:true,
								width:'100%',
								layout:'column',
								items:[ {
										xtype:'panel',
										border:false,
										autoHeight:true,
										width:'100%',
										items:[  
	                
										       {
							                				xtype:'panel',
							                				border:false,
							                				autoHeight:true,
							                				layout:'column',
							                				style:'padding:0px 0px 20px 0px',
							                				items:[
							                				       		{
																		columnWidth: '.25',
																		xtype:'displayfield',
																		style:'font-size:14px',
																		value:'Select Date:'
							                				       		},
							                				       		new Ext.form.DateField({
							                	                        fieldLabel: 'fromDate',
							                	                        name: 'dates',
																		id:'dates',
													                	format:'d/m/Y',
													                	altFormats:'j/n/Y',
													                	editable:false,
													                	columnWidth: '.3',
							                	                        allowBlank:true,
							                	                        maxValue:new Date(),
																	    listeners:{
																        	 'focus': function()
																        	 {this.setMaxValue(new Date());}
																	    }
							                	                        })
							                				       ]
							                					},
																
																{	

													 				xtype:'panel',
													 				id:'brokerPanel',
													 				autoHeight:true,
													 				style:'padding:0px 0px 20px 0px',
													 				layout:'column',
													 				border:false,
													 					items:[{
													 						width: '25%',
													xtype:'displayfield',
													id: 'Items',
													style:'font-size:14px',
													value:'Select an Item:'

													},new Ext.form.ComboBox({
														
														 	cls : 'combosearch', 
													       store: brokerStore,
													       enableKeyEvents: true,
													       typeAhead: false,
													       
													        anyMatch:true,
													        id:'itemCombo',
													        displayField: 'brokerdisplay',
													        valueField:'brokervalue',
													        //hiddenName:'cc_action',
													        mode: 'local',
													        triggerAction: 'all',
													        emptyText:'Type an Item name...',
													        selectOnFocus:true,
													        width: '35%',
													        listWidth: 200,
													        maxHeight:135,
													        editable: true,
													        minChars : 4,
													        maskRe :/^[a-zA-Z ]{0,}$/,
										                    forceSelection:false,
													        lazyRender:false,
													        //applyTo: 'local-state',        
													        hideTrigger:true,
													        style:'padding:0px 20px 0px 0px',
													        listeners:{
													         'keyup':function(key)
													         {
															        	 
															        	 var fieldValue = this.getRawValue();
															        	
															        	
															        	 if(fieldValue.length>0)
															        	 {
															       // 	 if(headersearchdata1 == '[{ }]')
															        // 	{
															        		 params = "query="+fieldValue;
														   	 					headersearchdata1 = ajaxFunction('GET','../AutoFillServlet',false,params);
														   	 					//alert("headersearchdata1 ="+headersearchdata1);
																		headersearchdata = Ext.util.JSON.decode(headersearchdata1);
															        	 
																		headersearchdata1 = '[{ }]';
															        	this.getStore().removeAll(true);
															    		this.getStore().loadData(headersearchdata);
															    		
															     		
																//}		
															         }		
															        	
																         
													         }
															         
													         
													  }
													          }),
													          
													          {
													        	    xtype: 'box',
													        	    id:'addbutton',
													        	    width:'5%',
													        	    html: '<a href="#" rel="info"> <img src="../com_exp_cemk_css/images/addIcon.gif" alt="Expense Records" /></a>', 
													        	   // style:'padding:0px 0px 0px 20px;cursor: pointer',
													        	    style:'padding:0px 0px 0px 20px',
													        	    listeners: {render: function(c){c.el.on('click', function() { 
													        	    	if(isEmptyValidation('itemCombo','Please select an Item.'))
													        	    		addItems();
													        	    	});}}
													        	}
													          
													          
													          
													          ]},
																
														    	   {
																		
										                				xtype:'panel',
										                				border:false,
										                				autoHeight:true,
										                				style:'padding:0px 0px 0px 0px',
																		layout:'column',
										                				items:[
																	{
																		columnWidth: '.25',
																		xtype:'displayfield',
																		style:'font-size:14px',
																		value:'Description:'
																		},
																		{
																		      xtype:'textarea',
																		      id:'desc',
																		      enableKeyEvents:true,
																		      columnWidth: '.3',
																		      maskRe :/^[a-zA-Z0-9 ,@]{0,}$/,
																		      listeners:{
																		        	 'keyup': function()
																		        	 {
																		        		 limiter(50,'desc','limiter','extjs'); 
																		        	 }}
																		    }       
										                				       ]
														    	   },{
																		
										                				xtype:'panel',
										                				border:false,
										                				autoHeight:true,
										                				style:'padding:0px 0px 15px 0px',
																		layout:'column',
										                				items:[
																	{
																		columnWidth: '.55',
																		xtype:'displayfield',
																		id:'limiter',
																		style:'font-size:10px;text-align: right;',
																		value:'<font color="blue"><i>50 Characters left</i></font>'
																		}]
														    	   },
														    	   {
																		
										                				xtype:'panel',
										                				border:false,
										                				autoHeight:true,
										                				style:'padding:0px 0px 15px 0px',
																		layout:'column',
										                				items:[
														    	   {
																		columnWidth: '.25',
																		xtype:'displayfield',
																		id:'rs10',
																		style:'font-size:14px',
																		value:'Expense:'
						                				       		},																													
						                				       		{
						                				       		xtype: 'textfield',
																		id: 'rs10Price',
																		columnWidth: '.3',
																		maskRe :/^[0-9.]{0,}$/,
																		value: '0'
																	}]
														    	   },{
																
									                				xtype:'panel',
									                				border:false,
									                				autoHeight:true,
									                				style:'padding:0px 0px 10px 0px',
																	layout:'column',
									                				items:[{
																		xtype:'checkbox',        
																		 id:'all',
																		 listeners:{
																        	 'check': function()
																        	 {
								                					if(Ext.getCmp('all').getValue()==true){
								                					
								                						Ext.getCmp('user').setValue('');
								                						Ext.getCmp('user').disable();
								                					
								                					
								                					}else{
								                						Ext.getCmp('user').enable();
								                					}
																        	 }
								                					}
																		 
																	}, 
																	{
																		columnWidth: '.25',
																		xtype:'displayfield',
																		style:'font-size:14px',
																		value:'All Share Holders'
																		}]
																	
																
																},
																{
																
									                				xtype:'panel',
									                				border:false,
									                				autoHeight:true,
									                				style:'padding:0px 0px 10px 0px',
																	layout:'column',
									                				items:[{
																					columnWidth: '.25',
																					xtype:'displayfield',
																					style:'font-size:14px',
																					value:'Share Holders:'
									                				       		},new Ext.ux.form.LovCombo({
																					id:'user',
																					name:'dimension',
																					hideTrigger:false,
																					editable:false,
																					store:userStore,
																					valueField:'userId',
																					displayField:'userName',
																					resizable:true,
																					listWidth: 325,
																					typeAhead: true,
																					mode: 'local',
																					columnWidth: '.3',
																					triggerAction: 'all',
																					selectOnFocus:true
																					
																					
																					})]
																
																},
																{
																	
									                				xtype:'panel',
									                				border:false,
									                				autoHeight:true,
									                				style:'padding:0px 0px 10px 0px',
																	layout:'column',
									                				items:[{
																					columnWidth: '.25',
																					xtype:'displayfield',
																					style:'font-size:14px;',
																					value:'Paid By:'
									                				       		},
																				new Ext.form.ComboBox({
																				id:'paidBy1',
																				name:'dimension',
																				editable:false,
																				store:userStore,
																				valueField:'userId',
																				displayField:'userName',
																				typeAhead: true,
																				mode: 'local',
																				resizable:true,
																				listWidth: 325,
																				columnWidth: '.3',
																				triggerAction: 'all',
																				selectOnFocus:true,
																				listeners:{
																		'select': function(key){
																		 document.getElementById('paidBy').value = this.getValue();
		                    		    					
																		}}
																				
																				})]}
																
																
																]}
																
																
																
																
																				
												]}]
										 
							         		}
							         
							         
							         
							         ]
						        })],
						        buttons: [{
            text: 'Submit',
            handler: function(){
			var val=validate();
			if(val===true){
			calculate();
			shareholderChecking();
        	        	
        	  	/*alert("inside submit");
				alert("shareholders "+document.getElementById('shareholders').value);
				alert("shareholderCount "+document.getElementById('shareholderCount').value);
				alert("paidBy "+document.getElementById('paidBy').value);
				alert("totalExpense "+document.getElementById('totalExpense').value);
				alert("itemsList "+document.getElementById('itemsList').value);
				alert("Date "+document.getElementById('dates').value);*/
				document.getElementById('description').value=Ext.getCmp('desc').getValue();	
				
				var shareHoldersName="";
				if(Ext.getCmp('all').getValue()===true){
					for(var i=0;i<userResData.length;i++){
					if(shareHoldersName==""){
						shareHoldersName=userResData[i].userName;
					}
					else{
						shareHoldersName=shareHoldersName+','+userResData[i].userName;
					
					}}
				}
				else{
					
					shareHoldersName=Ext.getCmp('user').getRawValue();
					
					
				}
				dateCheck(shareHoldersName,document.getElementById('totalExpense').value,document.getElementById('shareholderCount').value,document.getElementById('dates').value,document.getElementById('itemsList').value,Ext.getCmp('paidBy1').getRawValue(),document.getElementById('description').value);
				//confirmCheck(document.getElementById('shareholders').value,document.getElementById('totalExpense').value,document.getElementById('shareholderCount').value,document.getElementById('dates').value,document.getElementById('itemsList').value,document.getElementById('paidBy').value,document.getElementById('description').value);
		     
        }    
          
       } },{
           text: 'Reset',
           handler: function(){
        	   Ext.getCmp('dataEntryform').getForm().reset();
           }}]
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
	Ext.getCmp('dates').setValue(new Date());	
	Ext.getCmp('paidBy1').setValue(userResData[0].userId);
	document.getElementById('paidBy').value=userResData[0].userId;
	
	//Ext.getCmp('brokerFinal').getStore().commitChanges();
 	//Ext.getCmp('brokerFinal').getStore().removeAll();					 
						 
	 viewport.render('entryDatacontent');
	 });
