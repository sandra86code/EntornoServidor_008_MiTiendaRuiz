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
    
    protected String getArticlesUser() throws DaoException {
		ArrayList<Article> articles = null;
		StringBuilder tdUser = new StringBuilder();
		try {
			articles = (ArrayList<Article>) DaoArticle.getArticles();
			if(articles!=null) {
				Iterator<Article> it = articles.iterator();
				while(it.hasNext()){
					Article a = it.next();
					tdUser.append("<div class=\"gallery-item\">"
							+ "<div class=\"gallery-item-text\">"
							+ "<p><span class=\"article-name\"> " + a.getName() + "</span></p>"
							+ "<p><span class=\"article-info\">Descripción: </span>" + a.getDescription() + "</p>"
							+ "<p><span class=\"article-info\">Categoría: </span>" + a.getCategory().getName() + "</p>"
							+ "<p><span class=\"article-info\">Precio: </span>" + a.getPrice() + "€</p>"
							+ "</div>"
							+ "<div class=\"gallery-item-image\">");
					if(a.getImage()!=null) {
						tdUser.append("<img src=\"./download-image?cod=" + a.getCod() + "\"/>");
					}else {
						tdUser.append("<img src=\"img/nophoto.jpg\"/>");
					}
					tdUser.append("</div>"
							+ "<div class=\"gallery-item-buy\">");
					if(a.getQuantity()==0) {
						tdUser.append("</div></div>");
					}else {
						tdUser.append("<form class=\"add-article-to-cart\" action=\"add-article-to-cart.jsp\" method=\"post\">"
								+ "<label for=\"quantity\"><b>Cantidad</b></label>"
								+ "<input type=\"number\" name=\"quantity\" min=\"1\" max=\"" + a.getQuantity() + "\" required />"
								+ "<input type=\"hidden\" name=\"id_article\" value=\"" + a.getCod() + "\">"
								+ "<button type=\"submit\" class=\"login_button\">Añadir al carrito</button>"
								+ "</form>"
								+ "</div></div>");
					}
				}
			}	
		}catch(Exception e) {
			throw new DaoException(e.getMessage());
		}
		return tdUser.toString();
    }
    
    protected String getArticlesAdmin() throws DaoException {
    	ArrayList<Article> articles = null;
    	StringBuilder tdAdmin = new StringBuilder();
    	try {
    		articles = (ArrayList<Article>) DaoArticle.getArticles();
    		if(articles!=null) {
    			Iterator<Article> it = articles.iterator();
    			while(it.hasNext()){
    				Article a = it.next();
    				tdAdmin.append("<tr>"
    						+ "<td>" + a.getCod() + "</td>"
    						+ "<td>" + a.getName() + "</td>"
    						+ "<td>" + a.getDescription() + "</td>"
    						+ "<td>" + a.getPrice() + " €</td>"
    						+ "<td>" + a.getQuantity() + "</td>"
    						+ "<td>" + a.getCategory().getName() + "</td>"
    						+ "<td>");
    				if(a.getImage()!=null) {
    					tdAdmin.append("<img class='image' src=\"./download-image?cod=" + a.getCod() + "\"/></td>");
    				}else {
    					tdAdmin.append("<img class='image' src=\"img/nophoto.jpg\"/></td>");
    				}
    				tdAdmin.append("<td class='form'>"
    						+ "<form method='post' name='edit-article' action='edit-article.jsp' id='edit-article'>"
    						+ "<input type='hidden' name='cod' value='" + a.getCod() + "'>"
    						+ "<button type='submit' class=\"btn\"><i class=\"fa fa-edit\"></i></button>"
    						+ "</form>"
    						+ "</td>"
    						+ "<td class='form'>"
    						+ "<form method='post' name='delete-article' action='delete-article.jsp' id='delete-article'>"
    						+ "<input type='hidden' name='cod' value='" + a.getCod() + "'>"
    						+ "<button type='submit' class=\"btn\"><i class=\"fa fa-trash\"></i></button>"
    						+ "</form>"
    						+ "</td>"
    						+ "</tr>");
    			}
    		}
    	}catch(Exception e) {
			throw new DaoException(e.getMessage());
		}
    	return tdAdmin.toString();
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
				response.sendRedirect("error.jsp?redirect=index&msg=Nick incorrecto"); //Datos de logueo incorrectos según restricciones
			}else {
				encriptedPassword = DigestUtils.md5Hex(password);
			}
		}else { //Existe una session abierta
			isAdmin = Boolean.parseBoolean(admin);
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
					response.sendRedirect("error.jsp?redirect=index&msg=Password incorrecto"); //Password no corresponde con la bbdd
					return;
				}
			}catch(Exception e) {
				String message = e.getMessage();
				response.sendRedirect("error.jsp?redirect=index&msg=" + message);
				return;
			}
		}
		
		//Recupero el carrito de la sesión y si no existe (primera vez que entra), lo añado a la sesión
		Cart myCart = (Cart) session.getAttribute("cart");
		if(myCart==null) {
			try {
				myCart = new Cart();
				session.setAttribute("cart", myCart);
			} catch (Exception e) {
				String message = e.getMessage();
				response.sendRedirect("error.jsp?redirect=index&msg=" + message);
			}
		}
		
		//Variables para la página web
		String htmlPart1 = "<html>"
				+ "<head>"
				+ "<title>Appify</title>"
				+ "<link rel=\"stylesheet\" href=\"css/grid.css\">"
				+ "<link rel=\"stylesheet\" href=\"css/showarticles_style.css\">"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">"
				+ "</head>"
				+ "<body>"
				+ "<div class=\"wrapper\">"
				+ "<div class=\"close_session\">"
				+ "<a href='index.jsp'><img src='img/close-session.png' class='close-button'/></a>"
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
		String titleUser = "<div class='admin_button user'>"
				+ "<h1>Nuestros productos</h1>"
				+ "</div>";
		String buttonAdmin = "<div class=\"admin_button admin\">"
				+ "<input type=\"button\" onclick=\"location.href='add-article.jsp';\" value=\"+ nuevo artículo\" />"
				+ "</div><br>";
		String htmlPart3User = "<div class=\"grid-container grid-container-user\">";
		String htmlPart3Admin = "<div class=\"grid-container grid-container-admin\">"
				+ "<table>"
				+ "<thead>"
				+ "<tr>"
				+ "<th>Cod</th>"
				+ "<th>Nombre</th>"
				+ "<th>Descripción</th>"
				+ "<th>Precio</th>"
				+ "<th>Cantidad</th>"
				+ "<th>Categoría</th>"
				+ "<th>Imagen</th>"
				+ "<th colspan='2'>Acciones</th>"
				+ "</tr>"
				+ "</thead>"
				+ "<tbody>";
		String htmlPart4 = "</div></div></div><script src=\"script/delete-article.js\"></script></body></html>";
		
		try {
			if(isAdmin) {
				String tdAdmin = getArticlesAdmin();
				response.getWriter().append(htmlPart1 + htmlPart2 + buttonAdmin + htmlPart3Admin + tdAdmin + htmlPart4);
			}else {
				String tdUser = getArticlesUser();
				response.getWriter().append(htmlPart1 + chart + htmlPart2 + titleUser + htmlPart3User + tdUser + htmlPart4);
			}
		}catch(Exception e) {
			String message = e.getMessage();
			response.sendRedirect("error.jsp?redirect=index&msg=" + message);
			
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
