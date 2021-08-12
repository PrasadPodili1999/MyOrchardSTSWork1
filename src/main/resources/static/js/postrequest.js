$( document ).ready(function() {
	
	$("#add").click(function(){
		
		$("#addEmployee").slideToggle("slow");
	});
	// SUBMIT FORM
    $("#employeeForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	// PREPARE FORM DATA
    	var formData = {
    			name : $("#name").val(),
    			salary : $("#salary").val(),
    			dept : $("#dept").val()
    	}
    	
    	console.log("formData before post: " + formData);
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "ems/addEmployee",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				
					$("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" + 
												"Post Successfully! <br>" +
												"--> {name: " + result.name + 
													", salary: " + result.salary +
													", dept: " + result.dept +"}</p>");
				
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    	// Reset FormData after Posting
    	resetData();

    }
    
    function resetData(){
    	$("#name").val("");
    	$("#salary").val("");
    	$("#dept").val("");
    }
})