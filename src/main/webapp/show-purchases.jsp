<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.model.Article" %>
<%@ page import ="com.jacaranda.model.Purchase" %>
<%@ page import ="com.jacaranda.cart.Cart" %>
<%@ page import ="com.jacaranda.control.DaoPurchase" %>

<%@ page import ="com.jacaranda.control.DaoUser" %>
<%@ page import ="com.jacaranda.model.User" %>

<%@ page import ="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver compras de usuario</title>
<link rel="stylesheet" href="css/grid.css">
<link rel="stylesheet" href="css/buy-articles_style.css">
</head>
<body>
<% 
HttpSession sessionsa = request.getSession();
String login = (String) sessionsa.getAttribute("login");
String nick = (String) sessionsa.getAttribute("nick");
String admin = (String) sessionsa.getAttribute("admin");
Cart cart = (Cart) sessionsa.getAttribute("cart");
if(login != null && nick !=null && login.equals("true") && cart!=null) {
	if(admin.equals("false")) {
		try {
			/* User user = DaoUser.getUser(nick);
			ArrayList<Purchase> purchases = (ArrayList<Purchase>)user.getPurchases(); */
			ArrayList<Purchase> purchases = (ArrayList<Purchase>)DaoPurchase.getPurchases(nick);
			if(purchases.size()==0) {
				%><jsp:forward page="error.jsp?redirect=show-articles&msg=Aun no ha realizado ninguna compra"></jsp:forward><%
			}else {
			
				%>
				<div class="wrapper">
					<div class="close_session">
						<a href='index.jsp'><img src='img/close-session.png' class='close-button'/></a>
					</div>
					<div class="page_name">
						<img src="img/logo.png"/>
					</div>
					<div class="user_name">
						<div class="usericon">
							<img class="icon" src="img/user.png"/>
							<p> <%=nick %> </p>
						</div>
						<div class="charticon">
							<a href="show-cart.jsp">
								<img class="icon" src="img/trolley.png">
							</a>
							<p> <%=cart.getQuantityArticles() %> </p>
						</div>
					</div>
					<div class="content">
						<div class="page-title">
							<h1>Mis compras</h1>
						</div>
						<div class="grid_container">
							<table>
								<thead>
									<tr>
										<th>Fecha de la compra</th>
										<th>Nombre del artículo</th>
										<th>Precio del artículo</th>
										<th>Cantidad</th>
										<th>Precio total de la compra</th>
									</tr>
								</thead>
								<tbody>
								<%
								for(Purchase purchaseItem : purchases) {
									%><tr>
										<td><%=String.valueOf(purchaseItem.getDate()).replace('T', ' ') %></td>
										<td><%=purchaseItem.getArticle().getName() %></td>
										<td><%=purchaseItem.getPrice()%> €</td>
										<td><%=purchaseItem.getQuantity()%></td>
										<td><%=purchaseItem.getTotalPrice()%> €</td>
									<tr><%
								}%>
									</tbody>
								</table>
								<div class="return-buttons">
									<input type="button" class="return_button" onclick="location.href='show-articles';" value="Volver a los artículos" />
								</div>
							</div>
						</div>
					</div><%
			}
		}catch(Exception e) {
			String message = e.getMessage();
			%><jsp:forward page="error.jsp?redirect=show-articles&msg=<%=message%>"></jsp:forward><%
		}
	}else {
		%><jsp:forward page="error.jsp?redirect=show-articles&msg=No eres usuario, eres administrador"></jsp:forward><%
	}
}else {%>
	<jsp:forward page="error.jsp?redirect=index&msg=No te has autenticado"></jsp:forward>
<%}%>
<script src="script/buy-articles.js"></script>
</body>
</html>