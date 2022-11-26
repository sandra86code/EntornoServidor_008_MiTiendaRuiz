<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.cart.Cart" %>
<%@ page import ="com.jacaranda.cart.CartItem" %>
<%@ page import ="com.jacaranda.control.DaoArticle" %>
<%@ page import ="com.jacaranda.model.Article" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Quantity Article</title>
</head>
<body>
<% 
HttpSession sessionsa = request.getSession();
String login = (String) sessionsa.getAttribute("login");
String nick = (String) sessionsa.getAttribute("nick");
Cart cart = (Cart) sessionsa.getAttribute("cart");
if(login != null && nick !=null && login.equals("true") && cart!=null) {
	String id = request.getParameter("id_article");
	String q = request.getParameter("quantity");
	String index = request.getParameter("id_item");
	if(id!=null && q!=null && index!=null){
		try {
			int idArticle = Integer.parseInt(id);
			int quantity = Integer.parseInt(q);
			int indexItem = Integer.parseInt(index);
			CartItem cartItem = cart.getCartItems().get(indexItem);
			int maxStockItem = DaoArticle.getArticle(idArticle).getQuantity();
			cart.modifyQuantityCartItem(cartItem, quantity, maxStockItem);%>
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