$(document).ready(function() {
	
	// Do GET all Employees from Back-End with JQUERY AJAX
	$(Document).on("click","#getAll",function(){
	
		$.ajax({
			type : "GET",
			url : "ems/getEmployee",
			success: function(result){
				$.each(result, function(index, employee){
					
					var employeeRow = '<tr>' +
										'<td>' + index + '</td>' +
										'<td>' + employee.name.toUpperCase() + '</td>' +
										'<td>' + employee.salary + '</td>' +
										'<td>' + employee.dept.toUpperCase() + '</td>' +
								        '<td class="text-center">' +
								        	'<input type="hidden" value=' + employee.id + '>' +
								        	'<a class="deletelink">' +
						          				'<span >Delete</span>' +
						        			'</a>' +
								        '</td>' +
									  '</tr>';
					
					$('#employeeTable tbody').append(employeeRow);
		        });
				
				$( "#employeeTable tbody tr:odd" ).addClass("info");
				$( "#employeeTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});	
	});
	
	// Do DELETE a Customer via JQUERY AJAX
	$(document).on("click","a.deletelink",function() {
		
		var employeeId = $(this).parent().find('input').val();
		var workingObject = $(this);
		
		$.ajax({
			type : "DELETE",
			url : "ems/delete/" + employeeId,
			success: function(resultMsg){
				$("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
											"Employee with Id=" + employeeId + " is deleted successfully!"  +
										"</p>");
				
				workingObject.closest("tr").remove();
				
				// re-css for table
				$( "#employeeTable tbody tr:odd" ).addClass("info");
				$( "#employeeTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
	});
});