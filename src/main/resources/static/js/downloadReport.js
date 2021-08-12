$(Document).ready(function(){
	
	
	$(Document).on("click","#downloadExcel",function(){
		
		$.ajax({
			type : "GET",
			url : "ems/generate",
			success : function() {
				
					$("#downloadResult").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" + 
												"Downloaded Successfully! <br>" +"</p>");
				alert("Excel File Downloaded successfully!!!")
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});
})