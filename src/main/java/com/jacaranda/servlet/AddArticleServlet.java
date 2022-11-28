package com.jacaranda.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Blob;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.hibernate.Hibernate;
import com.jacaranda.control.ConnectionDB;
import com.jacaranda.control.DaoArticle;
import com.jacaranda.control.DaoCategory;
import com.jacaranda.model.Category;

/**
 * Servlet implementation class AddArticleServlet
 */
@WebServlet("/add-article")
@MultipartConfig(maxFileSize=1024*1024*50,
				maxRequestSize=1024*1024*100)
public class AddArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddArticleServlet() {
        super();
    }
    
    protected String getFileName(Part p){
    	String GUIDwithext = Paths.get(p.getSubmittedFileName()).getFileName().toString();
    	return GUIDwithext;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		String nick = (String) session.getAttribute("nick");
		String admin = (String) session.getAttribute("admin");
		if(login != null && nick !=null && login.equals("true")){
			if(admin.equals("true")) {
				String cId = request.getParameter("category");
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				String p = request.getParameter("price");
				String q = request.getParameter("quantity");
				
				if(cId == null || name == null || description == null || p == null || q==null) {
					response.sendRedirect("error.jsp?redirect=add-article&msg='Valores incorrectos. Vuelve a intentarlo'");
				}else {
					try {
						Category category = DaoCategory.getCategory(Integer.parseInt(cId));
						double price = Double.parseDouble(p);
						int quantity = Integer.parseInt(q);
						//Consigo la foto del formulario mediante el objeto Part
						Part part = request.getPart("image");
						String fileName = getFileName(part);
						Blob blob = null;
						if(!fileName.equals("")) {
							//Conseguimos los bytes de la imagen
							InputStream fileContent = part.getInputStream();
							//Conseguimos un creador de datos blog para esta sesión y creamos un blob con la imagen
							ConnectionDB connection = new ConnectionDB();
							blob = Hibernate.getLobCreator(connection.getSession()).createBlob(fileContent, 
									fileContent.available());
							
						}
							//Guardo el artículo
							boolean result = DaoArticle.addArticle(name, description, price, quantity, 
									blob, category);
						if(result) {
							response.sendRedirect("show-articles");
						}
					}catch (Exception e) {
						String message = e.getMessage();
						response.sendRedirect("error.jsp?redirect=add-article&msg=" + message);
					}
				}
			}else {
				response.sendRedirect("error.jsp?redirect=show-articles&msg=No eres administrador");
			}
		}else {
			response.sendRedirect("error.jsp?redirect=index&msg=No te has autenticado");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
