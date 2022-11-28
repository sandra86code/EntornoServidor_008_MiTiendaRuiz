<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.control.DaoArticle" %>
<%@ page import ="com.jacaranda.cart.Cart" %>
<%@ page import ="com.jacaranda.model.Article" %>
<%@ page import ="com.jacaranda.cart.CartItem" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add article to cart</title>
</head>
<body>
	<% 
	HttpSession sessionsa = request.getSession();
	String login = (String) sessionsa.getAttribute("login");
	String nick = (String) sessionsa.getAttribute("nick");
	Cart myCart = (Cart) sessionsa.getAttribute("cart");
	if(login != null && nick !=null && login.equals("true") && myCart != null) {
		String id_article = request.getParameter("id_article");
		String q = request.getParameter("quantity");
		if(id_article!=null && q!=null) {
			try {
				int cod = Integer.parseInt(id_article);
				int quantity = Integer.parseInt(q);
				Article article = DaoArticle.getArticle(cod);
				int maxStockArticle = article.getQuantity();
				if(quantity<=maxStockArticle) {
					CartItem cartItem = new CartItem(article, quantity);
					myCart.addCartItem(cartItem);
					%><jsp:forward page="show-articles"></jsp:forward><%
				}else {
					%><jsp:forward page="error.jsp?redirect=show-articles&msg='La cantidad es mayor que el stock'"></jsp:forward><%
				}
			}catch(Exception e) {
				String message = e.getMessage();
				%><jsp:forward page="error.jsp?redirect=show-articles&msg=<%=message%>"></jsp:forward><%
			}
		}else {%>
			<jsp:forward page="error.jsp?redirect=show-articles&msg=Datos erróneos"></jsp:forward><%
		}
	}else {%>
		<jsp:forward page="error.jsp?redirect=index&msg='No te has autenticado'"></jsp:forward>
	<%}%>
</body>
</html>