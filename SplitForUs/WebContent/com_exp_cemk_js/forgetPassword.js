  function fogetPasswordPopup(){ 
	  
	  Ext.Msg.prompt('Forget Password?', 'Please enter your User Name :', function(btn, userName,cfg){
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
	  			}else{
	  				var secQues = ajaxFunction('POST','../GetSecurityQuestionServlet',false,params);
	  				Ext.Msg.prompt('Secret Answer', 'Ques:- <b>'+secQues+'</b><br>Please enter your Secret Answer:', function(btn, secAns,cfg){
	  			      if (btn == 'ok'){
	  			    	  var newsecAnsMsg = 'Ques:- <b>'+secQues+'</b><br>Please enter your Secret Answer:';
	  			          if(secAns==="" || secAns===null || secAns===undefined)
	  			  			{
	  			  				Ext.Msg.show(Ext.apply({}, { msg: newsecAnsMsg }, cfg));
	  			  			}
	  			  		else
	  			  		{
			  			  		var secParams="userName="+userName+"&secAns="+secAns;	
			  			  		var password = ajaxFunction('POST','../GetPasswordServlet',false,secParams);
			  			  		if(password.indexOf("Sorry.")>-1)
			  			  			extERRORAlert(password);
			  			  		else
			  			  			extINFOAlert(password);
	  			  		}
	  			      }
	  				});
	  		
	      }}}
	  });

	  
	  
  }