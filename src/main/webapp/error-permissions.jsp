<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appify - Error Permisos</title>
<link rel="stylesheet" href="css/error_style.css">
</head>
<body>
	<%HttpSession sessionsa = request.getSession();
	String login = (String) sessionsa.getAttribute("login");
	String nick = (String) sessionsa.getAttribute("nick");
	if(login != null && nick !=null && login.equals("true")){%>
		
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
						message = "";
					}
					%>
					<h2><%=message%></h2>
					<input type="button" onclick="location.href='show-articles';" value="Volver" />
				</div>
			</div>
		</div>
	<%}else {%>
	<jsp:forward page="error.jsp?msg='No te has autenticado'"></jsp:forward>
	<%}%>
</body>
</html>