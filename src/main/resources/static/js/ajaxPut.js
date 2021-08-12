$(document).ready(function() {
	
	
	$("#update").click(function(){
	
	$("#updateEmployee").slideToggle("slow");
	});
	
	
	
	$("#customizedForm").submit(function(){
		event.preventDefault();
		ajaxPut();
	});
	
	
	/*
	 * AJAX PUT updated-form
	 */
    function ajaxPut(){
    	// PREPARE FORM DATA
    	var formData = {
    			id: $("#updateFormEmpId").val(),
    			name : $("#updateFormName").val(),
    			salary : $("#updateFormSalary").val(),
    			dept : $("#updateFormDept").val()
    	}
    	
    	
    	console.log("formData before PUT: " + formData);
    	
    	// DO PUT
    	$.ajax({
			type : "PUT",
			contentType : "application/json",
			url : "ems/update" ,
			data : JSON.stringify(formData),
			dataType : 'json',
			
			// SUCCESS response
			success : function(employee) {
				// Create successful message
				$("#putResultDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" + 
											"Put Successfully! <br>" +
											"--> {id: " + employee.id +
												"name: " + employee.name +
												", salary: " + employee.salary +
												", dept: " + employee.dept +"}</p>");
				
				
			},
			
			// ERROR response 
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    }
})