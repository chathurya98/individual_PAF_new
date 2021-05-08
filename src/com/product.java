package com;



	import java.sql.Connection;

	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.Statement;

	public class product {

		
		//A common method to connect to the DB
			private Connection connect() 
			 { 
			 Connection con = null; 
			 try
			 { 
				 Class.forName("com.mysql.jdbc.Driver"); 
			 
			 //Provide the correct details: DBServer/DBName, username, password 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/product", "root", "");
			 } 
			 catch (Exception e) 
			 {e.printStackTrace();} 
			 return con; 
			 } 
			public String insertproduct(String code, String name, String price, String desc) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for inserting."; } 
			 
			 // create a prepared statement
			 String query = " insert into producttable (`code`,`name`,`price`,`desc`)"
			 + " values (?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			
			 preparedStmt.setString(1, code); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, price); 
			 preparedStmt.setString(4, desc);
			
			 
			// execute the statement3
			 preparedStmt.execute(); 
			 con.close(); 
			 String newproduct = readproduct(); 
			 output = "{\"status\":\"success\", \"data\": \"" +  newproduct + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\":  \"Error while inserting the product.\"}"; 
				 System.err.println(e.getMessage()); 
				 } 
				 return output; 
				 } 
		
			
			
			public String readproduct() 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>code</th><th>name</th>" +
			 "<th>price</th>" + 
			 "<th>desc</th>"  +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from producttable"; 
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String code = Integer.toString(rs.getInt("code")); 
			 String name = rs.getString("name"); 
			 String price = rs.getString("price"); 
			 String desc = rs.getString("desc"); 
			
			 
			 
			 // Add into the html table
		
			 output += "<tr><td><input code='hidcodeUpdate' name='hidcodeUpdate'type='hidden' value='" + code + "'>"+name+ "</td>"; 
		       output += "<td>" + name + "</td>"; 
			 output += "<td>" + price + "</td>"; 
			 output += "<td>" + desc + "</td>"; 
			
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' "
					 + "class='btnUpdate btn btn-secondary' data-code='" + code + "'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' "
					 + "class='btnRemove btn btn-danger' data-code='" + code + "'></td></tr>"; 
					  } 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while reading the product."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
			public String updateproduct(String code, String name, String price,String desc)
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 // create a prepared statement
			 String query = "UPDATE product SET name=?,price=?,desc=?  WHERE code=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values

			
			 preparedStmt.setString(1, code); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, price); 
			 preparedStmt.setString(4, desc);
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newproduct = readproduct(); 
			 output = "{\"status\":\"success\", \"data\": \"" +  newproduct + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\":  \"Error while updating the product.\"}"; 
				 System.err.println(e.getMessage()); 
				 } 
				 return output; 
				 }  
			public String deleteproduct(String code) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
			 // create a prepared statement
			 String query = "delete from producttable where code=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, code);
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newproduct = readproduct(); 
			 output = "{\"status\":\"success\", \"data\": \"" +  newproduct + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\":  \"Error while deleting the product.\"}"; 
						 System.err.println(e.getMessage()); 
						 } 
						 return output; 
						 } 
						}
	

