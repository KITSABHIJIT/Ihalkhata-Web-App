/*
 * JQuery functions for slideout feedback form
 * 
 * Sets up a sliding form on click of a feedback button
 * On submit button will send the data to a php script
 * 
 * By http://www.paulund.co.uk
 */
(function ($j) {

  feedback_button = {

    onReady: function () {      
      this.feedback_button_click();
      this.send_feedback();
    },
    
    feedback_button_click: function(){
    	$("#feedback_button").click(function(){
    		$('.form').slideToggle();
    		$('#status').text("Please Send Us Your Feedback");
    		$("#feedback_text").attr("value", "");
    		document.getElementById('limit').innerHTML = "<font color='blue'><i>200 Character left</i></font>";
    	});

    },

    send_feedback: function(){
    	$('#submit_form').click(function(){
    		var email="cemkabhijit@gmail.com";
    		if($('#feedback_text').val().trim() != ""){
    			$('#loadingmessage').ajaxStart (function () {
    				   $(this).show();
    				   $('#feedback_text').attr("disabled", "disabled");
    				   $('#submit_form').attr("disabled", "disabled");
    				}).ajaxStop (function () {
    				   $(this).hide();
    				   $('#feedback_text').removeAttr("disabled");  
    				   $('#submit_form').removeAttr("disabled");
    				});
    			$.ajax({  
    				type: "POST",  
      			  	url: "../SendFeedbackServlet",  
      			  	data: 'feedback=' + $('#feedback_text').val()+ "&user=" + email,  
	      			success: function(result,status) { 
					//feedback sent successfully displays a success message
	      				if(result == 'Message Sent'){
	      					$('#status').text("Feedback successfully Sent");
	      				} else {
	      					$('#status').text("Feedback Failed to Send");
	      				}
	      			},
	      			error: function(result,status){
	      				$('#status').text("Feedback Failed to Send");
	      				 
	      			}  
      			});
    		}
    	});
    },
    
    
  };

  $j().ready(function () {
	  feedback_button.onReady();
  });

})(jQuery);	
