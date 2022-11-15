<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appify - Login</title>
<link rel="stylesheet" href="css/login_style.css">
</head>
<body>
<% session.invalidate(); %>
	
	<div class="wrapper">
		<div class="close_session">
		</div>
		<div class="page_name">
			<img src="img/logo.png"/>
		</div>
		<div class="user_name">
		</div>
		<div class="content">
			<div class="login">
				<div class="login_title">
					<h1>Entrar</h1>
				</div>
				<div class="form">
					<form class="login_form" id="loginForm" action="show-articles" method="post">
						<label class="login_label" for="nick">Usuario</label>
						<input type="text" minlength="5" maxlength="20" placeholder="Introduce tu nombre de usuario" name="nick" required>
						<label class="login_label" for="user">Contraseña</label>
						<input type="password" minlength="5" maxlength="20" placeholder="Introduce tu contraseña" name="password" required>
						<button type="submit" id="loginButton" class="login_button">Enviar</button>
						<button type="reset" id="resetButton" class="login_button">Borrar</button>
						<div class="register_link">
							<a href="registration.jsp">¿No tienes cuenta? REGÍSTRATE</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>