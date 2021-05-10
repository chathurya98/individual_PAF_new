$(document).on("click", "#btnCreateproduct", function(event){ 
	
	
	// Clear alerts---------------------
 $("#alertSuccess").text("Successfully Inserted"); 
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
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "productAPI", 
 type : type, 
 data : $("#formproduct").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onproductSaveComplete(response.responseText, status); 
 } 
 });
});


// CLIENT-MODEL================================================================
function validateproductForm(){
	// code
	if ($("#code").val().trim() == ""){
		return "Insert product code.";
	}
	// Name
	if ($("#name").val().trim() == ""){
		return "Insert product Name.";
	}

	// price-------------------------------
	if ($("#price").val().trim() == ""){
		return "Insert product price.";
	}
	// Description-------------------------------
	if ($("#desc").val().trim() == ""){
		return "Insert product description.";
	}
	
	
	return true;
}


function onproductSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
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
$("#hidItemIDSave").val(""); 
 
}


$(document).on("click", ".btnRemove", function(event) { 
		 $.ajax( 
		 	{ 
		 	url : "productAPI", 
		 	type : "DELETE", 
		 	data : "code=" + $(this).data("code"),
		 	dataType : "text", 
		 	complete : function(response, status) { 
		 		onproductDeleteComplete(response.responseText, status); 
		 	} 
		}); 
})
		
function onproductDeleteComplete(response, status){ 
	if (status == "success") { 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") { 
 			$("#alertSuccess").text("Successfully deleted."); 
 			$("#alertSuccess").show(); 
 			$("#divItemsGrid").html(resultSet.data); 
 		} else if (resultSet.status.trim() == "error") { 
 			$("#alertError").text(resultSet.data); 
 			$("#alertError").show(); 
 		} 
 	} else if (status == "error") { 
 		$("#alertError").text("Error while deleting."); 
 		$("#alertError").show(); 
 	} else { 
 		$("#alertError").text("Unknown error while deleting.."); 
 		$("#alertError").show(); 
 	} 
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{ 
	 $("#hidItemIDSave").val($(this).data("code")); 
		 $("#code").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#name").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#price").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#desc").val($(this).closest("tr").find('td:eq(3)').text()); 
		 
		});