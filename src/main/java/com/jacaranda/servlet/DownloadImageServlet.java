package com.jacaranda.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
 * Servlet implementation class DownloadImageServlet
 */
@WebServlet("/download-image")
public class DownloadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		String nick = (String) session.getAttribute("nick");
		if(login != null && nick !=null && login.equals("true")){
			//Id del artículo que llama al servlet
			String cod = request.getParameter("cod");
			if(cod!=null) {
				try {
					//Recupero blob de la imagen de la BD
					Blob img = DaoArticle.getArticle(Integer.parseInt(cod)).getImage();
					byte[] blobFiles = img.getBytes(1,  (int) img.length());
					//Conseguimos el outputstream para imprimir datos binarios en la respuesta
					ServletOutputStream outputStream = response.getOutputStream();
					//Escribimos el array de bytes en el servlet
					outputStream.write(blobFiles);
				}catch(Exception e) {
					String message = e.getMessage();
					response.sendRedirect("error.jsp?msg='" + message + "'");
			}
				
		}else {
			response.sendRedirect("error.jsp?msg='No te has autenticado'");
		}
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
