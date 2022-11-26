<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appify - Error</title>
<link rel="stylesheet" href="css/grid.css">
<link rel="stylesheet" href="css/error_style.css">
</head>
<body>
	
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
				<%String redirect = request.getParameter("redirect");
				if(redirect.equals("show-articles")) {
					%><input type="button" onclick="location.href='show-articles';" value="Volver" /><%
				}else {
					%><input type="button" onclick="location.href='<%=redirect%>.jsp';" value="Volver" /><%
				}%>
			</div>
		</div>
	</div>
</body>
</html>