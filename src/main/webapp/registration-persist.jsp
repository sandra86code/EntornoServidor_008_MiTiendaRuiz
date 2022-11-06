<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.jacaranda.control.DaoUser"%>
<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appify - Registro</title>
</head>
<body>
	<%
	String nick = request.getParameter("nick");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String surname = request.getParameter("surname");
	String date = request.getParameter("birth_date");
	String s = request.getParameter("sex");
	boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
	if(nick!=null && !nick.isEmpty() && !nick.isBlank() && password!=null && !password.isEmpty() && !password.isBlank() 
			&& name!=null && !name.isEmpty() && !name.isBlank() && surname!=null && !surname.isEmpty() && !surname.isBlank()
			&& date!=null && s!=null){
		char sex = s.charAt(0);
		String encriptedPassword = DigestUtils.md5Hex(password);
		LocalDate birthDate = LocalDate.parse(date);
		try {
			DaoUser.addUser(nick, encriptedPassword, name, surname, birthDate, sex, admin);
			%>
			<jsp:forward page="index.jsp"></jsp:forward>
			<%
		}catch(Exception e) {
			String message = e.getMessage();
			%>
			<jsp:forward page="regError.jsp"><jsp:param name="msg" value="<%=message%>"/></jsp:forward>
			<%
		}
	}else {
		%>
		<jsp:forward page="regError.jsp"><jsp:param name="msg" value="Datos incorrectos"/></jsp:forward>
		<%
	}
	%>
</body>
</html>