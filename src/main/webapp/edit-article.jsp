<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.control.DaoArticle" %>
<%@ page import ="com.jacaranda.control.DaoCategory" %>
<%@ page import ="com.jacaranda.model.Article" %>
<%@ page import ="com.jacaranda.model.Category" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Artículo</title>
<link rel="stylesheet" href="css/grid.css">
<link rel="stylesheet" href="css/reg_style.css">
</head>
<body>
	<% 
	HttpSession sessionsa = request.getSession();
	String login = (String) sessionsa.getAttribute("login");
	String nick = (String) sessionsa.getAttribute("nick");
	String admin = (String) sessionsa.getAttribute("admin");
	if(login != null && nick !=null && login.equals("true")) {
		if(admin.equals("true")) {
			String cod = request.getParameter("cod");
			if(cod!=null) {
				try {
					int idArticle = Integer.parseInt(cod);
					Article article = DaoArticle.getArticle(idArticle);
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
						</div>
						<div class="content">
							<div class="login">
								<div class="login_title">
									<h1>Editar artículo</h1>
								</div>
								<div class="form">
									<form class="login_form" id="edit-article" action="edit-article" method="post" enctype="multipart/form-data">
										<label class="login_label" for="cod_article">Código</label>
										<input type="text" value='<%=article.getCod() %>' name="cod_article" readonly>
										<label class="login_label" for="name_article">Nombre</label>
										<input type="text" minlength="2" maxlength="50" value="<%=article.getName()%>" name="name_article" required>
										<label class="login_label" for="description_article">Descripción</label>
										<textarea minlength="2" maxlength="120" name="description_article" col="60" required><%=article.getDescription()%></textarea>
										<label class="login_label" for="price_article">Precio</label>
										<input type="number" name="price_article" min='0.01' step="0.01" value="<%=article.getPrice()%>" required><br>
										<label class="login_label" for="quantity_article">Cantidad</label>
										<input type="number" name="quantity_article" min='1' value="<%=article.getQuantity()%>" required><br>
										<label class="login_label" for="category_article">Categoría</label>
										<select name="category_article" required>
										<%
										ArrayList<Category> categories = (ArrayList<Category>)DaoCategory.getCategories();
										if(categories!=null) {
											
											Iterator<Category> it = categories.iterator();
											while(it.hasNext()){
												Category c = it.next();
												if(article.getCategory().getName().equals(c.getName())) {
													%><option value="<%=c.getCod()%>" selected><%=c.getName()%></option><%
												}else {
													%><option value="<%=c.getCod()%>"><%=c.getName()%></option><%
												}
											}
										}%>
										</select>
										<label class="login_label" for="image_article">Imagen (bmp, jpg, png)</label>
										<input type="file" name="image_article" accept=".bmp, .jpg, .png"><br>
										
										<button type="link" id="returnButton" class="return_button" onclick="location.href='show-articles'">Volver</button>
										<button type="reset" id="resetButton" class="reset_button">Resetear</button>
										<button type="submit" id="loginButton" class="submit_button">Enviar</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				<%
				}catch(Exception e) {
					String message = e.getMessage();
					%><jsp:forward page="error.jsp?redirect=show-articles&msg=<%=message%>"></jsp:forward><%
				}
			}else {
				%><jsp:forward page="error.jsp?redirect=show-articles&msg=Error. No existe el código de artículo"></jsp:forward><%
			}
		}else {
			%><jsp:forward page="error.jsp?redirect=show-articles&msg=No eres administrador"></jsp:forward><%
		}
	}else {
		%><jsp:forward page="error.jsp?redirect=index&msg=No te has autenticado"></jsp:forward><%
	}%>
	<script src="script/edit-article.js"></script>
</body>
</html>