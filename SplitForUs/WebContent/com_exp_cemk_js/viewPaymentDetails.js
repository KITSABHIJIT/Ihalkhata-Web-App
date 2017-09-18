Ext.BLANK_IMAGE_URL = '../com_exp_cemk_css/images/default/s.gif';
Ext.ns('Example');
Example.version = '1.0';

Ext.override(Ext.ux.form.LovCombo, {
	beforeBlur: Ext.emptyFn
});
Ext.onReady(function(){
	
	
	
	 Ext.QuickTips.init();
	 Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	 function renderDate(val, meta, record){
	      var myDate=val.split("-");
	      var finalDate=myDate[2]+"/"+myDate[1]+"/"+myDate[0];
	      meta.attr = 'ext:qtip="' + finalDate + '"';
		  return finalDate;
		  
	  	
	    }
	 function renderShow(val, meta, record){
	      meta.attr = 'ext:qtip="' + val + '"';
	      return val;
		 
	    }
	 function confirmCheck(date,giver,payee,amount){
			
				 var tabsPopUp = new Ext.Panel({
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
														url: '../com_exp_cemk_css/images/money transfer online.gif' 
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
									   			   			style:'padding:0px 0px 5px 0px',
									   			   			layout:'column',
									   			   			items:[ {
									   			   					width:'100%',
									   			   					xtype:'displayfield',
									   			   					style:'font-size:14px',
									   			   					value:'<b>Are you sure to Transfer Money</b>'
									   			   			   		}	   			   			   		
									   			   			   		]},
			{
				xtype:'panel',
				border:false,
				autoHeight:true,
				width:'100%',
				style:'padding:0px 0px 5px 0px',
				layout:'column',
				items:[ {
						width:'40%',
						xtype:'displayfield',
						style:'font-size:14px',
						value:'Date:-'
				   		}, 
				   		{
				   			width:'60%',
							xtype:'displayfield',
							style:'font-size:14px',
							value:'<b>'+date+'</b>'
					   		}
				   		
				   		]},
				   			   	
				   			   			   			
					   			   			   				   		{
									   			   			   			xtype:'panel',
									   			   			   			border:false,
									   			   			   			autoHeight:true,
									   			   			   			width:'100%',
									   			   			   			style:'padding:0px 0px 5px 0px',
									   			   			   			layout:'column',
									   			   			   			items:[ 
									   			   			   			   		{
									   			   			   			   			width:'40%',
									   			   			   						xtype:'displayfield',
									   			   			   						style:'font-size:14px',
									   			   			   						value:'From:-'
									   			   			   				   		},{
									   			   			   				   			width:'60%',
										   			   			   						xtype:'displayfield',
										   			   			   						style:'font-size:14px',
										   			   			   						value:'<b>'+Ext.getCmp('creditor').getRawValue()+'</b>'
										   			   			   				   		}]},
										   			   			   				{
														   			   			   			xtype:'panel',
														   			   			   			border:false,
														   			   			   			style:'padding:0px 0px 5px 0px',
														   			   			   			autoHeight:true,
														   			   			   			width:'100%',
														   			   			   			layout:'column',
														   			   			   			items:[ 
														   			   			   			   		{
														   			   			   			   			width:'40%',
														   			   			   						xtype:'displayfield',
														   			   			   						style:'font-size:14px',
														   			   			   						value:'To:-'
														   			   			   				   		},{
														   			   			   				   			width:'60%',
															   			   			   						xtype:'displayfield',
															   			   			   						style:'font-size:14px',
															   			   			   						value:'<b>'+Ext.getCmp('payee').getRawValue()+'</b>'
															   			   			   				   		}]},
				   			   			   			   		
														       
															   			   			   				 {
																			   			   			   			xtype:'panel',
																			   			   			   			border:false,
																			   			   			   			autoHeight:true,
																			   			   			   			style:'padding:0px 0px 5px 0px',
																			   			   			   			width:'100%',
																			   			   			   			layout:'column',
																			   			   			   			items:[ 
																			   			   			   			   		{
																			   			   			   			   			width:'40%',
																			   			   			   						xtype:'displayfield',
																			   			   			   						style:'font-size:14px',
																			   			   			   						value:'Transfer Amount:-'
																			   			   			   				   		},{
																			   			   			   				   			width:'60%',
																				   			   			   						xtype:'displayfield',
																				   			   			   						style:'font-size:14px',
																				   			   			   						value:'<b>Rs. '+amount+'</b>'
																				   			   			   				   		}]}]

							} 
							
							],buttons:[{
																		xtype:'button',
																		text:'Transfer',
																		columnWidth: .05,
																		 handler: function(){
																		win.close();
																		submit(date,giver,payee,amount);
																					}
																	},{
																		xtype:'button',
																		text:'Cancel',
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
				     	           items: tabsPopUp
				     	        })
				     });
				     win.show(this);
				
			}
	 
	 function submit(date,payee,creditor,amount){
		 var params="date="+date+"&payee="+payee+"&creditor="+creditor+"&amount="+amount;
			var result=ajaxFunction('POST','../GivePaymentServlet',false,params);
			var resultData=result.split(":");
			Ext.Msg.alert(resultData[0],resultData[1]);
			var params1="start=0&limit=4";
			PaymentRes=ajaxFunction('POST','../GridPaymentServlet',false,params1);
			PaymentData = Ext.util.JSON.decode(PaymentRes);
			Paymentchart.removeAll(true);
			Paymentchart.loadData(PaymentData);
			Paymentchart.commitChanges();
			Ext.getCmp('amount').setValue('');
		 
	 }
	 
	 
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
	   	 
	 
	 
	 
  
   var PaymentDetailsReader= new Ext.data.JsonReader({
       totalProperty: 'totalCount',
       root: 'items',	       
       fields:[   {name  : 'date',mapping : 'date', type: 'date', dateFormat: 'Y-m-d'},
		        {name    : 'items',mapping : 'items'},
		        {name    : 'desc',mapping : 'desc'},
		        {name    : 'borrower',mapping : 'borrower'},
		        {name    : 'to',mapping : 'to'},
		        {name    : 'for',mapping : 'for'},
		        {name    : 'relation',mapping : 'relation'},
		        {name    : 'payee',mapping : 'payee'},
		        {name    : 'amount',mapping : 'amount'}
		     ]
   });

   var PaymentPaidReader= new Ext.data.JsonReader({
		   totalProperty: 'totalCount',
	       root: 'items',
	       fields:[					    		      		       
			 	{name    : 'date',mapping : 'date', type: 'date', dateFormat: 'Y-m-d'},
		        {name    : 'receiver',mapping : 'receiver'},
		        {name    : 'relation',mapping : 'relation'},
		        {name    : 'from',mapping : 'from'},
		        {name    : 'payee',mapping : 'payee'},
		        {name    : 'amount',mapping : 'amount'}
		    ]
   });
   //added for pagination.....
   
    // manually load local data

   
   var PaymentDetailschart =new Ext.data.GroupingStore({
		proxy:new Ext.data.HttpProxy({ url: '../GridPaymentDetailsServlet',method: 'POST', timeout: 300000 }), 
		 reader: PaymentDetailsReader,
         groupField:'borrower'
     });
   var PaymentPaidchart =new Ext.data.GroupingStore({
		proxy:new Ext.data.HttpProxy({ url: '../GridPaymentDetailsServlet',method: 'POST', timeout: 300000 }), 
		 reader: PaymentPaidReader,
		 baseParams:{type:'1'},
        groupField:'receiver'
    });
   var tb = new Ext.Toolbar({
       items: [{xtype: 'tbfill'},{
           xtype: 'button',
           cls  : 'x-btn-text-icon',
           icon : uriPrefix+'/com_exp_cemk_css/images/Excel_icon.gif',
           text:'Export',
           autoHeight:true,
           handler: function() {
       	exportGridToExcel(this.findParentByType('grid'));
           }
           }]
       });
   var tbPayment = new Ext.Toolbar({
       items: [{xtype: 'tbfill'},{
           xtype: 'button',
           cls  : 'x-btn-text-icon',
           icon : uriPrefix+'/com_exp_cemk_css/images/Excel_icon.gif',
           text:'Export',
           autoHeight:true,
           handler: function() {
       	exportGridToExcel(this.findParentByType('grid'));
           }
           }]
       });
   var tbPaidPayment = new Ext.Toolbar({
       items: [{xtype: 'tbfill'},{
           xtype: 'button',
           cls  : 'x-btn-text-icon',
           icon : uriPrefix+'/com_exp_cemk_css/images/Excel_icon.gif',
           text:'Export',
           autoHeight:true,
           handler: function() {
       	exportGridToExcel(this.findParentByType('grid'));
           }
           }]
       });
   var Paymentchart = new Ext.data.JsonStore({
		root: 'items',
        totalProperty: 'totalCount',
		proxy:new Ext.data.HttpProxy({ url: '../GridPaymentServlet', timeout: 300000 }), 
        fields: [
		      		       
				        {name    : 'borrower',mapping : 'borrower'},
				        {name    : 'relation',mapping : 'relation'},
				        {name    : 'from',mapping : 'from'},
				        {name    : 'payee',mapping : 'payee'},
				        {name    : 'amount',mapping : 'amount',type:'float'}
		]
	});

    // create the Grid
    var gridtwo = new Ext.grid.GridPanel({
        store: PaymentDetailschart,
        height: 430,
        trackMouseOver:false,
        tbar: tbPayment,
        disableSelection:true,
        viewConfig:{forceFit:true,emptyText: 'No Payment details available',deferEmptyText: false},
        loadMask: true,
        width:'100%',
        columns: [
            {
                id       :'date',
                header   : '<b>Date</b>',
                dataIndex: 'date',
                sortable : true,
                renderer: Ext.util.Format.dateRenderer('d/m/Y')            },
            {
                header   : '<b>Borrower</b>', 
                dataIndex: 'borrower',
                
                sortable : true            },
                {
                    header   : '&nbsp;', 
                    dataIndex: 'relation',
                    
                    sortable : true            },
                {
                    header   : '<b>Amount (Rs.)</b>', 
                    dataIndex: 'amount',
                    renderer:roundNumberGrid,
                    sortable : true            },
                {
                    header   : '&nbsp;', 
                    dataIndex: 'to',
                    
                    sortable : true            },
                    {
                        header   : '<b>Payee</b>', 
                        dataIndex: 'payee',
                        
                        sortable : true            },
                        {
                            header   : '', 
                            dataIndex: 'for',
                            
                            sortable : true            },
                            {
                                header   : '<b>Item</b>', 
                                dataIndex: 'items',
                                
                                sortable : true            },
                                {
                                    header   : '<b>Description</b>', 
                                    dataIndex: 'desc',
                                    
                                    sortable : true }
                
        ],
        view: new Ext.grid.GroupingView({
            forceFit:true,emptyText: 'No Payment details available',deferEmptyText: false,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
        }),    
        
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: PaymentDetailschart,
            //height: 30,
			displayInfo: true,
            plugins: new Ext.ux.ProgressBarPager(),
            //displayMsg: 'Displaying notifications {0} - {1} of {2}',
            emptyMsg: "No Items to display"
        }) 
    });
	
	
     PaymentDetailschart.load({params:{start:0, limit:15}});
    // PaymentDetailschart.load.defer(20,PaymentDetailschart,[{params:{start:0, limit:15}}]);
     
     
     
     
     
     var gridone = new Ext.grid.GridPanel({
         store: Paymentchart,
         height: 405,
         tbar: tb,
         viewConfig:{forceFit:true,emptyText: 'No Payment available',deferEmptyText: false},
         trackMouseOver:false,
         disableSelection:true,
         loadMask: true,
         width:'100%',
         columns: [
             
             {
                         header   : '<b>Payee</b>', 
                         dataIndex: 'payee',
                         sortable : true         },
                 {
                     header   : '&nbsp;', 
                     dataIndex: 'relation',
                     sortable : true            },
                 {
                     header   : '<b>Amount (Rs.)</b>', 
                     dataIndex: 'amount',
                     renderer:roundNumberGrid,
                     sortable : true            },
                 {
                     header   : '&nbsp;',
                     dataIndex: 'from',
                     sortable : true            },
                     {
                         header   : '<b>Borrower</b>', 
                         dataIndex: 'borrower',
                         sortable : true            }
                 
         ],
         
         bbar: new Ext.PagingToolbar({
             pageSize: 10,
             store: Paymentchart,
             //height: 30,
 			displayInfo: true,
             plugins: new Ext.ux.ProgressBarPager(),
             //displayMsg: 'Displaying notifications {0} - {1} of {2}',
             emptyMsg: "No Items to display"
         }) 
     });
 	
 	
      Paymentchart.load({params:{start:0, limit:10}});
     
      var gridthree = new Ext.grid.GridPanel({
          store: PaymentPaidchart,
          height: 430,
          trackMouseOver:false,
          tbar: tbPaidPayment,
          disableSelection:true,
          viewConfig:{forceFit:true,emptyText: 'No Payment Transaction available',deferEmptyText: false},
          loadMask: true,
          width:'100%',
          columns: [
              {
                  id       :'date',
                  header   : '<b>Date</b>',
                  dataIndex: 'date',
                  sortable : true,
                  renderer: Ext.util.Format.dateRenderer('d/m/Y')            },
              {
                  header   : '<b>Receiver</b>', 
                  dataIndex: 'receiver',
                  sortable : false            },
                  {
                      header   : '&nbsp;', 
                      dataIndex: 'relation',
                      sortable : false            },
                  {
                      header   : '<b>Amount (Rs.)</b>', 
                      dataIndex: 'amount',
                      renderer:roundNumberGrid,
                      sortable : true            },
                  {
                      header   : '&nbsp;', 
                      dataIndex: 'from',
                      sortable : false            },
                      {
                          header   : '<b>Payee</b>', 
                          dataIndex: 'payee',
                          sortable : false            }
                  
          ],
          view: new Ext.grid.GroupingView({
              forceFit:true,emptyText: 'No Payment Transaction available',deferEmptyText: false,
              groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
          }),    
          
          bbar: new Ext.PagingToolbar({
              pageSize: 15,
              store: PaymentPaidchart,
              //height: 30,
  			displayInfo: true,
              plugins: new Ext.ux.ProgressBarPager(),
              //displayMsg: 'Displaying notifications {0} - {1} of {2}',
              emptyMsg: "No Items to display"
          }) 
      });
  	
  		//PaymentPaidchart.load.defer(20,PaymentPaidchart,[{params:{start:0, limit:15}}]);
  		PaymentPaidchart.load({params:{start:0, limit:15}});
     
    
     //gridtwo.render('viewData123');	 
		 var viewport2 = new Ext.form.FormPanel({
			 layout:'form',
			 border:false,
			 width:'100%',
			 height:460,
			 id:'Myform',
			 //renderTo: Ext.getBody(),
			 url:'',

			         items:[{
							 xtye:'panel',
							  width:'100%',
							 standardSubmit:true,
							 border:false,
							 items:[
							        new Ext.TabPanel({
							        	id : 'content-panel',
							        	 autoHeight: true,
							        	 width:'100%',
							             title:'BCC Report',
							             fieldlabel:'BCC Report',
							            // defaults:{autoScroll:true}, 
							             id:'Tabs',
							             activeTab: 0,
							             style:'padding:0 0 0 0',
								         items:[
							
								                {
									        	 	title: 'Payments To be Given',
									                layout:'form',
									                autoHeight: true,
										        	 width:'100%',
										        	 items:[  
														{
															xtype:'panel',
															border:true,
															height: 430,
												        	 width:'100%',
															layout:'column',
															items:[    							                       
									                         {
																xtype:'panel',
																border:false,
																title:'Payment Details',
																autoHeight: true,
													        	 width:'50%',
																layout:'column',
																items:[gridone]},
									                        {
																xtype:'panel',
																border:false,
																title:'Payment Module',
																height: 430,
													        	 width:'49.9%',
																//layout:'column',
																items:[
																       
																       {
																xtype:'panel',
																border:false,
																autoHeight: true,
													        	width:'100%',
													        	style:'padding:10px 0px 10px 10px',
																layout:'column',
																items:[{
																columnWidth: '.3',
																xtype:'displayfield',
																style:'font-size:14px',
																value:'Select a Money Owner:'
																	},
																	new Ext.form.ComboBox({
																		id:'payee',
																		name:'dimension',
																		hideTrigger:false,
																		store:userStore,
																		valueField:'userId',
																		displayField:'userName',
																		typeAhead: true,
																		editable:false,
																		mode: 'local',
																		columnWidth: '.3',
																		resizable:true,
																		triggerAction: 'all',
																		selectOnFocus:true,
																		listeners:{
																			'select': function(key){}}
																		
																		})]},
																       {

																			xtype:'panel',
																			border:false,
																			autoHeight: true,
																        	 width:'100%',
																        	 style:'padding:10px 0px 10px 10px',
																			layout:'column',
																			items:[{
																			columnWidth: '.3',
																			xtype:'displayfield',
																			style:'font-size:14px',
																			value:'Select a Money Giver:'
																				},
																				new Ext.form.ComboBox({
																					id:'creditor',
																					name:'dimension',
																					hideTrigger:false,
																					store:userStore,
																					valueField:'userId',
																					displayField:'userName',
																					typeAhead: true,
																					mode: 'local',
																					editable:false,
																					columnWidth: '.3',
																					resizable:true,
																					triggerAction: 'all',
																					selectOnFocus:true,
																					listeners:{
																			'select': function(key){}}
																					
																					})]
																       },

																       {
																		xtype:'panel',
																		border:false,
																		autoHeight: true,
																		style:'padding:10px 0px 10px 10px',
																		width:'100%',
																		layout:'column',
																		items:[
																	{
																		columnWidth: '.3',
																		xtype:'displayfield',
																		style:'font-size:14px',
																		value:'Payment Amount:'
																			},
																			{
																				xtype: 'textfield',
																				id: 'amount',
																				columnWidth: '.3',
																				maskRe :/^[0-9.]{0,}$/,
																				value: ''
																			}]}
															       
																       
																       ],buttons: [{																							
																			xtype: 'button',
																			id: 'paid',
																			columnWidth: '.1',
																			value: 'Paid',
																			text: 'Paid',
																			listeners:{
																			'click': function(key){
																		var d1=new Date();
																		var date=d1.getDate()+"/"+(d1.getMonth()+1)+"/"+d1.getFullYear();
																		var payee=Ext.getCmp('payee').value;
																		var creditor=Ext.getCmp('creditor').value;
																		var amount=parseFloat(Ext.getCmp('amount').getValue());
																		if(payee==creditor){
																			Ext.Msg.show({
																				 title:'Sorry!',
																				msg: 'Wrong Selection. Payee and creditor cannot be same.',
																			   buttons: Ext.Msg.OK,
																			   animEl: 'elId',
																			   icon: Ext.MessageBox.ERROR
																			}); 
																			
																		}
																		else if(isNaN(amount) || amount==null || amount<=0 || parseFloat(amount)*1==0){
																			Ext.Msg.show({
																				 title:'Sorry!',
																				msg: 'Enter a valid Transfer amount.',
																			   buttons: Ext.Msg.OK,
																			   animEl: 'elId',
																			   icon: Ext.MessageBox.ERROR
																			}); 
																			
																			Ext.getCmp('amount').setValue('');
																		}
																		else{
																			confirmCheck(date,payee,creditor,amount);
																		}
																	}}
																		}]}
									                      
									                       ]}
									                       
									                       
									                       
									                       
									                       ]},
									                       {
												        	 	title: 'Payments at a glance ',
												                layout:'form',
												                autoHeight: true,
																width:'100%',
												                items:[   gridtwo]},
												                {
													        	 	title: 'Paid Amounts',
													                layout:'form',
													                autoHeight: true,
																	width:'100%',
													                items:[   gridthree]}
							         
							        ]})
							        
							       
							        
							        
							        
							        
							        
							        ]}]});
		 
		 
		 userRes = ajaxFunction('POST','../GetUserServlet',false,null);
		 //alert("userRes data" +userRes);
		 userResData = Ext.util.JSON.decode(userRes);
		  // alert("pieBrokData.length = "+individualPieData.length);
		  // alert("pieBrkData = "+individualPieData);
		 userStore.removeAll(true);
		 userStore.loadData(userResData);

		 userStore.commitChanges(); 
		 Ext.getCmp('payee').setValue(userResData[0].userId);
		 Ext.getCmp('creditor').setValue(userResData[userResData.length-1].userId);
		 viewport2.render('viewPaymentcontent');
  });
	