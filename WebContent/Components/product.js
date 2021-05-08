$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertSuccess").hide();

});


//SAVE ============================================
$(document).on("click", "#btnSave", function(product)
		{ 
		// Clear alerts---------------------
		 $("#alertSuccess").text(""); 
		 $("#alertSuccess").hide(); 
		 $("#alertError").text(""); 
		 $("#alertError").hide();
		  
		// Form validation-------------------
		var status = validateproductForm(); 
		if (status != true) 
		 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
		 } 
		// If valid------------------------
		var type = ($("#hidcodeSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "productAPI", 
		 type : type, 
		 data : $("#formproduct").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onFeedbackSaveComplete(response.responseText, status); 
		 } 
		 }); 
		});


function onproductSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 	$("#alertSuccess").text("Successfully saved."); 
 	$("#alertSuccess").show(); 
 	$("#divproductsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 	$("#alertError").text(resultSet.data); 
	$("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 	$("#alertError").text("Error while saving."); 
	$("#alertError").show(); 
 } else
 { 
 	$("#alertError").text("Unknown error while saving.."); 
 	$("#alertError").show(); 
 } 
	$("#hidcodeSave").val(""); 
	$("#formproduct")[0].reset(); 
}


//UPDATE==========================================


$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidcodeSave").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#name").val($(this).closest("tr").find('td:eq(2)').text());  
		 $("#price").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#desc").val($(this).closest("tr").find('td:eq(4)').text()); 
		});




//DELETE==========================================

$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "productAPI", 
		 type : "DELETE", 
		 data : "id=" + $(this).data("id"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onproductDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});



function onproductDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 	$("#alertSuccess").text("Successfully deleted."); 
 	$("#alertSuccess").show(); 
 	$("#divproductsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 	$("#alertError").text(resultSet.data); 
 	$("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 	$("#alertError").text("Error while deleting."); 
 	$("#alertError").show(); 
 } else
 { 
 	$("#alertError").text("Unknown error while deleting.."); 
 	$("#alertError").show(); 
 } 
}




//PRODUCTMODEL VALIDATION=========================================================================

function validateForm() {
	
	if ($("#code").val().trim() == "") {
		return "Insert product code.";
	}
	
	if ($("#name").val().trim() == "") {
		return "Insert product name.";
	}
	if ($("#price").val().trim() == "") {
		return "Insert product price .";
	}
	

	
	if ($("#desc").val().trim() == "") {
		return "Insert product description.";
	}
	
	
	
	
	

	return true;
}