<%@page import="com.product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style > p.solid {border-style: solid;} </style>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/product.js"></script>
<meta charset="ISO-8859-1">
<title>Product  Details</title>
</head>
<body>
	<div class="container"><div class="row"><div class="col-6">
		<h1><h1 style="background-color:palevioletred;">Product Details</h1>
<p class="solid">
			<form id="formproduct" name="formproduct">
			
				<b>Code:</b>
				<input id="code" name="code" type="text" class="form-control form-control-sm"><br>
		 		<b>Name:</b>
				<input id="name" name="name" type="text" class="form-control form-control-sm"><br> 
				<b>Price:</b>
				<input id="price" name="price" type="text" class="form-control form-control-sm"><br>
		 		<b>Description:</b>
				<input id="desc" name="desc" type="text" class="form-control form-control-sm"><br>
				

      			<input id="btnCreateproduct" name="btnCreateproduct" type="button" value="Save" class="btn btn-primary">
      			<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
			</form>
	
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			<div id="divItemsGrid">
<%
 product productObj = new product();
 out.print(productObj.readproduct());
 %>
			</div>
	</div> </div> </div> 

</body>
</html>