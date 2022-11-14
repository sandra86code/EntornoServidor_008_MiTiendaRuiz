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
	HttpSession session = request.getSession();
	String isSession = (String) session.getAttribute("login");
	String userSession = (String) session.getAttribute("nick");
	if(isSession != null && userSession !=null && isSession.equals("True")){
		
		String genre = request.getParameter("genre");
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
	}else {%>
	<jsp:forward
		page="error.jsp?msg='No te has autenticado'"></jsp:forward>
<%}%>
</body>
</html>