<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appify - Compra exitosa</title>
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
				<%String message = request.getParameter("msg");
				if (message==null) {
					message = "";
				}%>
				<h1><%=message%></h1>
				<input type="button" onclick="location.href='show-articles';" value="Volver a los artículos" />
			</div>
	
		</div>
	</div>
</body>
</html>