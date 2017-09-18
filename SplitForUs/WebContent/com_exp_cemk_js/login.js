
  
    /*
     * ================  Registration Popup Form 2  =======================
     */
    
    
  function loginPopup(){ 
	  Ext.QuickTips.init();
    var fsf = new Ext.FormPanel({
        labelWidth: 100, // label settings here cascade unless overridden
        url:'../LoginServlet',
        id:'loginForm',
        frame:true,
        bodyStyle:'padding:5px 5px 0',
        width: 350,

        items: [{
            xtype:'fieldset',
            //checkboxToggle:true,
            title: 'Login Information',
            autoHeight:true,
            //defaults: {width: 175},
            defaultType: 'textfield',
            //collapsed: true,
            items :[{
                    fieldLabel: 'User Name',
                    name: 'userName',
                    id: 'loginName',
                    width:175,
                    selectOnFocus:true,
                    allowBlank:false,
                    msgTarget:'side',
                    listeners: {
                        afterrender: function(field) {
                            field.focus(false, 1000);
                          }
                        }
                },{
                    fieldLabel: 'Password',
                    name: 'password',
                    width:175,
                    inputType: 'password',
                    id: 'loginPassword',
                    allowBlank:false,
                    msgTarget:'side'
                },{
		      		xtype:'panel',
		    		autoHeight:true,
		    		width: 350,
		    		style:'padding:2 0 2 2',
		    		layout:'column',
		    		border:false,
		    		items:[{
	    			xtype:'displayfield',
	    		   	id:'register',
	    		   	width:160,
	    		   	value:"<a href=\"#\" onclick=\"registerPopup(''); return false;\"><b><font color='blue'>Register Yourself</font></b></a>",
					border:false},
					{
		    			xtype:'displayfield',
		    		   	id:'forgetPwd',
		    		   	width:145,
		    		   	value:"<a href=\"#\" onclick=\"fogetPasswordPopup(); return false;\"><b><font color='blue'>Forget Password?</font></b></a>",
						border:false}]}
            ]
        }],

        buttons: [{
            text: 'Login',
            handler: function(){
        		submit();
        		
	   	}}],
	   	keys: [{ key: [Ext.EventObject.ENTER],
	   			handler: function() {submit();}  

             } ] 

    });

    //fsf.render('viewDatacontent');
    win = new Ext.Window(
    	    {
    	        width: 360,
    	        title: '<img src="../com_exp_cemk_css/images/user.jpg" height="16" width="16">&nbsp;Please identify yourself:-',
    	        id:'loginWin',
    	        autoHeight:true,
    	        modal: true,
    	        frame:true,
    	        resizable:false,
    	        //closeAction: 'hide',
    	        closable:true,
    	        items: new Ext.Panel(
    	        {
    	           items: fsf
    	        })
    	    });

    
    win.show(this);
    Ext.getCmp('loginWin').focus(true);
    
    
  }
function submit(){
	if(Ext.getCmp('loginForm').getForm().isValid()){
			Ext.getCmp('loginForm').getForm().submit({reset:false,
		 	   waitMsg:' ',
		 	   timeout: 30000,
		 	   success: function(form, action)
		 	   {
		 	   if(action.result.msg.indexOf("|")>-1){
		 		   firstLoginPopup(action.result.msg);
		 	   }else{
		 		  Ext.getCmp('loginWin').close();
		 		  location.replace(action.result.url);  
		 	   }
		 	   
		 	   },
		 	   failure: function(form,action)
		 	   {
		 		   extERRORAlert(action.result.msg);
		 		  Ext.getCmp('loginForm').getForm().reset();
		 	   }
		 	   
		    });}
}
  
  //************************************************************************************
