<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.control.DaoArticle" %>
<%@ page import ="com.jacaranda.control.DaoCategory" %>
<%@ page import ="com.jacaranda.model.Article" %>
<%@ page import ="com.jacaranda.model.Category" %>
<%@ page import ="com.jacaranda.control.ConnectionDB" %>
<%@ page import ="java.io.IOException" %>
<%@ page import ="java.io.InputStream" %>
<%@ page import ="java.sql.Blob" %>
<%@ page import ="org.hibernate.Hibernate" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Persistir modificación artículo</title>
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
			String cId = request.getParameter("category");
			String name = request.getParameter("name");
			String description = request.getParameter("newdescription");
			String p = request.getParameter("price");
			String q = request.getParameter("quantity");
			//Consigo la foto del formulario mediante el objeto Part
			Part part = request.getPart("image");
			if(cod!=null || cId == null || name == null || description == null || p == null || q==null) {
				%><jsp:forward page="error-permissions.jsp?msg=Valores incorrectos."></jsp:forward><%
				try {
					int idArticle = Integer.parseInt(cod);
					Category category = DaoCategory.getCategory(Integer.parseInt(cId));
					double price = Double.parseDouble(p);
					int quantity = Integer.parseInt(q);
					Article modifiedArticle = new Article(name, description, price, quantity, category);
					if(part!=null) {
						//Conseguimos los bytes de la imagen
						InputStream fileContent = part.getInputStream();
						//Conseguimos un creador de datos blog para esta sesión y creamos un blob con la imagen
						ConnectionDB connection = new ConnectionDB();
						Blob blob = Hibernate.getLobCreator(connection.getSession()).createBlob(fileContent, 
								fileContent.available());
						modifiedArticle.setImage(blob);
					}
					//Modifico el artículo en la BBDD
					DaoArticle.editArticle(idArticle, modifiedArticle);
					%><jsp:forward page="show-articles"></jsp:forward><%
				}catch(Exception e) {
					String message = e.getMessage();
					%><jsp:forward page="error-permissions.jsp?msg=<%=message%>"></jsp:forward><%
				}
				
			}else {
				%><jsp:forward page="error-permissions.jsp?msg=Error. No existe el código de artículo"></jsp:forward><%
			}
		}else {%>
			<jsp:forward page="error-permissions.jsp?msg='No eres administrador'"></jsp:forward>
		<%}
	}else {%>
		<jsp:forward page="error.jsp?msg='No te has autenticado'"></jsp:forward>
<%}%>

</body>
</html>