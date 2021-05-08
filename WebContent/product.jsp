<%@ page import="com.product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>


<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/product.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-8">
 <h1 class="m-3">Product details</h1>
 
 <form id="formproduct" name="formproduct">
 

 <br>
<!-- CODE -->
<br> Code: 
 <input id="code" name="code" type="text" class="form-control form-control-sm">
 <br>
 
<!-- name -->
 <br> Name: 
 <input id="name" name="name" type="text" class="form-control form-control-sm">
 <br>
 
 <!-- price -->
 <br> Price: 
 <input id="price" name="price" type="text" class="form-control form-control-sm">
 <br>
 
 <!-- desc -->
 <br> Description: 
 <input id="desc" name="desc" type="text" class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 <input type="hidden" id="hidIDSave" name="hidIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

 <br>
<div id="divproductsGrid">


<%
 product productObj = new product();
 out.print(productObj.readproduct());
 %>


</div>
</div> </div> </div>

    

</body>
</html>
