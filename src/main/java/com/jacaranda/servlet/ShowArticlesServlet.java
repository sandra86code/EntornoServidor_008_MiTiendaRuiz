package com.jacaranda.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.jacaranda.control.DaoArticle;
import com.jacaranda.control.DaoException;
import com.jacaranda.control.DaoUser;
import com.jacaranda.model.Article;
import com.jacaranda.model.User;

/**
 * Servlet implementation class ShowArticlesServlet
 */
@WebServlet("/show-articles")
public class ShowArticlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowArticlesServlet() {
        super();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		HttpSession session = request.getSession(); //Recupero sesión
		String nick = (String) session.getAttribute("nick"); //Obtengo el nick del usuario de la sesión
		String admin = (String) session.getAttribute("admin"); //Obtengo si el usuario de la sesión es admin o no
		boolean isAdmin = false;
		String encriptedPassword = null;
		response.setContentType("text/html;charset=UTF-8"); //Encabezados para la respuesta
		response.setCharacterEncoding("UTF-8");
		
		
		if(nick==null) { //No hay una sesión abierta. Primera vez que entra
			nick = request.getParameter("nick"); //Recupero nick del login
			String password = request.getParameter("password"); //Recupero contraseña del login
			
			if(nick==null || nick.isBlank() || nick.isEmpty() || 
					password == null || password.isBlank() || password.isEmpty() ) {
				response.sendRedirect("error.jsp?msg=Nick incorrecto"); //Datos de logueo incorrectos según restricciones
			}else {
				encriptedPassword = DigestUtils.md5Hex(password);
			}
		}else { //Existe una session abierta
			isAdmin = Boolean.parseBoolean(admin);
			log(isAdmin);
		}
		
		//Variables para la página web
		String htmlPart1 = "<html>"
				+ "<head>"
				+ "<title>Appify</title>"
				+ "<link rel=\"stylesheet\" href=\"css/table_style.css\">"
				+ "</head>"
				+ "<body>"
				+ "<div class=\"wrapper\">"
				+ "<div class=\"close_session\">"
				+ "<input type=\"button\" onclick=\"location.href='index.jsp';\" value=\"Volver al login\" />"
				+ "</div>"
				+ "<div class=\"page_name\">"
				+ "<img src=\"img/logo.png\"/>"
				+ "</div>"
				+ "<div class=\"user_name\">"
				+ "<p>Usuario: " + nick + "</p>";
		String buttonAdmin = "<div class=\"admin_button\">"
				+ "<input type=\"button\" onclick=\"location.href='add-article.jsp';\" value=\"Añadir artículo\" />"
				+ "</div><br>";
		String htmlPart3User = "<div class=\"table\">"
				+ "<table>"
				+ "<thead>"
				+ "<tr>"
				+ "<th>Código</th>"
				+ "<th>Nombre</th>"
				+ "<th>Descripción</th>"
				+ "<th>Precio</th>"
				+ "<th>Categoría</th>"
				+ "<th>Comprar</th>"
				+ "</tr>"
				+ "</thead>"
				+ "<tbody>";
		String htmlPart3Admin = "<div class=\"table\">"
				+ "<table>"
				+ "<thead>"
				+ "<tr>"
				+ "<th>Código</th>"
				+ "<th>Nombre</th>"
				+ "<th>Descripción</th>"
				+ "<th>Precio</th>"
				+ "<th>Categoría</th>"
				+ "<th>Editar</th>"
				+ "<th>Eliminar</th>"
				+ "</tr>"
				+ "</thead>"
				+ "<tbody>";
		String htmlPart4 = "</tbody></table></div></div></div></body></html>";
		
		
		//Guardo los artículos
		ArrayList<Article> articles = null;
		StringBuilder tdUser = new StringBuilder();
		StringBuilder tdAdmin = new StringBuilder();
		try {
			articles = DaoArticle.getArticles();
			if(articles!=null) {
				Iterator<Article> it = articles.iterator();
				while(it.hasNext()){
					Article a = it.next();
					tdUser.append("<tr>"
							+ "<td>" + a.getCod() + "</td>"
							+ "<td>" + a.getName() + "</td>"
							+ "<td>" + a.getDescription() + "</td>"
							+ "<td>" + a.getPrice() + " €</td>"
							+ "<td>" + a.getCategory().getName() + "</td>"
							+ "<td><a href=\"buy-article.jsp?cod=" + a.getCod() + "\"><img src=\"img/shop.png\" class=\"shop\"/></td>"
							+ "</tr>");
					tdAdmin.append("<tr>"
							+ "<td>" + a.getCod() + "</td>"
							+ "<td>" + a.getName() + "</td>"
							+ "<td>" + a.getDescription() + "</td>"
							+ "<td>" + a.getPrice() + " €</td>"
							+ "<td>" + a.getCategory().getName() + "</td>"
							+ "<td><a href=\"edit-article.jsp?cod=" + a.getCod() + "\"><img src=\"img/edit.png\" class=\"shop\"/></td>"
							+ "<td><a href=\"delete-article.jsp?cod=" + a.getCod() + "\"><img src=\"img/delete.png\" class=\"shop\"/></td>"
							+ "</tr>");	
				}
			}
			
			
			if(encriptedPassword!=null) { //Si es la primera vez que el usuario entra
				
				try {
					boolean isUserValid = DaoUser.userIsValid(nick, encriptedPassword); //Compruebo que el usuario es válido
					if(isUserValid) {
						isAdmin = DaoUser.isUserAdmin(nick);
						admin = String.valueOf(isAdmin);
						session.setAttribute("nick", nick); //Añado variables de sesión
						session.setAttribute("admin", admin);
						session.setAttribute("login", "true");
						
					}else {
						response.sendRedirect("error.jsp?msg=Password incorrecto"); //Password no corresponde con la bbdd
					}
				}catch(Exception e) {
					String message = e.getMessage();
					response.sendRedirect("error.jsp?msg=" + message);
				}
			}
			String role = "";
			if(isAdmin) {
				role = "admin";
			}else {
				role = "user";
			}
			
			String htmlPart2 = "<p>Rol: " + role + "</p>"
					+ "</div>"
					+ "<div class=\"content\">";
			if(isAdmin) { //Administrador
				if(tdAdmin.toString().equals("")) { //No hay artículos
					response.getWriter().append(htmlPart1 + htmlPart2 + buttonAdmin + htmlPart3Admin + htmlPart4);
				}else {	//Hay artículos
					response.getWriter().append(htmlPart1 + htmlPart2 + buttonAdmin + htmlPart3Admin + tdAdmin.toString() + htmlPart4);
				}
			}else { //Usuario no admin
				
				if(tdUser.toString().equals("")) { //No hay artículos
					response.getWriter().append(htmlPart1 + htmlPart2 + htmlPart3User + htmlPart4);
				}else {
					response.getWriter().append(htmlPart1 + htmlPart2 + htmlPart3User + tdUser.toString() + htmlPart4);
				}
			}
		}catch(Exception e) {
			String message = e.getMessage();
			response.sendRedirect("error.jsp?msg=" + message); 
		}
	}

	private void log(boolean isAdmin) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
