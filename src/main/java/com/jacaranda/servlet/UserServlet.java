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

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/checkuser")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nick = request.getParameter("nick");
		String password = request.getParameter("password");
		
		String htmlPart1 = "<html>"
				+ "<head>"
				+ "<title>Appify</title>"
				+ "<link rel=\"stylesheet\" ";
		String cssError = "href=\"css/error_style.css\">";
		String cssTable = "href=\"css/table_style.css\">";
		String htmlPart2 = "</head>"
				+ "<body>"
				+ "<div class=\"wrapper\">"
				+ "<div class=\"close_session\">";
		String tableCloseButton = "<input type=\"button\" onclick=\"location.href='index.jsp';\" value=\"Volver al login\" />";
		String htmlPart3 = "</div>"
				+ "<div class=\"page_name\">"
				+ "<img src=\"img/logo.png\"/>"
				+ "</div>"
				+ "<div class=\"user_name\">";
		String htmlPart4 = "</div>"
				+ "<div class=\"content\">";
		String htmlErrorPart1 = "<div class=\"error\">"
				+ "<h1>Oops!</h1>";			
		String htmlErrorPart2 = "<input type=\"button\" onclick=\"location.href='index.jsp';\" value=\"Volver al login\" />"
				+ "</div>"
				+ "</div>"
				+ "</div>"
				+ "</body>"
				+ "</html>";
		String htmlTable = "<div class=\"table\">"
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
		String htmlTablePart2 = "</tbody></table></div></div></div></body></html>";
		
		if(nick==null || nick.isBlank() || nick.isEmpty() || password == null || password.isBlank() || password.isEmpty() ) {
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(htmlPart1 + cssError + htmlPart2 + htmlPart3 + htmlPart4 + htmlErrorPart1 +
					"<h2>Error. Los campos no pueden estar vacíos</h2>" + htmlErrorPart2);
		}else {
			String encriptedPassword = DigestUtils.md5Hex(password);
			try {
				if(DaoUser.userIsValid(nick, encriptedPassword)) {
					if(DaoUser.isUserAdmin(nick)) {
						response.sendRedirect("addArticle.jsp?nick=" + nick);
					}else {
						ArrayList<Article> articles = null;

						articles = DaoArticle.getArticles();
						if(articles!=null) {
							StringBuilder td = new StringBuilder();
							Iterator<Article> it = articles.iterator();
							while(it.hasNext()){
								Article a = it.next();
								td.append("<tr>"
										+ "<td>" + a.getCod() + "</td>"
										+ "<td>" + a.getName() + "</td>"
										+ "<td>" + a.getDescription() + "</td>"
										+ "<td>" + a.getPrice() + " €</td>"
										+ "<td>" + a.getCategory().getName() + "</td>"
										+ "<td><a href=\"buyProduct.jsp?cod=" + a.getCod() + "\"><img src=\"img/shop.png\" class=\"shop\"/></td>"
										+ "</tr>");	
							}
							response.setContentType("text/html;charset=UTF-8");
							response.setCharacterEncoding("UTF-8");
							
//							HttpSession session = request.getSession();
//							String isSession = (String) session.getAttribute("login");
//							String userSession = (String) session.getAttribute("nick");
							
							
							response.getWriter().append(htmlPart1 + cssTable + htmlPart2 + tableCloseButton + htmlPart3 + 
									"<p>Usuario: " + request.getParameter("nick") + "</p>" + htmlPart4 + htmlTable + td.toString() + "</tbody></table></div></div></div></body></html>");
						}else {
							response.setContentType("text/html;charset=UTF-8");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().append(htmlPart1 + cssTable + htmlPart2 + tableCloseButton + htmlPart3 + 
									"<p>Usuario: " + request.getParameter("nick") + "</p>" + htmlPart4 + htmlTable + "</table></div></div></div></body></html>");
						}
					}
				}else {
					response.setContentType("text/html;charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().append(htmlPart1 + cssError + htmlPart2 + htmlPart3 + htmlPart4 + htmlErrorPart1 +
							"<h2>La contraseña no es correcta</h2>" + htmlErrorPart2);
				}
			}catch(DaoException e) {
				String message = e.getMessage();
				response.getWriter().append(htmlPart1 + cssError + htmlPart2 + htmlPart3 + htmlPart4 + htmlErrorPart1 +
						"<h2>" + message + "</h2>" + htmlErrorPart2);
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
