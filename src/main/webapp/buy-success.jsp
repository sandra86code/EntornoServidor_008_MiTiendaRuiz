<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appify - Compra exitosa</title>
<link rel="stylesheet" href="css/grid.css">
<link rel="stylesheet" href="css/error_style.css">
</head>
<body>
	<%
	HttpSession sessionsa = request.getSession();
	String login = (String) sessionsa.getAttribute("login");
	String nick = (String) sessionsa.getAttribute("nick");
	String admin = (String) sessionsa.getAttribute("admin");
	if(login != null && nick !=null && login.equals("true")) {
		if(admin.equals("false")) {%>
			<div class="wrapper">
				<div class="close_session">
					<a href='index.jsp'><img src='img/close-session.png' class='close-button'/></a>
				</div>
				<div class="page_name">
					<img src="img/logo.png"/>
				</div>
				<div class="user_name">
				</div>
				<div class="content">
					<div class="error">
						<%String message = request.getParameter("msg");
						if (message==null) {
							message = "";
						}%>
						<h1><%=message%></h1>
						<input type="button" onclick="location.href='show-articles';" value="Volver a los artículos" />
					</div>
			
				</div>
			</div>
	<%}else {
		%><jsp:forward page="error.jsp?redirect=show-articles&msg=No tienes carrito"></jsp:forward><%
	}
}else {%>
	<jsp:forward page="error.jsp?redirect=index&msg=No te has autenticado"></jsp:forward>
<%}%>
</body>
</html>