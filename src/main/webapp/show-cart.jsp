<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.control.DaoArticle" %>
<%@ page import ="com.jacaranda.control.DaoUser" %>
<%@ page import ="com.jacaranda.cart.Cart" %>
<%@ page import ="com.jacaranda.model.Article" %>
<%@ page import ="com.jacaranda.model.User" %>
<%@ page import ="com.jacaranda.cart.CartItem" %>
<%@ page import ="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show cart</title>
<link rel="stylesheet" href="css/grid.css">
<link rel="stylesheet" href="css/show-cart.style.css">
</head>
<body>
	<% 
	HttpSession sessionsa = request.getSession();
	String login = (String) sessionsa.getAttribute("login");
	String nick = (String) sessionsa.getAttribute("nick");
	Cart cart = (Cart) sessionsa.getAttribute("cart");
	String admin = (String) sessionsa.getAttribute("admin");
	if(login != null && nick !=null && login.equals("true") && cart!=null) {
		if(admin.equals("false")) {%>
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
						<h1>Mi carrito</h1>
					</div>
					<div class="grid_container"><%
					ArrayList<CartItem> items = cart.getCartItems();
					if(items==null || items.size()==0) {%>
						</div>
						<div class="gallery-articles-price">
							<p><b>TOTAL CARRITO: </b><%= cart.getTotalPrice()%> €</p>
						
							<div class="gallery-articles-price-buttons">
								<button type="link" id="returnButton" class="return_button" onclick="location.href='show-articles'">Seguir comprando</button>
							</div>
						</div>
					<%}else{
						for(CartItem cartItem : items) {
							Article article = cartItem.getArticle();
							int cod = cartItem.getArticle().getCod();%>
							<div class="gallery_item">
								<div class="gallery-article-foto">
									<%if(article.getImage()==null){%>
										<img class="photo" src="img/nophoto.jpg"/>
									<%}else {%>
										<img class="photo" src='./download-image?cod=<%= cod %>'/><%
									}%>
								</div>
								<div class="gallery-article-info">
									<p class="article-title"><%= article.getName() %></p>
									<p><b>Descripcion: </b><%= article.getDescription() %></p>
									<p><b>Categoría: </b><%= article.getCategory().getName() %></p>
									<p><b>Precio del artículo: </b><%= article.getPrice() %>€</p>
									<p><b>Cantidad: </b><%= cartItem.getQuantity()%></p>
									<hr>
									<form class="modify-article-cart" action="modify-quantity-article.jsp" method="post">
										<label for="quantity" class="modify_quantity"><b>Modificar cantidad: </b></label>
										<input type="number" class="modify_quantity" name="quantity" min="1" max='<%=article.getQuantity()%>' value="<%=cartItem.getQuantity()%>" required>
										<input type="hidden" name="id_article" value='<%=cod %>'>
										<input type="hidden" name="id_item" value='<%=items.indexOf(cartItem)%>'>
										<button type="submit" class="modify_quantity">Modificar</button>
									</form>
								</div>
								<div class="gallery-article-delete">
									<form class="delete-article-cart" action="delete-article-cart.jsp" method="post">
										<input type="hidden" name="id_item" value='<%=items.indexOf(cartItem) %>'>
										<button type="submit" class="login_button">Eliminar artículo</button>
									</form>
								</div>
								<div class="gallery-article-price">
									<p class="partial-price"><b>Total: </b><%= cartItem.getPriceItem()%>€</p>
								</div>
							</div>
						<%
						}%>
						</div>
						<div class="gallery-articles-price">
							<p><b>TOTAL CARRITO: </b><%= cart.getTotalPrice()%> €</p>
						
							<div class="gallery-articles-price-buttons">
								<button type="link" id="returnButton" class="return_button" onclick="location.href='show-articles'">Seguir comprando</button>
								<button type="link" id="resetButton" class="emtpy_button" onclick="location.href='empty-chart.jsp'">Vaciar carrito</button>
								<button type="submit" id="loginButton" class="login_button" onclick="location.href='buy-articles.jsp'" >Comprar</button>
							</div>
						</div>
						<%
					}%>
				</div>
			</div>
		<%}else{%>
			<jsp:forward page="error.jsp?redirect=show-articles&msg=No tienes carrito"></jsp:forward><%
		}
	}else {%>
		<jsp:forward page="error.jsp?redirect=index&msg=No te has autenticado"></jsp:forward>
	<%}%>
</body>
</html>