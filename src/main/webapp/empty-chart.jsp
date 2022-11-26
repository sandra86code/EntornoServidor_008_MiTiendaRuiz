<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.cart.Cart" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vaciar carrito</title>
</head>
<body>
<% 
HttpSession sessionsa = request.getSession();
String login = (String) sessionsa.getAttribute("login");
String nick = (String) sessionsa.getAttribute("nick");
Cart cart = (Cart) sessionsa.getAttribute("cart");
if(login != null && nick !=null && login.equals("true") && cart!=null) {
	try {
		cart.emptyCart();
		%>
		<jsp:forward page="show-cart.jsp"></jsp:forward>
		<%
	}catch(Exception e) {
		String message = e.getMessage();
		%><jsp:forward page="error.jsp?redirect=show-cart&msg=<%=message%>"></jsp:forward><%
	}	
}else {%>
	<jsp:forward page="error.jsp?redirect=index&msg=No te has autenticado"></jsp:forward>
<%}%>
</body>
</html>