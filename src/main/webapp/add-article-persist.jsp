<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="javax.servlet.http.HttpSession" %>
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
	HttpSession sessionsa = request.getSession();
	String login = (String) sessionsa.getAttribute("login");
	String nick = (String) sessionsa.getAttribute("nick");
	String admin = (String) sessionsa.getAttribute("admin");
	if(login != null && nick !=null && login.equals("true")){
		if(admin.equals("true")) {
			String cId = request.getParameter("category");
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String p = request.getParameter("price");
			String image = request.getParameter("image");
			
			if(nick==null || cId == null || name == null || description == null || p == null || image == null) {
				%>
				<jsp:forward page="add-article-error.jsp"> 
					 <jsp:param name="msg" value="Valores incorrectos. Vuelve a intentarlo"/>
				</jsp:forward>
				<%
			}else {
				try {
					Category category = DaoCategory.getCategory(Integer.parseInt(cId));
					double price = Double.parseDouble(p);
					if(image.contains(".")) {
						String[] parts = image.split(Pattern.quote("."));
						String imageExtension = parts[parts.length-1];
						boolean result = DaoArticle.addArticle(name, description, price, imageExtension, category);
						if(result) {
							%><jsp:forward page="show-articles"></jsp:forward><%
						}
					}else {
						%><jsp:forward page="add-article-error.jsp"> 
							 <jsp:param name="msg" value="La imagen no tiene extensión"/>
						</jsp:forward><%
					}
				}catch (Exception e) {
					String message = e.getMessage();
					%><jsp:forward page="add-article-error.jsp">
						 <jsp:param name="msg" value="<%=message%>"/>
					</jsp:forward><%
				}
			}
		}else {%>
			<jsp:forward page="error-permissions.jsp?msg='No eres administrador'"></jsp:forward>
		<%}
	}else {%>
		<jsp:forward page="error.jsp?msg='No te has autenticado'"></jsp:forward>
	<%}%>
</body>
</html>