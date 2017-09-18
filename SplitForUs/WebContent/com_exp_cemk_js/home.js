 Ext.onReady(function(){
    
        // NOTE: This is an example showing simple state management. During development,
        // it is generally best to disable state management as dynamically-generated ids
        // can change across page loads, leading to unpredictable results.  The developer
        // should ensure that stable state ids are set for stateful components in real apps.
        Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
        Ext.QuickTips.init();
        var creditFlag=false;
        var gainerLoser= ajaxFunction('POST','../GetHomePageDataServlet',false,"type=4");
        var gainerLoserData = Ext.util.JSON.decode(gainerLoser);
        
        
        var debtReader = new Ext.data.JsonReader({
            totalProperty: 'totalCount',
            root: 'data',
            fields:[{name:'debtNotification'}]});

    	var creditReader = new Ext.data.JsonReader({
            totalProperty: 'totalCount',
            root: 'data',
            fields:[{name:'creditNotification'}]});
    	
    	var debtStore = new Ext.data.GroupingStore({
            reader: debtReader,
            proxy : new Ext.data.HttpProxy({
    			method: 'POST',
    			timeout:3000000,
    			url: '../GetHomePageDataServlet'
        	}),
        	baseParams:{type:'1'}
            });
        
    	var creditStore = new Ext.data.GroupingStore({
            reader: creditReader,
            proxy : new Ext.data.HttpProxy({
    			method: 'POST',
    			timeout:3000000,
    			url: '../GetHomePageDataServlet'
    	}),
    	baseParams:{type:'2'}
        });
    	
    	

    	var debtGrid = new Ext.grid.GridPanel({
    		store : debtStore,
    		id:'debtGrid',
    		viewConfig:{forceFit:true,emptyText: 'No Debt Notifications available'},
    		columns : [{id:'date',header:'<center><b>Notifications</b></center>',dataIndex:'debtNotification',sortable:false}],
    		//width:'100%',
    		//autoHeight:true,
    		anchor:'100% 100%',
    		animCollapse : false,
    		loadMask : true, 
    		bbar: new Ext.PagingToolbar({
                pageSize: 17,          
                autoHeight:true,
                store: debtStore,
                displayInfo: true,
                plugins: new Ext.ux.ProgressBarPager(),
                emptyMsg: "No Debt to display"
            })


    	});
    	debtStore.load.defer(20,debtStore,[{params:{start:0, limit:17}}]);
    	//debtStore.load({params:{start:0, limit:17}});
    	var creditGrid= new Ext.grid.GridPanel({
    		store : creditStore,
    		id:'creditGrid',
    		viewConfig:{forceFit:true,emptyText: 'No Credit Notifications available'},
    		columns:[{id:'date1',header:'<center><b>Notifications</b></center>',dataIndex:'creditNotification',sortable:false}],
    		//width:'100%',
    		//autoHeight:true,
    		anchor:'100% 100%',
    		animCollapse : false,
    		loadMask : true, 
    		bbar: new Ext.PagingToolbar({
                pageSize: 17,          
                autoHeight:true,
                store: creditStore,
                displayInfo: true,
                plugins: new Ext.ux.ProgressBarPager(),
                emptyMsg: "No Credit to display"
            })


    	});
    	
    	//creditStore.load({params:{start:0, limit:17}});
    	function roundNumberGraph(num, dec) {
			 var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);;
				
				return result;
		
	 }
   	function loadHighChart(){
   		//*******Loading High Chart*************
   		var dataObjectString= ajaxFunction('POST','../GetHomePageDataServlet',false,"type=3");
           var deployData = Ext.util.JSON.decode(dataObjectString);
           var deployChartData="";
   			for(var i=0;i<deployData.length;i++){
   			  if(deployChartData===""){
   				deployChartData="["+deployData[i].date+","+deployData[i].amount+"]";
   			  }
   				else{
   					deployChartData=deployChartData+",["+deployData[i].date+","+deployData[i].amount+"]";
   				} 
   		}
   		deployData= "[{ name: 'Expense Line',type :'spline' ,data: ["+deployChartData+"]}" +
   				",{ name: 'Expense Bar',type :'column' ,data: ["+deployChartData+"]}]";
   		deployData = Ext.util.JSON.decode(deployData);
   		Ext.getCmp('lineChart').chartConfig.series=deployData; 
   		Ext.getCmp('lineChart').refresh();
   		
   	}
   	
   	var chartExample= new Ext.ux.HighChart({
            titleCollapse: true,
            //layout:'fit',
            //width:'100%',
           // height:350,
            anchor:'100% 100%',
            border: true,
            id: 'lineChart',
            chartConfig: {
                chart: {
                    id: 'lineChart',
                    zoomType: 'xy'
                   
                   	 
                },
                title: {
                    text: '<b>Expense Breakdown of last 30 days</b>',
                    style: {
                        margin: '10px 100px 0 0' // center it
                    }
                },
				xAxis: {
					type: 'datetime',
					labels: {
					style: {
						color: '#FFFFFF'
					},
					formatter: function() {
						//return this.value +'°C';
						return Highcharts.dateFormat('%e. %b', this.value) ;
					}
					
				},
				title: {
					text: '',
					style: {
					color: '#FFFFFF'
					}
				}

				
},
                yAxis: {
						 title: {
							text: ' '
							}, 
                    plotLines: [
                        {
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }
                    ]
                },
                tooltip: {
                    formatter: function() {
	 					if(Highcharts.dateFormat('%e', this.x)==='1' || Highcharts.dateFormat('%e', this.x)==='21' || Highcharts.dateFormat('%e', this.x)==='31')
	 						return 'Expense: Rs.<b>'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'</b><br>on <b>'+Highcharts.dateFormat('%est %b %Y', this.x)+'</b>';
	 					else if(Highcharts.dateFormat('%e', this.x)==='2' || Highcharts.dateFormat('%e', this.x)==='22')
		 					return 'Expense: Rs.<b>'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'</b><br>on <b>'+Highcharts.dateFormat('%end %b %Y', this.x)+'</b>';
	 					else if(Highcharts.dateFormat('%e', this.x)==='3'  || Highcharts.dateFormat('%e', this.x)==='23')
		 					return 'Expense: Rs.<b>'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'</b><br>on <b>'+Highcharts.dateFormat('%erd %b %Y', this.x)+'</b>';
	 					else
		 					return 'Expense: Rs.<b>'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'</b><br>on <b>'+Highcharts.dateFormat('%eth %b %Y', this.x)+'</b>';

                    }
                },
                
                series: []
            }
        });
   	loadHighChart();	
    	var viewport = new Ext.form.FormPanel({
            layout: 'border',
            width : '100%',
			height : 460,
            /*style: {
                "margin-left": "15px", // when you add custom margin in IE 6...
                "margin-right":"15px",
                "margin-bottom":"20px"
            },*/

            items: [
            // create instance immediately
            /* new Ext.BoxComponent({
                region: 'north',
                height: '1%', // give north and south regions a height
                autoEl: {
                    tag: 'div',	
                    html:'&nbsp;'
                }
            }),new Ext.BoxComponent({
                region: 'south',
                height: '1%', // give north and south regions a height
                autoEl: {
                    tag: 'div',
                    html:'&nbsp;'
                }
            }),*//*{
                // lazily created panel (xtype:'panel' is default)
                region: 'south',
                contentEl: 'south',
                split: true,
                height: 100,
                minSize: 100,
                maxSize: 200,
                collapsible: true,
                title: 'South',
                margins: '0 0 0 0'
            },*/ {
                region: 'east',
                title: 'Have a Look',
                collapsible: true,
                split: true,
                width: '20%', // give east and west regions a width
                minSize: 175,
                maxSize: 400,
                padding: '0px 0px 0px 10px',
                layout: 'fit', // specify layout manager for items
                items:[{
                	border:false,
                    html: '<br/><font size="4">'+
                    	'<b>Debit Amount</u><img src="../com_exp_cemk_css/images/Go into.png" alt="Debit" width="25" height="25" /><br/>'+
                    	gainerLoserData.debit+'</b><br/><br/>'+
                    	'<b>Credit Amount<img src="../com_exp_cemk_css/images/go-back-icon.png" alt="Credit" width="25" height="25" /><br/>'+
                    	gainerLoserData.credit+'</b><br/><br/>'+
                    	'<b>Top Gainer<img src="../com_exp_cemk_css/images/Up-icon.png" alt="Gainer" width="25" height="25" /><br/>'+
                    	gainerLoserData.gainer+'</b><br/><br/>'+
                    	'<b>Top Loser</u><img src="../com_exp_cemk_css/images/Down-icon.png" alt="Loser" width="25" height="25" /><br/>'+
                    	gainerLoserData.loser+'</b></font>',
                    autoScroll: true
                }] }, {
                region: 'west',
                id: 'west-panel', // see Ext.getCmp() below
                title: 'Welcome',
                collapsible: true,
                split: true,
                width: '20%',
                layout: 'fit',
                minSize: 175,
                maxSize: 400,
                //margins: '0 0 0 5',
                contentEl: 'details'
            },
            // in this instance the TabPanel is not wrapped by another panel
            // since no title is needed, this Panel is added directly
            // as a Container
            new Ext.TabPanel({
                region: 'center', // a center region is ALWAYS required for border layout
                width: '60%',
                activeTab: 0,     // first tab initially active
                listeners: {
                	'tabchange': function(tabpanel, active_tab){
            				Ext.getCmp('debtGrid').doLayout();
							Ext.getCmp('creditGrid').doLayout();
            				active_tab.doLayout();
            				if(active_tab.id==='CreditTab' && !creditFlag){
            					creditStore.load.defer(20,creditStore,[{params:{start:0, limit:17}}]);
            					 creditFlag=true;
            				}
                        }
                    },
                items: [{
                    title: 'Debt',
                    id:'DebtTab',
                    layout:'anchor',
                    autoScroll: true,
                    items:debtGrid
                }, {
                    title: 'Credit',
                    id:'CreditTab',
                    autoScroll: true,
                    layout:'anchor',
                    items:creditGrid
                },{
                    title: 'Expense Breakdown',
                    autoScroll: true,
                    id:'BreakdownTab',
                    layout:'anchor',
                    items:chartExample
                }]
            })]
        });
       
        viewport.render('viewportContent');
        
    });

