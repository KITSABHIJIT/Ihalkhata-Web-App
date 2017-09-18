  function changePasswordPopup(){ 
	  
	  Ext.Msg.prompt('Change Password?', 'Please enter your User Name :', function(btn, userName,cfg){
	      if (btn == 'ok'){
	  	var newMsg = 'Please enter your User Name:';
	          if(userName==="" || userName===null || userName===undefined)
	  			{
	  				Ext.Msg.show(Ext.apply({}, { msg: newMsg }, cfg));
	  			}
	  		else
	  		{
	  			var params="userName="+userName;
	  			var userNamePresent = ajaxFunction('POST','../CheckUserNameServlet',false,params);
	  			if(userNamePresent==='false'){
	  				 Ext.MessageBox.show({
						 title : 'Success!',
						   msg : 'User Name is not valid.',
						   buttons: {yes: 'Ok', no: 'Try Again'},
	  				 		animEl : 'e2lId',
						   icon :  Ext.MessageBox.ERROR,
						   fn: function(btn){
						 	if (btn == 'no') {
						 		Ext.Msg.show(Ext.apply({}, { msg: newMsg }, cfg));
						 		}}
						});
	  			}else
	  				innerChangePasswordPopup();
	  			}}
	  });

	  
	  
  }
  
  function innerChangePasswordPopup(){ 
	  Ext.QuickTips.init();
    var fsf = new Ext.FormPanel({
        labelWidth: 100, // label settings here cascade unless overridden
        url:'../ChangePasswordServlet',
        id:'changePwdForm',
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
                    fieldLabel: 'Current Password',
                    name: 'oldPassword',
                    id: 'oldPassword',
                    width:175,
                    selectOnFocus:true,
                    inputType: 'password',
                    allowBlank:false,
                    msgTarget:'side',
                    listeners: {
                        afterrender: function(field) {
                            field.focus(false, 1000);
                          }
                        }
                },{
                    fieldLabel: 'Password',
                    name: 'newPassword',
                    id: 'newPassword',
                    width: 175,
                    inputType: 'password',
                    vtype:'newpasswordCheck',
                    initialPassField: 'oldPassword',
                    allowBlank:false,
                    regex :/^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$/,
                    regexText:'minimum length of password should be 8<br>must contains atleast one alphanumeric, capital, small & special characters',
                    msgTarget:'side'
                },{
                    fieldLabel: 'Confirm Password',
                    name: 'conPassword',
                    id: 'conPassword',
                    inputType: 'password',
                    vtype: 'passwordCheck',
                    initialPassField: 'newPassword',
                    width: 175,
                    allowBlank:false,
                    msgTarget:'side'
                }]
        }],

        buttons: [{
            text: 'Change Password',
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
    	        title: '<img src="../com_exp_cemk_css/images/user.jpg" height="16" width="16">&nbsp;Change Password:-',
    	        id:'changePwdWin',
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
    Ext.getCmp('changePwdWin').focus(true);
    
    
  }
function submit(){
	if(Ext.getCmp('changePwdForm').getForm().isValid()){
			Ext.getCmp('changePwdForm').getForm().submit({reset:false,
		 	   waitMsg:'Changing Password...',
		 	   timeout: 30000,
		 	   success: function(form, action)
		 	   {
		 		   Ext.getCmp('changePwdWin').close();
		 		  extINFOAlert(action.result.msg);
		 	   },
		 	   failure: function(form,action)
		 	   {
		 		  extERRORAlert(action.result.msg);
		 		  Ext.getCmp('changePwdForm').getForm().reset();
		 	   }
		 	   
		    });}
}