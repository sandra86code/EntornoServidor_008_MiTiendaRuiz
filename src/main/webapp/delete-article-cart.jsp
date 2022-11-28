<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.cart.Cart" %>
<%@ page import ="com.jacaranda.cart.CartItem" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eliminar artículo del carrito</title>
</head>
<body>
<% 
HttpSession sessionsa = request.getSession();
String login = (String) sessionsa.getAttribute("login");
String nick = (String) sessionsa.getAttribute("nick");
Cart cart = (Cart) sessionsa.getAttribute("cart");
if(login != null && nick !=null && login.equals("true") && cart!=null) {
	String index = request.getParameter("id_item");
	if(index!=null){
		try {
			int indexItem = Integer.parseInt(index);
			CartItem cartItem = cart.getCartItems().get(indexItem);
			cart.deleteCartItem(cartItem);
			%>
			<jsp:forward page="show-cart.jsp"></jsp:forward>
			<%
		}catch(Exception e) {
			String message = e.getMessage();
			%><jsp:forward page="error.jsp?redirect=show-cart&msg=<%=message%>"></jsp:forward><%
		}	
	}
}else {%>
	<jsp:forward page="error.jsp?redirect=index&msg=No te has autenticado"></jsp:forward>
<%}%>
</body>
</html>