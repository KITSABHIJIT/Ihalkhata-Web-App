Ext.BLANK_IMAGE_URL = '../com_exp_cemk_css/images/default/s.gif';
Ext.ns('Example');
Example.version = '1.0';

    /*
     * ================  Registration Popup Form 2  =======================
     */
    
    
  function registerPopup(flag){ 
	  Ext.QuickTips.init();
    var fsf = new Ext.FormPanel({
        labelWidth: 120, // label settings here cascade unless overridden
        url:'../UserRegistrationServlet',
        id:'registerForm',
        frame:true,
        bodyStyle:'padding:5px 5px 0',
        width: 400,

        items: [{
            xtype:'fieldset',
            //checkboxToggle:true,
            title: 'User Information',
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'textfield',
            //collapsed: true,
            items :[{
                    fieldLabel: 'First Name',
                    name: 'first',
                    id:'first',
                    allowBlank:false,
                    regex :/^[A-Za-z0-9 ]{3,20}$/,
                    regexText:'No special characters Allowed. Min 3 and max 20 characters',
                    msgTarget:'side',
                    listeners: {
                        afterrender: function(field) {
                            field.focus(false, 1000);
                          }
                        }
                },{
                    fieldLabel: 'Last Name',
                    name: 'last',
                    id:'last',
                    allowBlank:false,
                    regex :/^[A-Za-z0-9 ]{3,20}$/,
                    regexText:'No special characters Allowed. Min 3 and max 20 characters',
                    msgTarget:'side'
                },{
                    fieldLabel: 'Company',
                    name: 'company'
                }, {
                    fieldLabel: 'Email',
                    name: 'email',
                    id:'email',
                    vtype:'email',
                    allowBlank:false,
                    msgTarget:'side'
                    
                },{
                    fieldLabel: 'Mobile No',
                    name: 'mobile',
                    id: 'mobile',
                    maskRe : /^[0-9]+$/,
                    regex :/^([0-9]){7,15}$/,
                    regexText:'You should give a proper Mobile No',
                    msgTarget:'side'
                }
            ]
        },{
            xtype:'fieldset',
           // checkboxToggle:true,
            title: 'Login Information',
            autoHeight:true,
            //defaults: {width: 210},
            defaultType: 'textfield',
            //collapsed: false,
            items :[{
                    fieldLabel: 'User Name',
                    name: 'userName',
                    id: 'userName',
                    width: 210,
                    vtype:'unique',
                    allowBlank:false,
                    regex :/^[A-Za-z0-9_]{1,20}$/,
                    regexText:'User Name supports special characters only underscore.<br>Min 3 and max 20 characters.',
                    msgTarget:'side'
                },{
                    fieldLabel: 'Password',
                    name: 'password',
                    id: 'password',
                    width: 210,
                    inputType: 'password',
                    vtype:'userNamepasswordCheck',
                    initialPassField: 'userName',
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
                    initialPassField: 'password',
                    width: 210,
                    allowBlank:false,
                    msgTarget:'side'
                },{
                    fieldLabel: 'Group Id',
                    name: 'grpId',
                    hiddenName: 'grpId',
                    id: 'grpId',
                    regex :/^[A-Za-z0-9 ]{3,20}$/,
                    regexText:'No special characters Allowed. Min 3 and max 20 characters',
                    width: 210,
                    allowBlank:false,
                    msgTarget:'side'
                },new Ext.form.ComboBox({
                	fieldLabel: 'Security Question',
                    name: 'secQues',
                    id: 'secQues',
                    width: 210,
                    listWidth: 300,
                    allowBlank:false,
                    msgTarget:'side',
                	hideTrigger:false,
   	                store: new Ext.data.ArrayStore({
   	                	fields: ['abbr', 'state'],
   	                	data : [
   	                	        ['What was your childhood nickname?', 'What was your childhood nickname? '],
   	                	        ['What is your mother\'s maiden name?', 'What is your mother\'s maiden name?'],
   	                	        ['In what city or town was your first job?', 'In what city or town was your first job?'],
   	                	        ['What is your pet\'s name?', 'What is your pet\'s name?'],
   	                	        ['What is your favorite movie?','What is your favorite movie?']] // from state.js
   	                        }),
   	                        valueField:'abbr',
   	                        displayField:'state',
   	                        typeAhead: true,
   	                        mode: 'local',
   	                        triggerAction: 'all',
   	                        selectOnFocus:true
                	
                }),{
                    fieldLabel: 'Your Answer',
                    name: 'answer',
                    id: 'answer',
                    inputType: 'password',
                    width: 210,
                    allowBlank:false, 
                    regex :/^[A-Za-z0-9 ]*$/,
                    regexText:'Only Characters and numbers allowed',
                    msgTarget:'side'
                },{
		      		xtype:'panel',
		    		autoHeight:true,
		    		width: 330,
		    		style:'padding:2px 0px 2px 160px',
		    		layout:'column',
		    		border:false,
		    		items:[{
		    			xtype:'displayfield',
		    		   	id:'imgPreview',
		    		   	value:'<img src="'+uriPrefix+'/CaptchaServlet" height="45" alt="Displayed Code">',
						border:false},{	xtype:'displayfield',
			    		   	id:'imgPreview1',
			    		   	style:'padding:15 0 2 2',
			    		   	value:'<img src="../com_exp_cemk_css/images/refresh.png" height="20" width="20" style="cursor: pointer;margin-left: 0px" alt="Click to Reload the Code">',
							border:false,
							listeners: {
		                    afterrender: function(component) {
		                      component.getEl().on('click', function() { 
		                    	   Ext.getCmp('imgPreview').setValue('<img src="'+uriPrefix+'/CaptchaServlet" height="45" alt="Displayed Code">');
		                      });  
		                    }
		                  }
						}]},{
					        fieldLabel: 'Type the code',
					        name: 'code',
					        id: 'code',
					        width: 210,
					        allowBlank:false,
					        vtype: 'checkCaptchaCode',
		                    msgTarget:'side'
					    }
            ]
        }],

        buttons: [{
            text: 'Register',
            handler: function(){
        	if(Ext.getCmp('registerForm').getForm().isValid()){
        	Ext.MessageBox.show({
        		title : 'Confirm?',
        		msg :'Do you want to register<br><b>'+Ext.getCmp('first').getValue()+' '+Ext.getCmp('last').getValue()+'</b>',
        		buttons: Ext.Msg.YESNO,
        		animEl : 'e2lId',
        		icon : 'profile',
        		fn:function(btn){
        		if (btn == 'yes') {
        			//extINFOAlert(Ext.getCmp('first').getValue()+' '+Ext.getCmp('last').getValue()+' registered Successfully')
        			Ext.getCmp('registerForm').getForm().submit({reset:false,
        				waitMsg:'Adding User...',
        				timeout: 30000,
        				success: function(form, action)
        				{
        				Ext.MessageBox.show({
        					title : 'Success!',
        					msg : action.result.error,
        					buttons : Ext.Msg.OK,
        					animEl : 'e2lId',
        					icon : 'profile',
        					fn: function(btn){
        					if (btn == 'ok') {
        						 Ext.getCmp('registerWin').close();
        						 if(flag==="")
        							 loginPopup();
        						
        					}}
        				});			
        				
        				},
        				failure: function(form,action)
        				{
        					extERRORAlert(action.result.error);
        				}
        				
        			});
        			
        		}else{
        			Ext.MessageBox.hide();        
        		}
        		
        	}});
       
        	}}
        },{	
            	text: 'Reset',
            	id:'reset',
            handler: function(){
        	Ext.getCmp('registerForm').getForm().reset();
        	 Ext.getCmp('imgPreview').setValue('<img src="'+uriPrefix+'/CaptchaServlet" height="45" alt="Displayed Code">');
        }	
        }]	
    });	
    
    	//fsf.render('viewDatacontent');
    win = new Ext.Window(
    	    	{
    	    	autoHeight:true,
                modal: true,
                width: 415,
    	        title: '<img src="../com_exp_cemk_css/images/user.jpg" height="16" width="16">&nbsp;Register Yourself:-',
    	        id:'registerWin',
    	        frame:true,	
    	        resizable:false,
    	        closable:true,	
    	        items: new Ext.Panel(
    	        {	
    	           items: fsf
    	        })	
    	    });	
    
    	if(Ext.getCmp('loginWin'))
    		Ext.getCmp('loginWin').close();
    	
    	
    win.show(this);
    
    
    if(flag!==""){
    	Ext.getCmp('grpId').setValue(flag);
    	Ext.getCmp('grpId').getEl().dom.setAttribute('readOnly', true);
    	Ext.getCmp('reset').setVisible(false);
    	Ext.getCmp('registerWin').setTitle('<img src="../com_exp_cemk_css/images/user.jpg" height="16" width="16">&nbsp;Add user to your Group:-');
    }
    
    
  }	
  
  	
  //	************************************************************************************
  	
  	
    	//registerPopup();
    //Ext.getCmp('imgPreview').setSrc('../com_exp_cemk_css/images/confirm.png');

