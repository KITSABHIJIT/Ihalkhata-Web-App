Ext.BLANK_IMAGE_URL = '../com_exp_cemk_css/images/default/s.gif';
Ext.ns('Example');
Example.version = '1.0';

    /*
     * ================  Edit Profile Pop up  =======================
     */
function validateFileExtension(fileName) {
	var exp = /^.*\.(jpg|jpeg|JPG|JPEG|png|PNG|bmp|BMP)$/;
	return exp.test(fileName);
}  
  var filePath="";
  function editProfilePopup(userId,userName,company,email,phone){ 
	  Ext.QuickTips.init();
    var editForm = new Ext.FormPanel({
        labelWidth: '15%', // label settings here cascade unless overridden
        id:'editProfileForm',
        frame:true,
        layout:'column',
        fileUpload : true ,
        bodyStyle:'padding:5px 5px 0',
        width: '100%',

        items: [{
            xtype:'fieldset',
            title: 'User Information',
            width: '65%',
            autoHeight:true,
            defaults: {width: '90%'},
            margins:'0px 10px 0px 0px',
            defaultType: 'textfield',
            items :[{
                    fieldLabel: 'Name',
                    name: 'first',
                    id:'first',
                    allowBlank:false,
                    regex :/^[A-Za-z0-9 ]{3,20}$/,
                    regexText:'No special characters Allowed. Min 3 and max 20 characters',
                    msgTarget:'side',
                    value:userName
                },{
                    fieldLabel: 'Company',
                    name: 'company',
                    id:'company',
                    value:company
                }, {
                    fieldLabel: 'Email',
                    name: 'email',
                    id:'email',
                    vtype:'email',
                    allowBlank:false,
                    msgTarget:'side',
                    value:email
                    
                },{
                    fieldLabel: 'Mobile No',
                    name: 'mobile',
                    id: 'mobile',
                    maskRe : /^[0-9]+$/,
                    regex :/^([0-9]){7,15}$/,
                    regexText:'You should give a proper Mobile No',
                    msgTarget:'side',
                    value:phone
                }
            ]
        },{
            xtype:'fieldset',
            title: 'Profile Pic',
            autoHeight:true,
            width: '25%',
            style:'padding:0px 5px 0px 30px',
            items :[{
		      		xtype:'panel',
		    		autoHeight:true,
		    		width: '100%',
		    		layout:'auto',
		    		border:false,
		    		items:[{
		    			xtype:'displayfield',
		    		   	id:'profilePic',
		    		   	value:'<img src="'+uriPrefix+'/ImageServlet?loginId='+userId+'"width=80 height=80 alt="'+userName+'">',
						border:false},{
				      		xtype:'panel',
				    		autoHeight:true,
				    		width: '100%',
				    		layout:'auto',
				    		style:'padding:5px 2px 2px 15px',
				    		border:false,
				    		items:[{      

                			xtype: 'fileuploadfield', 
                			id: 'filedata',
                			buttonOnly: true,
                			hideLabel: true,
                		    buttonText: 'change',
                		    listeners:{
                				      'fileselected': function(key)
                				      {
                		    	 
                		    	           var path=escape(Ext.getCmp('filedata').getValue());
                		    	          var chk= validateFileExtension(path);
                		    	          if(chk===true)
                		    	          {
                		    	           filePath=path;
                		    	          }
                		    	          else
                		    	          {
                		    	        	  extERRORAlert('Wrong Selection, Only Image file can be uploaded.');
                		    	        	  filePath="";
                		    	          }
                		    
                				      }}
                			


}]}
                				]}
            ]
        }],

        buttons: [{
            text: 'Update Profile',
            handler: function(){

        	if(Ext.getCmp('editProfileForm').getForm().isValid()){
        		var userNameSelected=escape(Ext.getCmp('first').getValue());
        		var emailSelected=escape(Ext.getCmp('email').getValue());
        		var phoneSelected=escape(Ext.getCmp('mobile').getValue());
        		var companySelected=escape(Ext.getCmp('company').getValue());
        		
        		if(filePath==="" && userNameSelected===escape(userName) && emailSelected===escape(email) && phoneSelected===escape(phone) && companySelected===escape(company))
        			extERRORAlert('Please edit something before Update.');
        		else{
        	Ext.MessageBox.show({
        		title : 'Confirm?',
        		msg :'Do you want to update <br><b>'+Ext.getCmp('first').getValue()+'\'s Profile</b>',
        		buttons: Ext.Msg.YESNO,
        		animEl : 'e2lId',
        		icon : 'profile',
        		fn:function(btn){
        		if (btn == 'yes') {

        			  
	    	          
	    	          
	    	            var url=uriPrefix+"/EditProfileServlet?userName="+userNameSelected+"&email="+emailSelected
	    	        	  +"&mobile="+phoneSelected+"&company="+companySelected+"&filePath="+filePath;
	    	        	  Ext.getCmp('editProfileForm').getForm().submit({
	    	        		  url:url,
	    	        		  waitMsg:'Updating '+Ext.getCmp('first').getValue()+'\'s Profile...',
	    	        		  timeout: 30000,
	    	        		  success: function(form, action)
	    	        		  {
		    	        	   Ext.Msg.show({
		    	        		   title : 'Success!',
		    	        		   msg : action.result.msg,
		    	        		   buttons : Ext.Msg.OK,
		    	        		   animEl : 'e2lId',
		    	        		   icon : Ext.MessageBox.INFO,
		    	        		   fn:function(btn){
		    	               		if (btn == 'ok') {
		    	               			Ext.getCmp('editProfileWin').close();
		    	               			location.reload(true);
		    	               		}}
		    	        			   });
		    	        	   
		    	        	   	} ,
	    	        		  failure: function(form,action)
	    	        		  {
	        					extERRORAlert(action.result.msg);
	    	        		  }
	    	        		  
	    	    	 });  
	    	    	 
	    	     
			    }else{
        			Ext.MessageBox.hide();        
        		}
        		
        	}});
        		}
        	}
        	
        }
        }]	
    });	
    
    	//fsf.render('viewDatacontent');
    win = new Ext.Window(
    	    	{
    	    	autoHeight:true,
                modal: true,
                width: '45%',
    	        title: '<img src="../com_exp_cemk_css/images/user.jpg" height="16" width="16">&nbsp;Edit Profile:-',
    	        id:'editProfileWin',
    	        frame:true,	
    	        resizable:false,
    	        closable:true,	
    	        items: new Ext.Panel(
    	        {	
    	           items: editForm
    	        })	
    	    });	
     	
    win.show(this);
    

    
    
  }	
  
  	
 