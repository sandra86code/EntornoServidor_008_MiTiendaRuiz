<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.cart.Cart" %>
<%@ page import ="com.jacaranda.cart.CartItem" %>
<%@ page import ="com.jacaranda.control.DaoPurchase" %>
<%@ page import ="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comprar artículos</title>
</head>
<body>
<% 
HttpSession sessionsa = request.getSession();
String login = (String) sessionsa.getAttribute("login");
String nick = (String) sessionsa.getAttribute("nick");
String admin = (String) sessionsa.getAttribute("admin");
Cart cart = (Cart) sessionsa.getAttribute("cart");
if(login != null && nick !=null && login.equals("true") && cart!=null) {
	try {
		ArrayList<CartItem> cartItems = cart.getCartItems();
		if(cartItems.size()==0) {
			%><jsp:forward page="error_cart.jsp?msg='Carrito vacío'"></jsp:forward><%
		}
		for(CartItem cartItem : cartItems) {
			DaoPurchase.addPurchase(cartItem, nick);
		}
		cart.emptyCart();
		%><jsp:forward page="buy-success.jsp?msg=Gracias por comprar en Appify"></jsp:forward><%
	}catch(Exception e) {
		String message = e.getMessage();
		%><jsp:forward page="error_cart.jsp?msg='<%=message%>'"></jsp:forward><%
	}	
}else {%>
	<jsp:forward page="error.jsp?msg='No te has autenticado'"></jsp:forward>
<%}%>
</body>
</html>