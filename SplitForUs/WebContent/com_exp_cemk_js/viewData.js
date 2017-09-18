Ext.BLANK_IMAGE_URL = '../com_exp_cemk_css/images/default/s.gif';
Ext.ns('Example');
Example.version = '1.0';
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
      var ExpenseRes = '[{ }]';
   var ExpenseData = Ext.util.JSON.decode(ExpenseRes);
   
   
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

   var ExpenseReader= new Ext.data.JsonReader({
       totalProperty: 'totalCount',
       root: 'items',	       
       fields:[					    		      		       
		        {name    : 'date',mapping : 'date', type: 'date', dateFormat: 'Y-m-d'},
		        {name    : 'items',mapping : 'items'},
		        {name    : 'shareholder',mapping : 'shareholder'},
		        {name    : 'shareholderCount',mapping : 'shareholderCount'},
		        {name    : 'totalExpense',mapping : 'totalExpense',type:'float'},
		        {name    : 'perHead',mapping : 'perHead'},
		        {name    : 'giver',mapping : 'giver'},
		        {name    : 'desc',mapping : 'desc'}
		       
		        
		        
		        
			]
   });
   //added for pagination.....
   
    // manually load local data

   
   var Expensechart  =new Ext.data.GroupingStore({
		proxy:new Ext.data.HttpProxy({ url: '../GridExpenseServlet', timeout: 300000 }), 
		 reader: ExpenseReader,
         groupField:'giver'
     });
   
 
     // create the Grid
    var gridtwo = new Ext.grid.GridPanel({
        store: Expensechart,
        height:460,
        tbar: tb,
        trackMouseOver:false,
        disableSelection:true,
        loadMask: true,
        width:'100%',
        viewConfig:{emptyText: 'No Expenses available',deferEmptyText: false},
        columns: [
            {
                id       :'date',
                header   : '<b>Date</b>',
                dataIndex: 'date',
                sortable : true,
                renderer: Ext.util.Format.dateRenderer('d/m/Y')
                },
            {
                header   : '<b>Items</b>', 
                dataIndex: 'items',
                sortable : true            },
                {
                    header   : '<b>Description</b>', 
                    dataIndex: 'desc',
                    
                    sortable : true/*,
                    renderer:renderShow*/            },
                {
                    header   : '<b>Shareholders</b>', 
                    dataIndex: 'shareholder',
                    
                    sortable : true            },
                {
                    header   : '<b>No of Shareholders</b>', 
                   dataIndex: 'shareholderCount',
                    
                    sortable : true            },
                {
                    header   : '<b>Total Expense (Rs.)</b>', 
                   dataIndex: 'totalExpense',
                   renderer:roundNumberGrid,
                   sortable : true            },
                    {
                        header   : '<b>Per Head Cost (Rs.)</b>', 
                        dataIndex: 'perHead',
                        
                        sortable : true            },
                    {
                        header   : '<b>Paid By</b>', 
                        dataIndex: 'giver',
                        
                        sortable : true            }
                
        ],
        
        view: new Ext.grid.GroupingView({
            forceFit:true,
            emptyText: 'No Expenses available',deferEmptyText: false,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
        }),  
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: Expensechart,
            //height: 30,
			displayInfo: true,
            plugins: new Ext.ux.ProgressBarPager(),
            //displayMsg: 'Displaying notifications {0} - {1} of {2}',
            emptyMsg: "No Expenses to display"
        }) 
    });
	
    Expensechart.load.defer(20,Expensechart,[{params:{start:0, limit:20}}]);
    var outerWidth=window.screen.width-20;
    
    
       //gridtwo.render('viewData123');	 
		 var viewport2 = new Ext.form.FormPanel({
			 layout:'form',
			 id:'Myform',
			 width:'100%',
			 height:490,
			 //renderTo: Ext.getBody(),
			 url:'',

			         items:[{
							 xtye:'panel',
							 standardSubmit:true,
							 border:false,
							 items:[
new Ext.TabPanel({
	id : 'content-panel',
	width:'100%',
   autoHeight:true,
     title:'BCC Report',
     fieldlabel:'BCC Report',
    // defaults:{autoScroll:true}, 
     id:'Tabs',
     activeTab: 0,
     style:'padding:0 0 0 0',
     items:[

            
            {
    	 	title: 'Expense Records ',
            layout:'form',
            width:'100%',
            autoHeight:true,
            items:[  gridtwo]}
							       
							  ]})      
							        
							        
							        ]}]});
		 viewport2.render('viewDatacontent');
  });
	