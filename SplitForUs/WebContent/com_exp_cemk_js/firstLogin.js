  function firstLoginPopup(data){ 
	  
	  if(Ext.getCmp('loginWin'))
	    	Ext.getCmp('loginWin').close();
	  
	  	var userName=data.split('|')[0];
	  	var secQues=data.split('|')[1];
	  	

	  
	  var fsf = new Ext.FormPanel({
        labelWidth: 120, // label settings here cascade unless overridden
        url:'../ChangePasswordServlet',
        id:'firstLoginForm',
        frame:true,
        bodyStyle:'padding:5px 5px 0',
        width: 400,

        items: [{
            xtype:'fieldset',
            //checkboxToggle:true,
            title: 'Please Change yor Password:-',
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'textfield',
            //collapsed: true,
            items :[{
	      		xtype:'panel',
	    		autoHeight:true,
	    		width: 330,
	    		style:'padding:2 0 2 2',
	    		layout:'column',
	    		border:false,
	    		items:[{
	    			xtype:'displayfield',
	    			value:'<img src="../com_exp_cemk_css/images/user.jpg" alt="'+userName+'">&nbsp;Welcome <b>'+userName+'</b>',
					border:false}]},{
				    			xtype:'displayfield',
				    			fieldLabel: 'Secret Question',
				    		   	value:'<b>'+secQues+'</b>',
								border:false},{
				                fieldLabel: 'Your Answer',
				                name: 'secAnswer',
				                id: 'secAnswer',
				                inputType: 'password',
				                allowBlank:false
								},
							{
			                fieldLabel: 'Old Password',
			                name: 'oldPassword',
			                id: 'oldPassword',
			                width: 210,
			                inputType: 'password',
			                allowBlank:false
				            },{
				                fieldLabel: 'New Password',
				                name: 'newPassword',
				                id: 'newPassword',
				                width: 210,
				                inputType: 'password',
				                allowBlank:false
					            },{
				                fieldLabel: 'Confirm Password',
				                name: 'newConPassword',
				                id: 'newConPassword',
				                inputType: 'password',
				                vtype: 'passwordCheck',
				                initialPassField: 'newPassword',
				                width: 210,
				                allowBlank:false
				            }
        ]
        }],

        buttons: [{
            text: 'Change Password',
            handler: function(){
        	if(isEmptyValidation('secAnswer','Please give a Secret Answer'))
				if(checkAlphaNumeric('secAnswer','Only Characters and numbers allowed')){
					if(isEmptyValidation('newPassword','Password is mandatory'))
						if(checkPassword('newPassword','minimum length of password should be 8<br>must contains atleast one alphanumeric, capital, small & special characters'))
							if(confirmPasswordValidation('newPassword','newConPassword','Password doesnt Match'))
								Ext.getCmp('firstLoginForm').getForm().submit({reset:false,
						    	   waitMsg:'Changing Password...',
						    	   timeout: 30000,
						    	   success: function(form, action)
						    	   {
									 if(Ext.getCmp('changePwdWin'))
									    	Ext.getCmp('changePwdWin').close();
									Ext.MessageBox.show({
										 title : 'Success!',
										   msg : action.result.msg,
										   buttons : Ext.Msg.OK,
										   animEl : 'e2lId',
										   icon : 'profile',
										   fn: function(btn){
										 	if (btn == 'ok') {
										 		loginPopup();
										 	}}
										});
						    	   },
						    	   failure: function(form,action)
						    	   {
						    		   extERRORAlert(action.result.msg);
						    	   }
						    	   
						       });}
				        		
					   	}}]
				    });

    //fsf.render('viewDatacontent');
    win = new Ext.Window({
    	        layout: 'fit',
    	        width: 415,
    	        title: '<img src="../com_exp_cemk_css/images/user.jpg" height="16" width="16">&nbsp;Change Password:-',
    	        id:'changePwdWin',
    	        height: 300,
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
  }