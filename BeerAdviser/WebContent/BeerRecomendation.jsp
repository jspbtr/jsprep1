<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="ISO-8859-1">
<title>BeerAdviser</title>
</head>
<body>
<%
         out.println("Beer Selection Advise <br>");
         List styles = (List)request.getAttribute("styles");
                   Iterator i = styles.iterator();
                   while(i.hasNext()){
                	   out.println("<br> try : "+i.next()+"<a href='tp'>Buy Now</a>");
                   }
                   String color = request.getParameter("color");
                   out.print("<br><br><br>Selected Beer Color : "+color);
%>
</body>
</html>