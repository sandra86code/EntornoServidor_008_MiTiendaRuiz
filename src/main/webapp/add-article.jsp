<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="javax.servlet.http.HttpSession" %>
<%@ page import ="com.jacaranda.model.Category" %>
<%@ page import ="com.jacaranda.control.DaoCategory" %>
<%@ page import ="java.util.Iterator" %>
<%@ page import ="java.util.ArrayList" %>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appify - Añadir producto</title>
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
		if(admin.equals("true")) {%>
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
						<h1>Añadir artículo</h1>
					</div>
					<div class="form">
						<form class="login_form" id="loginForm" action="add-article" method="post" enctype="multipart/form-data">
							<label class="login_label" for="category">Categoría*</label>
							<select name="category" required>
							<%
							ArrayList<Category> categories = (ArrayList<Category>)DaoCategory.getCategories();
							if(categories!=null) {
								%>
								<option disabled selected>--Elige una categoría--</option>
								<%
								Iterator<Category> it = categories.iterator();
								while(it.hasNext()){
									Category c = it.next();
									%><option value="<%=c.getCod()%>"><%=c.getName()%></option><%
								}
							}%>
							</select>
							<label class="login_label" for="name">Nombre*</label>
							<input type="text" minlength="2" maxlength="50" placeholder="Introduce el nombre del artículo" name="name" required>
							<label class="login_label" for="description">Descripción*</label>
							<textarea minlength="2" maxlength="120" name="description" cols="45" required></textarea>
							<label class="login_label" for="price">Precio*</label>
							<input type="number" name="price" min='0.01' step="0.01" required><br>
							<label class="login_label" for="quantity">Cantidad*</label>
							<input type="number" name="quantity" min='1' required><br>
							<label class="login_label" for="image">Imagen (bmp, jpg, png)</label>
							<input type="file" name="image" accept=".bmp, .jpg, .png"><br>
							
							<input type="button" onclick="location.href='show-articles';" value="Volver" />
							<button type="reset" id="resetButton" class="login_button">Borrar</button>
							<button type="submit" id="loginButton" class="login_button">Enviar</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%}else {%>
			<jsp:forward page="error.jsp?redirect=show-articles&msg=No eres administrador"></jsp:forward>
		<%}
	}else {%>
		<jsp:forward page="error.jsp?redirect=index&msg=No te has autenticado"></jsp:forward>
<%}%>

</body>
</html>