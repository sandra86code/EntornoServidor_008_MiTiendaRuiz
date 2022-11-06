<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appify - Registro</title>
<link rel="stylesheet" href="css/reg_style.css">

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
					<h1>Registrarse</h1>
				</div>
				<div class="form">
					<form class="login_form" id="loginForm" action="registration-persist.jsp" method="post">
						<label class="login_label" for="nick">Usuario</label>
						<input type="text" minlength="3" maxlength="20" placeholder="Introduce un nombre de usuario" name="nick" required>
						<label class="login_label" for="password">Contraseña</label>
						<input type="password" minlength="5" maxlength="20" placeholder="Introduce una contraseña" name="password" required>
						<label class="login_label" for="name">Nombre</label>
						<input type="text" minlength="2" maxlength="30" placeholder="Introduce tu nombre" name="name" required>
						<label class="login_label" for="surname">Apellidos</label>
						<input type="text" minlength="2" maxlength="60" placeholder="Introduce tu/s apellido/s" name="surname" required>
						<label class="login_label" for="birth_date">Fecha de nacimiento</label>
						<input type="date" name="birth_date" min='1899-01-01' max='2000-13-13' required><br>
						<label class="login_label" for="sex">Sexo</label>
						<select name="sex" id="sex" required>
                            <option disabled selected>--Elige tu sexo--</option>
                            <option value="M">Mujer</option>
                            <option value="H">Hombre</option>
                        </select><br>
                        <input type="hidden" name="admin" value="false">
						<button type="submit" id="loginButton" class="login_button">Enviar</button>
						<button type="reset" id="resetButton" class="login_button">Borrar</button>
						<input type="button" onclick="location.href='index.jsp';" value="Volver al login" />
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>