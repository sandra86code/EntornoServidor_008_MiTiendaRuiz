<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appify - Login</title>
<link rel="stylesheet" href="css/error_style.css">
</head>
<body>
	<%HttpSession sessionsa = request.getSession();
	String login = (String) sessionsa.getAttribute("login");
	String nick = (String) sessionsa.getAttribute("nick");
	String admin = (String) sessionsa.getAttribute("admin");
	if(login != null && nick !=null && login.equals("true")) {
		if(admin.equals("true")) {%>
		<div class="wrapper">
			<div class="close_session">
			</div>
			<div class="page_name">
				<img src="img/logo.png"/>
			</div>
			<div class="user_name">
			</div>
			<div class="content">
				<div class="error">
					<h1>Oops!</h1>
					<%String message = request.getParameter("msg");
					if (message==null) {
						message = "Datos incorrectos";
					}%>
					<h2><%=message%></h2>
					<input type="button" onclick="location.href='add-article.jsp';" value="Volver" />
				</div>
			</div>
		</div>
		<%}else {%>
			<jsp:forward page="error-permissions.jsp?msg='No eres administrador'"></jsp:forward>
		<%}
	}else {%>
		<jsp:forward page="error.jsp?msg='No te has autenticado'"></jsp:forward>
<%}%>	
</body>
</html>