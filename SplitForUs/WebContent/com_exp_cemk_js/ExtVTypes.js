	
	//***************SET OF REGULAR EXPRESSION********************************************

	var ck_name = /^[A-Za-z0-9 ]{3,20}$/;
	var ck_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i; 
	var ck_username = /^[A-Za-z0-9_]{1,20}$/;
	//var ck_password =  /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;
	var ck_password = /^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$/;
	var ck_phone = /^([0-9]){7,15}$/;
	var ck_alphaNumericr =/[^A-Za-z\d]$/;

	//************************************************************************************
	
	
	
Ext.apply(Ext.form.VTypes, {
	
	//****************VALIDATING DATE RANGES**********************
	
    daterange : function(val, field) {
        var date = field.parseDate(val);

        if(!date){
            return false;
        }
        if (field.startDateField) {
            var start = Ext.getCmp(field.startDateField);
            if (!start.maxValue || (date.getTime() != start.maxValue.getTime())) {
                start.setMaxValue(date);
                start.validate();
            }
        }
        else if (field.endDateField) {
            var end = Ext.getCmp(field.endDateField);
            if (!end.minValue || (date.getTime() != end.minValue.getTime())) {
                end.setMinValue(date);
                end.validate();
            }
        }
        /*
         * Always return true since we're only using this vtype to set the
         * min/max allowed values (these are tested for after the vtype test)
         */
        return true;
    },
    
    //************************************************************************************
    
    //****************VALIDATING CONFIRM PASSWORD*****************************************
      passwordCheck : function(val, field) {
          if (field.initialPassField) {
              var pwd = Ext.getCmp(field.initialPassField);
              return (val == pwd.getValue());
          }
          return true;
      },

      passwordCheckText : 'Passwords do not match',
    //************************************************************************************
      
      //****************VALIDATING USER NAME AND PASSWORD*****************************************
      userNamepasswordCheck : function(val, field) {
          if (field.initialPassField) {
              var pwd = Ext.getCmp(field.initialPassField);
              return (val != pwd.getValue());
          }
         
      },

      userNamepasswordCheckText : 'User Name & Password should not be same',
    //************************************************************************************
      //****************VALIDATING OLD AND NEW PASSWORD*****************************************
      newpasswordCheck : function(val, field) {
          if (field.initialPassField) {
              var pwd = Ext.getCmp(field.initialPassField);
              return (val != pwd.getValue());
          }
         
      },

      newpasswordCheckText : 'Old & New Password should not be same',
    //************************************************************************************
   
    //****************VALIDATING CACTCHA CODE*********************************************
      checkCaptchaCode : function(val, field) {
         var data=ajaxFunction('GET','../CaptchaServlet',false,'flag=1');
      	return (val == data);
          
          return true;
      },

      checkCaptchaCodeText : 'Please give the correct code',
    //************************************************************************************
   
    //****************VALIDATING UNIQUE USER NAME*********************************************
      unique: function(value, field){ 
    	  var data=ajaxFunction('POST','../CheckUserNameServlet',false,'userName='+value);
    	  return (data=='false');
    	  
    	  },
    	  uniqueText: 'This User Name is already in use.'
    	  
     //************************************************************************************
});

function trim (str) {
	var	str = str.replace(/^\s\s*/, ''),
		ws = /\s/,
		i = str.length;
	while (ws.test(str.charAt(--i)));
	return str.slice(0, i + 1);
}
function extERRORAlert(msg){
	Ext.Msg.show({
		   title : 'Sorry!',
		   msg : msg,
		   buttons : Ext.Msg.OK,
		   animEl : 'e2lId',
		   icon : Ext.MessageBox.ERROR
			   });
	return false;
}
function extINFOAlert(msg){
	Ext.Msg.show({
		   title : 'Success!',
		   msg : msg,
		   buttons : Ext.Msg.OK,
		   animEl : 'e2lId',
		   icon : Ext.MessageBox.INFO
			   });
	return true;
}
function extWarningAlert(msg){
	Ext.Msg.show({
		   title : 'Warning!',
		   msg : msg,
		   buttons : Ext.Msg.OK,
		   animEl : 'e2lId',
		   icon : Ext.MessageBox.WARNING
			   });
	return false;
}


function isEmptyValidation(component,msg){
	var data=Ext.getCmp(component).getValue();
	data=trim(data);
	if(data==="" || data===null || data===undefined){
		extERRORAlert(msg);
		Ext.getCmp(component).setValue('');
		return false;
	}else
		return true;
	
}

function confirmPasswordValidation(component,component1,msg){
	var data=trim(Ext.getCmp(component).getValue());
	var data1=trim(Ext.getCmp(component1).getValue());
	if(data!=data1){
		extERRORAlert(msg);
		Ext.getCmp(component1).setValue('');
		return false;
	}else
		return true;
	
}

function checkCaptchaCode(data,component,msg){
	var data1=Ext.getCmp(component).getValue();
	if(data!=data1){
		extERRORAlert(msg);
		Ext.getCmp(component).setValue('');
		return false;
	}else
		return true;
	
}


function checkEmail(component,msg){
	var data=Ext.getCmp(component).getValue();
	if(!ck_email.test(data)){
		extERRORAlert(msg);
		Ext.getCmp(component).setValue('');
		return false;
	}else
		return true;
}
function checkName(component,msg){
	var data=Ext.getCmp(component).getValue();
	if(!ck_name.test(data)){
		extERRORAlert(msg);
		Ext.getCmp(component).setValue('');
		return false;
	}else
		return true;
}
function checkUserName(component,msg){
	var data=Ext.getCmp(component).getValue();
	if(!ck_username.test(data)){
		extERRORAlert(msg);
		Ext.getCmp(component).setValue('');
		return false;
	}else
		return true;
}
function checkPassword(component,msg){
	var data=Ext.getCmp(component).getValue();
	if(!ck_password.test(data)){
		extERRORAlert(msg);
		Ext.getCmp(component).setValue('');
		return false;
	}else
		return true;
}
function checkPhone(component,msg){
	var data=Ext.getCmp(component).getValue();
	if(!ck_phone.test(data)){
		extERRORAlert(msg);
		Ext.getCmp(component).setValue('');
		return false;
	}else
		return true;
}
function checkAlphaNumeric(component,msg){
	var data=Ext.getCmp(component).getValue();
	if(ck_alphaNumericr.test(data)){
		extERRORAlert(msg);
		Ext.getCmp(component).setValue('');
		return false;
	}else
		return true;
}