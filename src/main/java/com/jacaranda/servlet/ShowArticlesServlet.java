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

import com.jacaranda.control.DaoArticle;
import com.jacaranda.control.DaoException;
import com.jacaranda.control.DaoUser;
import com.jacaranda.model.Article;

/**
 * Servlet implementation class ShowArticlesServlet
 */
@WebServlet("/ShowArticles")
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
		String nick = request.getParameter("nick");
		HttpSession session = request.getSession();
		if(nick==null || nick.isBlank() || nick.isEmpty()) {
			response.sendRedirect("error.jsp?msg=Nick incorrecto");
		}else {
			session.setAttribute("nick", nick);
			session.setAttribute("login", "true");
			
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
					+ "<p>Usuario: " + nick + "</p>"
					+ "</div>"
					+ "<div class=\"content\">";
			String buttonAdmin = "<div class=\"admin_buttons\">"
					+ "<input type=\"button\" onclick=\"location.href='addArticle.jsp?nick=" + nick + "';\" value=\"Añadir artículo\" />"
					+ "</div><br>";
			String htmlPart2User = "<div class=\"table\">"
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
			String htmlPart2Admin = "<div class=\"table\">"
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
			String htmlPart3 = "</tbody></table></div></div></div></body></html>";
			
		
			ArrayList<Article> articles = null;
			
			
			try {
				articles = DaoArticle.getArticles();
				if(articles!=null) {
					//Guardo en String lista de usuarios
					StringBuilder tdUser = new StringBuilder();
					StringBuilder tdAdmin = new StringBuilder();
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
					response.setContentType("text/html;charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					
					boolean isAdmin = DaoUser.isUserAdmin(nick);
					if(isAdmin) {
						response.getWriter().append(htmlPart1 + buttonAdmin + htmlPart2Admin + tdAdmin.toString() + htmlPart3);
					}else {
						response.getWriter().append(htmlPart1 + htmlPart2User + tdUser.toString() + htmlPart3);
					}
					

				}else {
					boolean isAdmin = DaoUser.isUserAdmin(nick);
					//Mostrar tabla sin artículos
					response.setContentType("text/html;charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					if(isAdmin) {
						response.getWriter().append(htmlPart1 + buttonAdmin + htmlPart2Admin + htmlPart3);
					}else {
						response.getWriter().append(htmlPart1 + htmlPart2User + htmlPart3);
					}
					
				}
			}catch(Exception e) {
				String message = e.getMessage();
				response.sendRedirect("error.jsp?msg=" + message);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
