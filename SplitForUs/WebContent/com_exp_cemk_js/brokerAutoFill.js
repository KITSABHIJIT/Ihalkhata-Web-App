var finalBrokerStore=null;
var abc="cde";

	 Ext.QuickTips.init();
	 var headersearchdata1= '[{ }]';
	    var headersearchdata = Ext.util.JSON.decode(headersearchdata1);
           
//Broker JSONReader and Store
	    
	   var brokerReader= new Ext.data.JsonReader(
					{						   
						              
					},[		
					    {name    : 'brokerdisplay',mapping : 'brokerdisplay'},
					 
					 
					   	{name    : 'brokervalue' ,mapping : 'brokervalue'}
					   
					]
				);
	       
			var brokerStore = new Ext.data.Store({
			       
		         fields: ['brokerdisplay','brokervalue'],
		         data: headersearchdata,
		      	reader:brokerReader,
		      	pruneModifiedRecords : true
		        
		     });
			
			var finalBrokerStore = new Ext.data.Store({
		       
	 		 fields: ['brokerdisplay','brokervalue'],
		        data: headersearchdata,
		      	reader:brokerReader,
		      	pruneModifiedRecords : true
		        
		     });
			
			var brokerRecord = Ext.data.Record.create([ // creates a subclass of Ext.data.Record
	 		                            		      
	 		                            		      {name: 'brokerdisplay'},
	 		                            		{name: 'brokervalue'}
	 		                            		      
	 		                            		    ]); 

       		
function autoBroker(key)
{
		
	var found=false;
	var addFinalBrk = new Array();
	var indexAddFinalBrk = 0;
	var prevValue = Ext.getCmp('brokerFinal').getValue();
	var prevValueArray = new Array();
	prevValueArray = prevValue.split(",");
				 			
	var itemValue = key.value;
	var itemName = key.getRawValue();
	
 		  		
				/*if(prevValueArray.toString()==="")
 		  		{
 		  		//	alert("prevCheckedValues.length ==0");
 		  			Ext.getCmp('brokerFinal').setValue(itemName);
 		  			document.getElementById("brkTokens").value = itemValue;
 		  			var NewRecord = new brokerRecord({
 		  				brokerdisplay: itemName,
 		  				brokervalue: itemValue
 		  				});
 		  							Ext.getCmp('brokerFinal').getStore().add(NewRecord);
 		  		}*/
 		  		
 		  			for(var i=0;i<prevValueArray.length;i++){
 		  			if(prevValueArray[i]==itemValue){
 		  				found = true;
	 	  				break;
 		  			}
 		  			}
 		  			if(!found)
		 	  		{
 		  				var NewRecord = new brokerRecord({
 		  					brokerdisplay: itemName,
 		  					brokervalue: itemValue
 		  					});
 		  			Ext.getCmp('brokerFinal').getStore().add(NewRecord);
 		  			Ext.getCmp('brokerFinal').setValue(prevValueArray+","+itemName);
 		  			//document.getElementById('itemTokens').value = prevValueArray+","+itemValue;
		 	  		}
 		  		
			
				
				Ext.getCmp('brokerFinal').getStore().commitChanges();
				key.getStore().removeAll(true);
				key.setRawValue('');
				
				
			
			
			}