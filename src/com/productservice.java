package com;
import model.product;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/product")

//PRODUCT SERVICE CALL
public class productservice {
	product productObj = new product();
@GET
@Path("/product")
@Produces(MediaType.TEXT_HTML)
	public String readproducts()
	{
		 return productObj.readproduct();
		 }
	
//----------------------------------------------------------------------------------
	
//INSERT(ADD,POST)
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
	public String insertproduct(@FormParam("code") String code,
			@FormParam("name") String name,
			@FormParam("price") String price,
			@FormParam("desc") String desc)
	{
		String output = productObj.insertproduct(code, name,price, desc);
	return output;
		}
	


//----------------------------------------------------------------------------------

//READ(GET)
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
		public String readproduct()
	 {
	 		return productObj.readproduct();
	 		}
	
	
//----------------------------------------------------------------------------------

//UPDATE(PUT)
@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
		public String updateproduct(String productData)
	{
		
//CONVERT THE INPUT STRING TO JSON OBJECT
	 JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject();
	 
//READ THE VALUES FROM THE JSON OBJECT
	 String code = productObject.get("code").getAsString();
	 String name = productObject.get("name").getAsString();
	 String price = productObject.get("price").getAsString();
	 String desc = productObject.get("desc").getAsString();
	 String output = productObj.updateproduct(code, name, price,desc);
	 	return output;
	}



//----------------------------------------------------------------------------------	

//DELETE 
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
		public String deleteproduct(String productData)
	{
//CONVERT THE INPUT STRING TO AN XML
	 Document doc = Jsoup.parse(productData, "", Parser.xmlParser());

//READ THE VALUE FROM THE ELEMENT <PRODUCTCODE>
	 String code = doc.select("code").text();
	 String output = productObj.deleteproduct(code);
	 	return output;
	}
	
}

