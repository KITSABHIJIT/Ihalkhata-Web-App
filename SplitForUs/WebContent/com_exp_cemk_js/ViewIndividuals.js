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
			function renderDate(val, meta, record) {
				var myDate = val.split("-");
				var finalDate = myDate[2] + "/" + myDate[1] + "/" + myDate[0];
				meta.attr = 'ext:qtip="' + finalDate + '"';
				return finalDate;

			}
			function renderShow(val, meta, record) {
				meta.attr = 'ext:qtip="' + val + '"';
				return val;

			}
			function piePopUp(user, startMonth, finalYear, endMonth,
					endFinalYear, dateRangeDisplay, itemType) {

				// **************Grid JsonReader and
				// Store*************************************************************

				var result = "../GridSelectedExpenseServlet?userId=" + user
						+ "&month=" + startMonth + "&endmonth=" + endMonth
						+ "&year=" + finalYear + "&endYear=" + endFinalYear
						+ "&itemType=" + itemType;

				var gridchart = new Ext.data.JsonStore({
					root : 'items',
					totalProperty : 'totalCount',
					proxy : new Ext.data.HttpProxy({
						url : result,
						timeout : 300000
					}),
					fields : [ {
						name : 'date',
						mapping : 'date'
					}, {
						name : 'item',
						mapping : 'item'
					}, {
						name : 'desc',
						mapping : 'desc'
					}, {
						name : 'amount',
						mapping : 'amount',
						renderer:roundNumberGrid,
						type : 'float'
					} ]

				});
				
				var grids = new Ext.grid.GridPanel({
					store : gridchart,
					viewConfig:{emptyText: 'No Data available'},
					columns : [ {
						id : 'date',
						header : '<b>Date</b>',
						dataIndex : 'date',
						width : 80,
						sortable : true,
						renderer : renderDate
					}, {
						header : '<b>Items</b>',
						dataIndex : 'item',
						width : 100,
						sortable : true
					}, {
						header : '<b>Description</b>',
						dataIndex : 'desc',
						width : 100,
						sortable : true
					}, {
						header : '<b>Exp Amount (Rs.)</b>',
						width : 130,
						dataIndex : 'amount',
						renderer:roundNumberGrid,
						sortable : true
					} ],
					bbar : new Ext.PagingToolbar({
						pageSize : 10,
						store : gridchart,
						height : 25,
						width : 200,
						displayInfo : true,
						// plugins: new Ext.ux.ProgressBarPager(),
						// displayMsg: 'Displaying notifications {0} - {1} of
						// {2}',
						emptyMsg : "No data to display"
					}),

					stripeRows : true,
					frame : true,
					collapsible : false,
					collapsed : false,
					width : 440,
					height : 285,
					animCollapse : false,
					title : '',
					sortable : true,
					loadMask : true,
					iconCls : 'icon-grid'

				});

				gridchart.load.defer(20,gridchart,[{params:{start:0, limit:10}}]);
				// ***********************************End of Security JsonReader
				// and Store********************************

				var tabs = new Ext.Panel({
					region : 'center',
					margins : '1 1 1 0',
					activeTab : 0,
					defaults : {
						autoScroll : true
					},
					frame : true,
					items : [ {
						title : 'Expenditure Details on ' + itemType + ' of '
								+ dateRangeDisplay,
						layout : 'form',
						id : 'popupform',
						items : [ {
							xtype : 'panel',
							border : false,
							// layout:'column',

							items : [ {
								xtype : 'panel',
								title : '',
								collapsible : false,
								border : false,
								items : grids
							},

							{
								xtype : 'panel',
								border : false,
								layout : 'column',
								items : [ {
									columnWidth : '.41',
									border : false,
									height : 4
								}, {
									xtype : 'button',
									text : 'Close',
									columnWidth : .1,
									handler : function() {
										win.close();
									}
								} ]

							} ]
						} ]
					} ]
				});

				var win = new Ext.Window({
					title : 'Expenditure Popup',
					closable : true,
					width : 460,
					height : 380,
					border : false,
					resizable : false,
					plain : true,
					layout : 'border',
					modal : true,
					items : [ tabs ]
				});

				win.show(this);

			}

			var individualRes = '[{ }]';
			var individualData = Ext.util.JSON.decode(individualRes);

			var individualReader = new Ext.data.JsonReader([ {
				name : 'type',
				mapping : 'type'
			}, {
				name : 'amount',
				type : 'float',
				mapping : 'amount'
			}

			]);

			var userRes = '[{ }]';
			var userResData = Ext.util.JSON.decode(userRes);
			var userResReader = new Ext.data.JsonReader({}, [ {
				name : 'userName',
				mapping : 'userName'
			}, {
				name : 'userId',
				mapping : 'userId'
			}

			]);

			var userStore = new Ext.data.Store({
				fields : [ 'userName', 'userId' ],
				data : userResData,
				reader : userResReader
			});

			userRes = ajaxFunction('POST', '../GetUserServlet', false, 'user=loginUser');
			// alert("response data" +individualPieRes);
			userResData = Ext.util.JSON.decode(userRes);

			var user = userResData[0].userId;
			var dateRange = '0';
			var startMonth=getStartMonth(dateRange);
			var endMonth=getEndMonth(dateRange);
			var finalYear=getStartYear(dateRange);
			var endFinalYear=getEndYear(dateRange);
			var parameter = "user="+user+"&month=" + startMonth
					+ "&endmonth=" + endMonth + "&year=" + finalYear
					+ "&endYear=" + endFinalYear;
			var individualPieRes = ajaxFunction('POST',
					'../GridIndividualPieServlet', false, parameter);
			var individualPieData = Ext.util.JSON.decode(individualPieRes);

			var userCategory = "";
			for ( var i = 0; i < individualPieData.length; i++) {
				if (userCategory === "")
					userCategory = "'" + individualPieData[i].type + "'";
				else
					userCategory = userCategory + ",'"
							+ individualPieData[i].type + "'";
			}
			tempUserCategory = "[" + userCategory + "]";

			finalUserCategory = Ext.util.JSON.decode(tempUserCategory);

			// high pie chart.......

			var IndividualHighPiechart = new Ext.ux.HighChart(
					{
						titleCollapse : true,
						layout : 'fit',
						width : '60%',
						//height : 410,
						border : true,
						id : 'highPie',
						chartConfig : {
							chart : {
								id : 'highPie',
								plotBackgroundColor : null,
								plotBorderWidth : null,
								plotShadow : false,
								emptyText: 'No Debt Notifications available'
							},
							title : {
								text : 'Current Month Expense Details of all users'
							},

							tooltip : {
								formatter : function() {
									return 'Category:<b>'
											+ this.point.name
											+ '</b><br>Expense:<b>Rs.'
											+ parseFloat(Math.round(this.y * 100) / 100).toFixed(2)
											+ '</b><br>Per:<b>'
											+ roundNumber(this.percentage, 2)
											+ ' %</b><br><b>Click for Details</b>';
								}
							},
							legend : {
								layout : 'vertical',
								align : 'right',
								// verticalAlign: 'top',
								// x: -10,
								// y: 50,
								borderWidth : 0
							},
							plotOptions : {
								pie : {
									// allowPointSelect: true,
									cursor : 'pointer',
									point : {
										events : {
											click : function() {
												// console.log(this);
												// alert('Name: '+ this.name +',
												// value: '+ this.y);
												var dateRange = Ext
														.getCmp('dateRange').value;
												var dateRangeDisplay = Ext
														.getCmp('dateRange')
														.getRawValue();
												var user = Ext.getCmp('user').value;

												var startMonth=getStartMonth(dateRange);
												 var endMonth=getEndMonth(dateRange);
												 var finalYear=getStartYear(dateRange);
												 var endFinalYear=getEndYear(dateRange);
												var result = "../GridIndividualExpenseServlet?userId="
														+ user
														+ "&month="
														+ startMonth
														+ "&endmonth="
														+ endMonth
														+ "&year="
														+ finalYear
														+ "&endYear="
														+ endFinalYear;
												piePopUp(user, startMonth,
														finalYear, endMonth,
														endFinalYear,
														dateRangeDisplay,
														this.name);
											}
										}
									},

									dataLabels : {
										enabled : false
									},
									showInLegend : true
								}
							},
							series : []
						}
					});

			var individualPieReader = new Ext.data.JsonReader({}, [ {
				name : 'type',
				mapping : 'type'
			}, {
				name : 'amount',
				type : 'float',
				mapping : 'amount'
			}

			]);

			var individualPiechart = new Ext.data.Store({
				fields : [ 'type', 'amount' ],
				data : individualPieData,
				reader : individualPieReader
			});

			var individualLineRes = '[{ }]';
			var individualLineData = Ext.util.JSON.decode(individualLineRes);
			var individualLineReader = new Ext.data.JsonReader({}, [ {
				name : 'date',
				mapping : 'date'
			}, {
				name : 'amount',
				type : 'float',
				mapping : 'amount'
			}

			]);

			var individualLinechart = new Ext.data.Store({
				fields : [ 'date', 'amount' ],
				data : individualLineData,
				reader : individualLineReader
			});

			// added for pagination.....

			// manually load local data

			var result = "../GridIndividualExpenseServlet?userId=null&month=null&endmonth=null&year=null";
			var individualchart = new Ext.data.JsonStore({
				root : 'items',
				totalProperty : 'totalCount',

				// idProperty: 'threadid',
				// remoteSort: true,
				proxy : new Ext.data.HttpProxy({
					url : result,
					timeout : 300000
				}),
				// url: '../GridindividualServlet',
				fields : [   {name    : 'date',mapping : 'date', type: 'date', dateFormat: 'Y-m-d'}, {
					name : 'item',
					mapping : 'item'
				}, {
					name : 'desc',
					mapping : 'desc'
				}, {
					name : 'amount',
					mapping : 'amount',
					type : 'float'
				} ]

			});


			var columnChart = new Ext.ux.HighChart({
				titleCollapse : true,
				layout : 'fit',
				height : 420,
				width : '50%',
				border : true,
				id : 'highColumn',
				chartConfig : {
					chart : {
						id : 'highColumn',
						defaultSeriesType : 'column',
						margin : [ 50, 150, 60, 80 ]
					},
					title : {
						text : 'Current Month Expense of All Users'
					},
					xAxis : {
						categories : finalUserCategory,
						labels : {
							enabled : false
						}

					},
					yAxis : {
						min : 0,
						title : {
							text : 'Expense(Rs)'
						}
					},
					legend : {
						enabled : false
					},
					tooltip : {
						formatter : function() {
							return 'Category: <b>' + this.x
									+ '</b><br>Expense: <b>Rs.'
									+ parseFloat(Math.round(this.y * 100) / 100).toFixed(2) + '</b>';
						}
					},
					plotOptions : {
						/*
						 * series: { cursor: 'pointer', point: {events: {click:
						 * function() {console.log(this); alert('Category: '+
						 * this.category +', value: '+ this.y + 'Series: ' +
						 * this.series.name);}}}
						 *  },
						 */
						column : {
							pointPadding : 0.2,
							borderWidth : 0
						}
					},

					series : []

				}
			});

			/*
			 * var columnChart = new Ext.chart.ColumnChart({ store:
			 * individualPiechart, //url:'../ext-3.0-rc1/resources/charts.swf',
			 * xField: 'type', yField: 'amount' , height:500, width:500 });
			 */
			/*
			 * var lineChart = new Ext.chart.LineChart({ store:
			 * individualLinechart, xField: 'date', yField: 'amount',
			 * height:500, width:500 });
			 */

			var lineChart = new Ext.ux.HighChart(
					{
						titleCollapse : true,
						layout : 'fit',
						height : 420,
						width : '50%',
						border : true,
						id : 'highLine',
						chartConfig : {
							chart : {
								id : 'highLine',
								// defaultSeriesType: 'sline',
								margin : [ 50, 150, 60, 80 ]
							},
							title : {
								text : 'Current Month Expense of All Users'
							},
							xAxis : {
								type : 'datetime',
								labels : {
									style : {
										color : '#FFFFFF'
									},
									formatter : function() {
										// return this.value +'°C';
										return Highcharts.dateFormat('%e. %b',
												this.value);
									}

								},
								title : {
									text : 'Days',
									style : {
										color : '#FFFFFF'
									}
								}

							// categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May',
							// 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
							},
							yAxis : {
								min : 0,
								title : {
									text : 'Expense(Rs)'
								}
							},

							tooltip : {
								formatter : function() {
									// return ''+ Highcharts.dateFormat('%e. %b
									// %Y, %H:%M:%S', this.x) +': '+ this.y
									// +(this.series.name == 'Rainfall' ? ' mm'
									// : '°C');
									// return ''+this.x +' : '+ this.y
									// +(this.series.name == 'Rainfall' ? ' mm'
									// : '°C');
									if (Highcharts.dateFormat('%e', this.x) === "1")
										return 'Expense: <b>Rs.'
												+ parseFloat(Math.round(this.y * 100) / 100).toFixed(2)
												+ '</b><br>on <b>'
												+ Highcharts.dateFormat(
														'%est %b %Y', this.x)
												+ '</b>';
									else if (Highcharts
											.dateFormat('%e', this.x) === "2")
										return 'Expense: Rs.'
												+ parseFloat(Math.round(this.y * 100) / 100).toFixed(2)
												+ '</b><br>on <b>'
												+ Highcharts.dateFormat(
														'%end %b %Y', this.x)
												+ '</b>';
									else if (Highcharts
											.dateFormat('%e', this.x) === "3")
										return 'Expense: <b>Rs.'
												+ parseFloat(Math.round(this.y * 100) / 100).toFixed(2)
												+ '</b><br>on <b>'
												+ Highcharts.dateFormat(
														'%erd %b %Y', this.x)
												+ '</b>';
									else
										return 'Expense: <b>Rs.'
												+ parseFloat(Math.round(this.y * 100) / 100).toFixed(2)
												+ '</b><br>on <b>'
												+ Highcharts.dateFormat(
														'%eth %b %Y', this.x)
												+ '</b>';
								}
							},
							plotOptions : {
								spline : {
									lineWidth : 4,
									states : {
										hover : {
											lineWidth : 5
										}
									},
									marker : {
										enabled : false,
										states : {
											hover : {
												enabled : true,
												symbol : 'circle',
												radius : 5,
												lineWidth : 1
											}
										}
									}
								/*
								 * , pointInterval: 3600000, // one hour
								 * pointStart: Date.UTC(2009, 9, 6, 0, 0, 0)
								 */
								}
							},

							series : []

						}
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
			// create the Grid
			var gridtwo = new Ext.grid.GridPanel({
				store : individualchart,
				id : 'expenseGrid',
				height : 395,
				tbar: tb,
				loadMask : true,
				width : '100%',
				viewConfig:{forceFit:true,emptyText: 'No Expenses available'},
				columns : [ {
					id:'date',
					header : '<b>Date</b>',
					dataIndex : 'date',
					sortable : true,
					renderer: Ext.util.Format.dateRenderer('d/m/Y')            },
					{

					header : '<b>Items</b>',
					dataIndex : 'item',
					sortable : true
				}, {

					header : '<b>Description</b>',
					dataIndex : 'desc',
					sortable : true
				}, {
					header : '<b>Exp Amount (Rs.)</b>',
					dataIndex : 'amount',
					renderer:roundNumberGrid,
					sortable : true
				}

				],

				bbar : new Ext.PagingToolbar({
					pageSize : 15,
					store : individualchart,
					// height: 30,
					displayInfo : true,
					//plugins : new Ext.ux.ProgressBarPager(),
					// displayMsg: 'Displaying notifications {0} - {1} of {2}',
					emptyMsg : "Loading..."
				})
			});


			// gridtwo.render('viewData123');
			var viewport2 = new Ext.form.FormPanel(
					{
						layout : 'form',
						id : 'Myform',
						width : '100%',
						height : 460,
						url : '',

						items : [ {
							xtye : 'panel',
							width : '100%',
							autoHight : true,
							standardSubmit : true,
							border : false,
							region : 'center',
							margins : '135 0 0 0',
							items : [ new Ext.TabPanel(
									{
										id : 'content-panel',
										width : '100%',
										autoHight : true,
										id : 'Tabs',
										activeTab : 0,
										style : 'padding:0 0 0 0',
										items : [

												{
													title : 'Expenditure Records of Individuals ',
													layout : 'form',
													autoHeight : true,
													width : '100%',
													// border:false,
													items : [ {
														xtype : 'panel',
														border : false,
														autoHeight : true,
														width : '100%',
														style : 'padding:10px 0px 0px 0px',
														layout:'column',
														items : [
																	IndividualHighPiechart,
																	{
																		xtype : 'panel',
																		border : false,
																		autoHeight : true,
																		width : '40%',
																		// layout:'column',
																		items : [
																				{
																					xtype : 'panel',
																					border : false,
																					autoHeight : true,
																					hidden:true,
																					width : '100%',
																					style : 'padding:0px 0px 5px 0px',
																					layout : 'column',
																					items : [
																							{
																								columnWidth : '.3',
																								xtype : 'displayfield',
																								style : 'font-size:14px',
																								value : 'Select a User:'
																							},
																							new Ext.form.ComboBox(
																									{
																										id : 'user',
																										name : 'dimension',
																										hideTrigger : false,
																										store : userStore,
																										valueField : 'userId',
																										displayField : 'userName',
																										typeAhead : true,
																										mode : 'local',
																										columnWidth : '.25',
																										triggerAction : 'all',
																										selectOnFocus : true,
																										listeners : {
																											'select' : function(
																													key) {
																												var user = key.value;
																												var dateRange = Ext
																														.getCmp('dateRange').value;

																						    					 var startMonth=getStartMonth(dateRange);
																							    				 var endMonth=getEndMonth(dateRange);
																								    			 var finalYear=getStartYear(dateRange);
																									    		 var endFinalYear=getEndYear(dateRange);
																												var result = "../GridIndividualExpenseServlet?userId="
																														+ user
																														+ "&month="
																														+ startMonth
																														+ "&endmonth="
																														+ endMonth
																														+ "&year="
																														+ finalYear
																														+ "&endYear="
																														+ endFinalYear;
																												individualchart.proxy
																														.setUrl(
																																result,
																																true);
																												individualchart
																														.reload();

																												var parameter = "user="
																														+ user
																														+ "&month="
																														+ startMonth
																														+ "&endmonth="
																														+ endMonth
																														+ "&year="
																														+ finalYear
																														+ "&endYear="
																														+ endFinalYear;
																												individualPieRes = ajaxFunction('POST',
																														'../GridIndividualPieServlet',
																														false,
																														parameter);
																												
																												individualPieData = Ext.util.JSON.decode(individualPieRes);
																												
																												var userPieData = "";
																												for ( var i = 0; i < individualPieData.length; i++) {
																													if (userPieData === "")
																														userPieData = "['"
																																+ individualPieData[i].type
																																+ "',"
																																+ individualPieData[i].amount
																																+ "]";
																													else
																														userPieData = userPieData
																																+ ",['"
																																+ individualPieData[i].type
																																+ "',"
																																+ individualPieData[i].amount
																																+ "]";

																												}
																												userPieData = "[{type: 'pie',name: 'Expense',data:["
																														+ userPieData
																														+ "]}]";
																												userPieData = Ext.util.JSON.decode(userPieData);
																												Ext.getCmp('highPie').chartConfig.series = userPieData;
																												if(isEmpty(individualPieRes) || individualPieRes=='[]')
																													Ext.getCmp('highPie').setTitle('<br/><br/><br/>Sorry<br/>No Data Available for<br/>'+Ext.getCmp('dateRange').getRawValue()+ ' Expense Details of '+ Ext.getCmp('user').getRawValue());		
																												else
																													Ext.getCmp('highPie').setTitle(Ext.getCmp('dateRange').getRawValue()+ ' Expense Details of '+ Ext.getCmp('user').getRawValue());
																												

																												/*
																												 * individualPiechart.removeAll(true);
																												 * individualPiechart.loadData(individualPieData);
																												 * individualPiechart.commitChanges();
																												 */
																												var userCategoryData = "";

																												for ( var i = 0; i < individualPieData.length; i++) {
																													if (userCategoryData === "")
																														userCategoryData = individualPieData[i].amount;
																													else
																														userCategoryData = userCategoryData
																																+ ","
																																+ individualPieData[i].amount;

																												}
																												userCategoryData = "[{ name: 'Expense',data: ["
																														+ userCategoryData
																														+ "]}]";
																												userCategoryData = Ext.util.JSON
																														.decode(userCategoryData);
																												Ext.getCmp('highColumn').chartConfig.series = userCategoryData;
																												if(isEmpty(individualPieRes) || individualPieRes=='[]')
																													Ext.getCmp('highColumn').setTitle('<br/><br/><br/>Sorry<br/>No Data Available for<br/>'+Ext.getCmp('dateRange').getRawValue()+ ' Expense Details of '+ Ext.getCmp('user').getRawValue());		
																												else
																													Ext.getCmp('highColumn').setTitle(Ext.getCmp('dateRange').getRawValue()+ ' Expense Details of '+ Ext.getCmp('user').getRawValue());
																											
																												individualLineRes = ajaxFunction(
																														'POST',
																														'../GridIndividualLineServlet',
																														false,
																														parameter);
																												individualLineData = Ext.util.JSON
																														.decode(individualLineRes);
																												var userLineCategoryData = "";

																												for ( var i = 0; i < individualLineData.length; i++) {
																													if (userLineCategoryData === "")
																														userLineCategoryData = "["
																																+ individualLineData[i].date
																																+ ","
																																+ individualLineData[i].amount
																																+ "]";
																													else
																														userLineCategoryData = userLineCategoryData
																																+ ",["
																																+ individualLineData[i].date
																																+ ","
																																+ individualLineData[i].amount
																																+ "]";
																												}
																												userLineCategoryData = "[{ name: 'Expense',type :'spline' ,data: ["
																														+ userLineCategoryData
																														+ "]}]";
																												userLineCategoryData = Ext.util.JSON
																														.decode(userLineCategoryData);
																												Ext
																														.getCmp('highLine').chartConfig.series = userLineCategoryData;
																												if(isEmpty(individualLineRes) || individualLineRes=='[]')
																													Ext.getCmp('highLine').setTitle('<br/><br/><br/>Sorry<br/>No Data Available for<br/>'+Ext.getCmp('dateRange').getRawValue()+ ' Expense Details of '+ Ext.getCmp('user').getRawValue());		
																												else
																													Ext.getCmp('highLine').setTitle(Ext.getCmp('dateRange').getRawValue()+ ' Expense Details of '+ Ext.getCmp('user').getRawValue());
																											
																												/*
																												 * individualLinechart.removeAll(true);
																												 * individualLinechart.loadData(individualLineData);
																												 * individualLinechart.commitChanges();
																												 */

																											}
																										}

																									})

																					]
																				},
																				{
																					xtype : 'panel',
																					border : false,
																					autoHeight : true,
																					width : '100%',
																					layout : 'column',
																					style : 'padding:0px 0px 5px 0px',
																					items : [
																							{
																								columnWidth : '.25',
																								xtype : 'displayfield',
																								style : 'font-size:14px',
																								value : 'Select a Period:'
																							},
																							new Ext.form.ComboBox(
																									{
																										id : 'dateRange',
																										name : 'dimension',
																										hideTrigger : false,
																										store : new Ext.data.ArrayStore(
																												{
																													fields : [
																															'abbr',
																															'state' ],
																													data : [
																															[
																																	'0',
																																	'Current Month' ],
																															[
																																	'1',
																																	'Last Month' ],
																															[
																																	'2',
																																	'Last Quarter' ],
																															[
																																	'3',
																																	'Last Year' ] ]
																												}),
																										valueField : 'abbr',
																										displayField : 'state',
																										typeAhead : true,
																										mode : 'local',
																										editable:false,
																										columnWidth : '.25',
																										triggerAction : 'all',
																										selectOnFocus : true,
																										listeners : {
																											'select' : function(
																													key) {
																												var dateRange = key.value;
																												var user = Ext
																														.getCmp('user').value;

																												var startMonth=getStartMonth(dateRange);
																												 var endMonth=getEndMonth(dateRange);
																												 var finalYear=getStartYear(dateRange);
																												 var endFinalYear=getEndYear(dateRange);
																												var result = "../GridIndividualExpenseServlet?userId="
																														+ user
																														+ "&month="
																														+ startMonth
																														+ "&endmonth="
																														+ endMonth
																														+ "&year="
																														+ finalYear
																														+ "&endYear="
																														+ endFinalYear;
																												individualchart.proxy
																														.setUrl(
																																result,
																																true);
																												individualchart
																														.reload();

																												var parameter = "user="
																														+ user
																														+ "&month="
																														+ startMonth
																														+ "&endmonth="
																														+ endMonth
																														+ "&year="
																														+ finalYear
																														+ "&endYear="
																														+ endFinalYear;
																												individualPieRes = ajaxFunction(
																														'POST',
																														'../GridIndividualPieServlet',
																														false,
																														parameter);
																												individualPieData = Ext.util.JSON
																														.decode(individualPieRes);

																												var userPieData = "";
																												for ( var i = 0; i < individualPieData.length; i++) {
																													if (userPieData === "")
																														userPieData = "['"
																																+ individualPieData[i].type
																																+ "',"
																																+ individualPieData[i].amount
																																+ "]";
																													else
																														userPieData = userPieData
																																+ ",['"
																																+ individualPieData[i].type
																																+ "',"
																																+ individualPieData[i].amount
																																+ "]";

																												}
																												userPieData = "[{type: 'pie',name: 'Expense',data:["
																														+ userPieData
																														+ "]}]";
																												userPieData = Ext.util.JSON
																														.decode(userPieData);
																												Ext
																														.getCmp('highPie').chartConfig.series = userPieData;
																												if(isEmpty(individualPieRes) || individualPieRes=='[]')
																													Ext.getCmp('highPie').setTitle('<br/><br/><br/>Sorry<br/>No Expense Data Available for <br/>'+Ext.getCmp('dateRange').getRawValue()+ ' for '+ Ext.getCmp('user').getRawValue());		
																												else
																													Ext.getCmp('highPie').setTitle(Ext.getCmp('dateRange').getRawValue()+ ' Expense Details of '+ Ext.getCmp('user').getRawValue());

																												/*
																												 * individualPiechart.removeAll(true);
																												 * individualPiechart.loadData(individualPieData);
																												 * individualPiechart.commitChanges();
																												 */
																												var userCategoryData = "";

																												for ( var i = 0; i < individualPieData.length; i++) {
																													if (userCategoryData === "")
																														userCategoryData = individualPieData[i].amount;
																													else
																														userCategoryData = userCategoryData
																																+ ","
																																+ individualPieData[i].amount;

																												}
																												userCategoryData = "[{ name: 'Expense',data: ["
																														+ userCategoryData
																														+ "]}]";
																												userCategoryData = Ext.util.JSON
																														.decode(userCategoryData);
																												Ext
																														.getCmp('highColumn').chartConfig.series = userCategoryData;
																												if(isEmpty(individualPieRes) || individualPieRes=='[]')
																													Ext.getCmp('highColumn').setTitle('<br/><br/><br/>Sorry<br/>No Expense Data Available for <br/>'+Ext.getCmp('dateRange').getRawValue()+ ' for '+ Ext.getCmp('user').getRawValue());		
																												else
																													Ext.getCmp('highColumn').setTitle(Ext.getCmp('dateRange').getRawValue()+ ' Expense Details of '+ Ext.getCmp('user').getRawValue());

																												individualLineRes = ajaxFunction(
																														'POST',
																														'../GridIndividualLineServlet',
																														false,
																														parameter);
																												individualLineData = Ext.util.JSON
																														.decode(individualLineRes);
																												var userLineCategoryData = "";

																												for ( var i = 0; i < individualLineData.length; i++) {
																													if (userLineCategoryData === "")
																														userLineCategoryData = "["
																																+ individualLineData[i].date
																																+ ","
																																+ individualLineData[i].amount
																																+ "]";
																													else
																														userLineCategoryData = userLineCategoryData
																																+ ",["
																																+ individualLineData[i].date
																																+ ","
																																+ individualLineData[i].amount
																																+ "]";
																												}
																												userLineCategoryData = "[{ name: 'Expense',type :'spline' ,data: ["
																														+ userLineCategoryData
																														+ "]}]";
																												userLineCategoryData = Ext.util.JSON
																														.decode(userLineCategoryData);
																												Ext
																														.getCmp('highLine').chartConfig.series = userLineCategoryData;
																												if(isEmpty(individualLineRes) || individualLineRes=='[]')
																													Ext.getCmp('highLine').setTitle('<br/><br/><br/>Sorry<br/>No Expense Data Available for <br/>'+Ext.getCmp('dateRange').getRawValue()+ ' for '+ Ext.getCmp('user').getRawValue());		
																												else
																													Ext.getCmp('highLine').setTitle(Ext.getCmp('dateRange').getRawValue()+ ' Expense Details of '+ Ext.getCmp('user').getRawValue());

																												/*
																												 * individualLinechart.removeAll(true);
																												 * individualLinechart.loadData(individualLineData);
																												 * individualLinechart.commitChanges();
																												 */

																												  var parameter="month="+startMonth+"&endmonth="+endMonth+"&year="+finalYear+"&endYear="+endFinalYear+"&type=1&data=1";
																												  ExpensePieRes = ajaxFunction('POST','../GridPieServlet',false,parameter);
																												  ExpensePieData = Ext.util.JSON.decode(ExpensePieRes);
																												  if(!isEmpty(ExpensePieRes) && ExpensePieRes!=="[]"){
																													  ExpensePieData = Ext.util.JSON.decode(ExpensePieRes);
																													  Ext.getCmp('expenseTotal').setValue('<b>Rs.'+ExpensePieData[0].amount+'</b>');
																												  }else
																													  Ext.getCmp('expenseTotal').setValue('<b>Rs.0.00</b>');
																												
																												
																												
																											}
																										}

																									}),{
																								columnWidth : '.2',
																								xtype : 'displayfield',
																								value : 'Expense:',
																								style: {
																				                "margin-left": "20px",
																				                "font-size":"14px"
																				            }
																							},{
																								columnWidth : '.25',
																								xtype : 'displayfield',
																								style : 'font-size:14px',
																								value : '0',
																								id:'expenseTotal'
																							}

																					]
																				},
																				gridtwo ]
																	}]
													}

													]
												},
												{
													title : 'Expenditure Graphs of Individuals ',
													layout : 'form',
													autoHeight : true,
													width : '100%',
													// border:false,
													style : 'padding:10px 10px 10px 10px',
													items : [ {
														xtype : 'panel',
														border : false,
														autoHeight : true,
														width : '100%',
														layout : 'column',
														items : [ columnChart,
																lineChart ]
													}

													]
												} ]
									})

							]
						} ]
					});

			// alert("pieBrokData.length = "+individualPieData.length);
			// alert("pieBrkData = "+individualPieData);
			userStore.removeAll(true);
			userStore.loadData(userResData);

			userStore.commitChanges();

			Ext.getCmp('user').setValue(userResData[0].userId);
			Ext.getCmp('dateRange').setValue('0');

			var result = "../GridIndividualExpenseServlet?userId=" + user
					+ "&month=" + startMonth + "&endmonth=" + endMonth
					+ "&year=" + finalYear + "&endYear=" + endFinalYear;
			individualchart.proxy.setUrl(result, true);
			individualchart.load.defer(20,individualchart,[{params:{start:0, limit:15}}]);
			
			var userPieData = "";
			for ( var i = 0; i < individualPieData.length; i++) {
				if (userPieData === "")
					userPieData = "['" + individualPieData[i].type + "',"
							+ individualPieData[i].amount + "]";
				else
					userPieData = userPieData + ",['"
							+ individualPieData[i].type + "',"
							+ individualPieData[i].amount + "]";

			}
			userPieData = "[{type: 'pie',name: 'Expense',data:[" + userPieData
					+ "]}]";
			userPieData = Ext.util.JSON.decode(userPieData);
			Ext.getCmp('highPie').chartConfig.series = userPieData;
			if(isEmpty(individualPieRes) || individualPieRes=='[]')
				Ext.getCmp('highPie').setTitle('<br/><br/><br/>Sorry<br/>No Expense Data Available for<br/>Current Month for '+ userResData[0].userName);
			else
				Ext.getCmp('highPie').setTitle('Current Month Expense Details of '+ userResData[0].userName);

			var userCategoryData = "";

			for ( var i = 0; i < individualPieData.length; i++) {
				if (userCategoryData === "")
					userCategoryData = individualPieData[i].amount;
				else
					userCategoryData = userCategoryData + ","
							+ individualPieData[i].amount;

			}
			userCategoryData = "[{ name: 'Expense',data: [" + userCategoryData
					+ "]}]";
			userCategoryData = Ext.util.JSON.decode(userCategoryData);
			Ext.getCmp('highColumn').chartConfig.series = userCategoryData;
			if(isEmpty(individualPieRes) || individualPieRes=='[]')
				Ext.getCmp('highColumn').setTitle('<br/><br/><br/>Sorry<br/>No Expense Data Available for<br/>Current Month for '+ userResData[0].userName);
			else
				Ext.getCmp('highColumn').setTitle('Current Month Expense Details of '+ userResData[0].userName);
						/*
			 * individualPiechart.removeAll(true);
			 * individualPiechart.loadData(individualPieData);
			 * individualPiechart.commitChanges();
			 */

			individualLineRes = ajaxFunction('POST',
					'../GridIndividualLineServlet', false, parameter);
			individualLineData = Ext.util.JSON.decode(individualLineRes);

			var userLineCategoryData = "";

			for ( var i = 0; i < individualLineData.length; i++) {
				if (userLineCategoryData === "")
					userLineCategoryData = "[" + individualLineData[i].date
							+ "," + individualLineData[i].amount + "]";
				else
					userLineCategoryData = userLineCategoryData + ",["
							+ individualLineData[i].date + ","
							+ individualLineData[i].amount + "]";
			}
			userLineCategoryData = "[{ name: 'Expense',data: ["
					+ userLineCategoryData + "]}]";
			userLineCategoryData = Ext.util.JSON.decode(userLineCategoryData);
			Ext.getCmp('highLine').chartConfig.series = userLineCategoryData;
			if(isEmpty(individualLineData) || individualLineData=='[]')
				Ext.getCmp('highLine').setTitle('<br/><br/><br/>Sorry<br/>No Expense Data Available for<br/>Current Month for '+ userResData[0].userName);
			else
				Ext.getCmp('highLine').setTitle('Current Month Expense Details of '+ userResData[0].userName);

			 var parameter="month="+startMonth+"&endmonth="+endMonth+"&year="+finalYear+"&endYear="+endFinalYear+"&type=1&data=1";
			  ExpensePieRes = ajaxFunction('POST','../GridPieServlet',false,parameter);
			  if(!isEmpty(ExpensePieRes) && ExpensePieRes!=="[]"){
				  ExpensePieData = Ext.util.JSON.decode(ExpensePieRes);
				  Ext.getCmp('expenseTotal').setValue('<b>Rs.'+ExpensePieData[0].amount+'</b>');
			  }else
				  Ext.getCmp('expenseTotal').setValue('<b>Rs.0.00</b>');
			viewport2.render('viewIndividualcontent');
		});
