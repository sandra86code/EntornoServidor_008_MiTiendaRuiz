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

import com.jacaranda.cart.Cart;
import com.jacaranda.cart.CartExpection;
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
		}
		Cart myCart = (Cart) session.getAttribute("cart");
		if(myCart==null) {
			User user = null;
			try {
				myCart = new Cart();
				session.setAttribute("cart", myCart);
			} catch (Exception e) {
				String message = e.getMessage();
				response.sendRedirect("error.jsp?msg=" + message);
			}
		}
		//Variables para la página web
		String htmlPart1 = "<html>"
				+ "<head>"
				+ "<title>Appify</title>"
				+ "<link rel=\"stylesheet\" href=\"css/articles_style.css\">"
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
				+ "<div class=\"usericon\"><img class=\"icon\" src=\"img/user.png\"/><p>" + nick + "</p></div>";
		String chart = "<div class=\"charticon\">"
				+ "<a href=\"show-cart.jsp\"><img class=\"icon\" src=\"img/trolley.png\"/></a>"
				+ "<p>" + myCart.getQuantityArticles() + "</p>"
				+ "</div>";
		String htmlPart2 = "</div>"
					+ "<div class=\"content\">";
		String buttonAdmin = "<div class=\"admin_button\">"
				+ "<input type=\"button\" onclick=\"location.href='add-article.jsp';\" value=\"Añadir artículo\" />"
				+ "</div><br>";
		String htmlPart3 = "<div class=\"grid-container\">";
		String htmlPart4 = "</div></div></div></body></html>";
		
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
					return;
				}
			}catch(Exception e) {
				String message = e.getMessage();
				if(message.contains("%")) {
					message = "Error en la base de datos";
				}
				response.sendRedirect("error.jsp?msg=" + message);
				return;
			}
		}
		
		
		
		//Guardo los artículos
		ArrayList<Article> articles = null;
		StringBuilder td = new StringBuilder();
		try {
			articles = (ArrayList<Article>) DaoArticle.getArticles();
			if(articles!=null) {
				Iterator<Article> it = articles.iterator();
				while(it.hasNext()){
					Article a = it.next();
					td.append("<div class=\"gallery-item\">"
							+ "<div class=\"text\">"
							+ "<p><span class=\"article-name\"> " + a.getName() + "</span></p>"
							+ "<p><span class=\"article-info\">Descripción: </span>" + a.getDescription() + "</p>"
							+ "<p><span class=\"article-info\">Categoría: </span>" + a.getCategory().getName() + "</p>"
							+ "<p><span class=\"article-info\">Precio: </span>" + a.getPrice() + "€</p>"
							+ "</div>"
							+ "<div class=\"image\">");
					if(a.getImage()!=null) {
						td.append("<img src=\"./download-image?cod=" + a.getCod() + "\"/>");
					}else {
						td.append("<img src=\"img/nophoto.jpg\"/>");
					}
					td.append("</div>"
							+ "<div class=\"buy\">");
					if(a.getQuantity()==0) {
						td.append("</div></div>");
					}else {
						if(!isAdmin) {
							td.append("<form class=\"add-article-to-cart\" action=\"add-article-to-cart.jsp\" method=\"post\">"
									+ "<label for=\"quantity\"><b>Cantidad</b></label>"
									+ "<input type=\"number\" name=\"quantity\" min=\"1\" max=\"" + a.getQuantity() + "\" required />"
									+ "<input type=\"hidden\" name=\"id_article\" value=\"" + a.getCod() + "\">"
									+ "<button type=\"submit\" class=\"login_button\">Añadir al carrito</button>"
									+ "</form>"
									+ "</div></div>");
						}else {
							td.append("<div class=\"icons\">"
									+ "<a class=\"edit\" href=\"edit-article.jsp?cod='" + a.getCod() + "'\"><img src=\"img/edit.png\" class=\"shop\"/></a>"
									+ "<a href=\"delete-article.jsp?cod='" + a.getCod() + "'\"><img src=\"img/delete.png\" class=\"shop\"/></a>"
									+ "</div></div></div>");
						}
					}
				}
			}
			if(isAdmin) {
				response.getWriter().append(htmlPart1 + htmlPart2 + buttonAdmin + htmlPart3 + td.toString() + htmlPart4);
			}else {
				response.getWriter().append(htmlPart1 + chart + htmlPart2 + htmlPart3 + td.toString() + htmlPart4);
			}
		}catch(Exception e) {
			String message = e.getMessage();
			response.sendRedirect("error.jsp?msg=" + message);
			
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
