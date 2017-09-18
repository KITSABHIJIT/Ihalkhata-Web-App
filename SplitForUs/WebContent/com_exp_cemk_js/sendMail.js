Ext.BLANK_IMAGE_URL = '../com_exp_cemk_css/images/default/s.gif';
Ext.ns('Example');
Example.version = '1.0';

Ext.override(Ext.ux.form.LovCombo, {
	beforeBlur : Ext.emptyFn
});
Ext
		.onReady(function() {

			Ext.QuickTips.init();
			Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

			function confirmCheck(user,mailId){
		var d=new Date();
		var date =d.getDate()+"/"+(d.getMonth()+1)+"/"+d.getFullYear();
		var mailIdArr=mailId.split(",");
		var finalmailId="";
		for(var i=0;i<mailIdArr.length;i++){
			if(finalmailId=="")
				finalmailId=mailIdArr[i];
			else
				finalmailId=finalmailId+"<br/>"+mailIdArr[i];
		}
		
		
		var tabsPopUp = new Ext.Panel({
			 border:false,
				//height:380,
				autoHeight:true,
				width:'100%',
				style:'padding:10px 0px 0px 0px',
				layout:'column',
				items:[new Ext.ux.Image ({ 
												id: 'imgPreview',
												autoHeight:true,
												width:'40%',
												url: '../com_exp_cemk_css/images/email1.jpg' 
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
							   			   					value:'<font color="blue">Are you sure to send the <br/>email notification to</font>'
							   			   			   		}	   			   			   		
							   			   			   		]},
	{
		xtype:'panel',
		border:false,
		autoHeight:true,
		width:'100%',
		style:'padding:0px 0px 10px 0px',
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
							   			   			   			style:'padding:0px 0px 10px 0px',
							   			   			   			layout:'column',
							   			   			   			items:[ 
							   			   			   			   		{
							   			   			   			   			width:'100%',
							   			   			   						xtype:'displayfield',
							   			   			   						style:'font-size:14px',
							   			   			   						value:'<b>'+user+'</b>'
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
												   			   			   			   			width:'100%',
												   			   			   						xtype:'displayfield',
												   			   			   						style:'font-size:14px',
												   			   			   						value:'<font color="blue">at</font>'
												   			   			   				   		}]},
		   			   			   			   		
												       
													   			   			   				 {
																	   			   			   			xtype:'panel',
																	   			   			   			border:false,
																	   			   			   			autoHeight:true,
																	   			   			   			style:'padding:0px 0px 10px 0px',
																	   			   			   			width:'100%',
																	   			   			   			layout:'column',
																	   			   			   			items:[ 
																	   			   			   			   		{
																	   			   			   			   			width:'100%',
																	   			   			   						xtype:'displayfield',
																	   			   			   						style:'font-size:14px',
																	   			   			   						value:'<b>'+finalmailId+'</b>'
																	   			   			   				   		}]}]

					} 
					
					],buttons:[{
						xtype:'button',
						text:'Send E-Mail',
						columnWidth: .05,
						 handler: function(){
						win.close();
						formSubmit();
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
		function formSubmit(){	
			Ext.getCmp('Myform').getForm().submit(
																												{
																													reset : false,
																													waitMsg : 'Sending Email...',
																													timeout : 4000,
																													params : {
																														user : document
																																.getElementById('userArray').value
																													},
																													success : function(
																															form,
																															action)

																													{

																														Ext.Msg
																																.alert(
																																		'Success',
																																		action.result.res);

																													},

																													failure : function(
																															form,
																															action)

																													{

																														Ext.Msg
																																.alert(
																																		'Failure',
																																		action.result.res);

																													}
																												});}	
			
			var userRes = '[{ }]';
			var userResData = Ext.util.JSON.decode(userRes);
			var userResReader = new Ext.data.JsonReader({}, [ {
				name : 'userName',
				mapping : 'userName'
			}, {
				name : 'email',
				mapping : 'email'
			}

			]);

			var userStore = new Ext.data.Store({
				fields : [ 'userName', 'email' ],
				data : userResData,
				reader : userResReader
			});

			var PaymentRes = '[{ }]';
			var PaymentData = Ext.util.JSON.decode(PaymentRes);

			var PaymentReader = new Ext.data.JsonReader([ {
				name : 'borrower',
				mapping : 'borrower'
			}, {
				name : 'relation',
				mapping : 'relation'
			}, {
				name : 'from',
				mapping : 'from'
			}, {
				name : 'payee',
				mapping : 'payee'
			}, {
				name : 'amount',
				mapping : 'amount'
			}

			]);

			var Paymentchart = new Ext.data.JsonStore({
				root : 'items',
				totalProperty : 'totalCount',

				// idProperty: 'threadid',
				// remoteSort: true,
				proxy : new Ext.data.HttpProxy({
					url : '../GridPaymentServlet',
					timeout : 300000
				}),
				// url: '../GridPaymentServlet',
				fields : [

				{
					name : 'borrower',
					mapping : 'borrower'
				}, {
					name : 'relation',
					mapping : 'relation'
				}, {
					name : 'from',
					mapping : 'from'
				}, {
					name : 'payee',
					mapping : 'payee'
				}, {
					name : 'amount',
					mapping : 'amount'
				}

				]

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
			var gridone = new Ext.grid.GridPanel({
		         store: Paymentchart,
		         height: 405,
		         tbar: tb,
		         forceFit:true,
		         trackMouseOver:false,
		         disableSelection:true,
		         loadMask: true,
		         width:'100%',
		         columns: [
		             
		             {
		                         header   : '<b>Payee</b>', 
		                         dataIndex: 'payee',
		                         width:150,
		                         sortable : true         },
		                 {
		                     header   : '&nbsp;', 
		                     dataIndex: 'relation',
		                     width:80,
		                     sortable : true            },
		                 {
		                     header   : '<b>Amount (Rs.)</b>', 
		                     width:100,
		                     dataIndex: 'amount',
		                     sortable : true            },
		                 {
		                     header   : '&nbsp;',
		                     dataIndex: 'from',
		                     width:70,
		                     sortable : true            },
		                     {
		                         header   : '<b>Borrower</b>', 
		                         dataIndex: 'borrower',
		                         width:150,
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

			Paymentchart.load({
				params : {
					start : 0,
					limit : 10
				}
			});

			// gridtwo.render('viewData123');
			var viewport2 = new Ext.form.FormPanel(
					{
						layout : 'form',
						border : false,
						id : 'Myform',
						 width:'100%',
						 height:460,
						url : '../SendEmailServlet',

						items : [{
							 xtye:'panel',
							  width:'100%',
							 standardSubmit:true,
							 border:false,
							 items : [ new Ext.TabPanel(
									{
										id : 'content-panel',
										autoHeight: true,
							        	 width:'100%',
										// border:false,
										title : 'BCC Report',
										fieldlabel : 'BCC Report',
										// defaults:{autoScroll:true},
										id : 'Tabs',
										activeTab : 0,
										style : 'padding:0 0 0 0',
										items : [
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
																title:'Send Email Notifications',
																height: 430,
													        	width:'49.9%',
													        	items:[{
																	xtype:'panel',
																	border:false,
																	autoHeight: true,
														        	width:'100%',
														        	style : 'padding:30px 0px 0px 10px',
																	layout:'column',
																	items:[
																		{
																			columnWidth : '.25',
																			xtype : 'displayfield',
																			style : 'font-size:14px',
																			value : 'Select Users:'
																		},
																																			
																		new Ext.ux.form.LovCombo({
																			id:'user',
																			name:'dimension',
																			hideTrigger:false,
																			editable:false,
																			resizable:true,
																			store:userStore,
																			valueField:'email',
																			displayField:'userName',
																			typeAhead: true,
																			mode: 'local',
																			columnWidth: '.3',
																			triggerAction: 'all',
																			selectOnFocus:true
																			
																			
																			})]},new Ext.ux.Image ({ 
																				width : 300,
																				height : 300,
																				url: '../com_exp_cemk_css/images/email.jpg' 
																			 })
																       ],buttons: [{
																			xtype : 'button',
																			id : 'paid',
																			columnWidth : '.1',
																			value : 'Send Email',
																			text : 'Send Email',
																			listeners : {
																				'click' : function(
																						key) {
																					var user = Ext
																							.getCmp('user').value;
																					var userName = Ext
																					.getCmp('user').getRawValue();
																					if (user == undefined
																							|| user == null
																							|| user == "") {
																						Ext.Msg
																								.show({
																									title : 'Sorry!',
																									msg : 'Wrong Selection. Please Select a User.',
																									buttons : Ext.Msg.OK,
																									animEl : 'elId',
																									icon : Ext.MessageBox.ERROR
																								});

																					}

																					else {
																						document
																								.getElementById('userArray').value = user;
																						confirmCheck(userName,user);
																					

																					}
																				}
																			}
																		}]
														}

												]
											}

											]
										}

										]
									})

							]
						} ]
					});

			userRes = ajaxFunction('POST', '../GetUserServlet', false, null);
			// alert("response data" +individualPieRes);
			userResData = Ext.util.JSON.decode(userRes);
			// alert("pieBrokData.length = "+individualPieData.length);
			// alert("pieBrkData = "+individualPieData);
			userStore.removeAll(true);
			userStore.loadData(userResData);

			userStore.commitChanges();
			Ext.getCmp('user').setValue(userResData[0].userName);
			viewport2.render('viewEmailcontent');
		});
