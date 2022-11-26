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
import com.jacaranda.model.Article;
import com.jacaranda.model.Category;

/**
 * Servlet implementation class EditArticleServlet
 */
@WebServlet("/edit-article")
@MultipartConfig(maxFileSize=1024*1024*50,
maxRequestSize=1024*1024*100)
public class EditArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditArticleServlet() {
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
				String cod = request.getParameter("cod_article");
				String cId = request.getParameter("category_article");
				String name = request.getParameter("name_article");
				String description = request.getParameter("description_article");
				String p = request.getParameter("price_article");
				String q = request.getParameter("quantity_article");
				
				if(cod!=null && cId != null && name != null && description != null && p != null && q!=null) {
					try {
						int idArticle = Integer.parseInt(cod);
						Category category = DaoCategory.getCategory(Integer.parseInt(cId));
						double price = Double.parseDouble(p);
						int quantity = Integer.parseInt(q);
						Article modifiedArticle = new Article(name, description, price, quantity, category);
						//Consigo la foto del formulario mediante el objeto Part
						Part part = request.getPart("image_article");
						String fileName = getFileName(part);
						if(!fileName.equals("")) {
							//Conseguimos los bytes de la imagen
							InputStream fileContent = part.getInputStream();
							//Conseguimos un creador de datos blog para esta sesión y creamos un blob con la imagen
							ConnectionDB connection = new ConnectionDB();
							Blob blob = Hibernate.getLobCreator(connection.getSession()).createBlob(fileContent, 
									fileContent.available());
							//Guardo la imagen en el artículo
							modifiedArticle.setImage(blob);
						}
						//Modifico el artículo en la BBDD
						DaoArticle.editArticle(idArticle, modifiedArticle);
						response.sendRedirect("show-articles");

					}catch (Exception e) {
						String message = e.getMessage();
						response.sendRedirect("error.jsp?redirect=edit-article&msg=" + message);
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
