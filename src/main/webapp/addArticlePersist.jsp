<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.model.Category" %>
<%@ page import ="com.jacaranda.control.DaoArticle" %>
<%@ page import ="com.jacaranda.control.DaoCategory" %>
<%@ page import ="com.jacaranda.control.DaoException" %>
<%@ page import ="com.jacaranda.model.Article" %>
<%@ page import ="java.util.regex.Pattern" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appify - Guardar producto en BBDD</title>
<link rel="stylesheet" href="css/addarticle_style.css">

</head>
<body>
	<%
	String nick = request.getParameter("nick");
	String cId = request.getParameter("category");
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	String p = request.getParameter("price");
	String image = request.getParameter("image");
	if(nick==null || cId == null || name == null || description == null || p == null || image == null) {
		%>
		<jsp:forward page="index.jsp" /> 
			 <jsp:param name="msg" value="Valores incorrectos. Vuelve a intentarlo."/>
		</jsp:forward>
		<%
	}else {
		try {
			Category category = DaoCategory.getCategory(Integer.parseInt(cId));
			int price = Integer.parseInt(p);
			if(image.contains(".")) {
				String[] parts = image.split(Pattern.quote("."));
				String imageExtension = parts[parts.length-1];
				Article article = new Article(name, description, price, imageExtension, category);
				boolean result = DaoArticle.addArticle(article, category);
				if(result) {
					%>
					<jsp:forward page="ShowArticles" />
						<jsp:param name="nick" value="<%=nick%>"/>
					</jsp:forward>
					<%
				}
			}else {
				%>
				<jsp:forward page="index.jsp" /> 
					 <jsp:param name="msg" value="La imagen no tiene extensión"/>
				</jsp:forward>
				<%
			}
		}catch (Exception e) {
			String message = e.getMessage();
			%>
			<jsp:forward page="index.jsp" /> 
				 <jsp:param name="msg" value="<%=message%>"/>
			</jsp:forward>
			<%
		}
		
	}
	
	

	%>
	<div class="wrapper">
		<div class="close_session">
			<input type="button" onclick="location.href='index.jsp';" value="Volver al login" />
		</div>
		<div class="page_name">
			<img src="img/logo.png"/>
		</div>
		<div class="user_name">
			<%-- <%
			String nick = request.getParameter("nick");
			if(nick!=null) {
				%>
				<p>Usuario: <%=nick%></p>
				<p>Rol: <i>admin</i></p>
			<%
			}
			%> --%>
		</div>
		
		
		<div class="content">
			<div class="login">
				<div class="login_title">
					<h1>Añadir artículo</h1>
				</div>
				<div class="table">
					<%
					
					
					
					%>
					
					
					<form class="login_form" id="loginForm" action="addArticlePersist.jsp" method="post">
						<label class="login_label" for="category">Categoría</label>
						<select name="category">
						
						</select>
						<label class="login_label" for="name">Nombre del artículo</label>
						<input type="text" minlength="2" maxlength="50" placeholder="Introduce el nombre del artículo" name="name" required>
						<label class="login_label" for="description">Descripción del artículo</label>
						<textarea minlength="2" maxlength="120" name="description" required></textarea>
						<label class="login_label" for="price">Precio del artículo</label>
						<input type="number" name="price" min='0.01' step="0.01" required><br>
						<label class="login_label" for="image">Imagen (bmp, jpg, png)</label>
						<input type="file" name="image" accept=".bmp, .jpg, .png" required><br>
						<button type="submit" id="loginButton" class="login_button">Enviar</button>
						<button type="reset" id="resetButton" class="login_button">Borrar</button>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>