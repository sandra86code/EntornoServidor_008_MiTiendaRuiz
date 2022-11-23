<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jacaranda.control.DaoArticle" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eliminar Artículo</title>
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
					DaoArticle.deleteArticle(idArticle);
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