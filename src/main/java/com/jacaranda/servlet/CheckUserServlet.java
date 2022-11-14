package com.jacaranda.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.jacaranda.control.DaoException;
import com.jacaranda.control.DaoUser;

/**
 * Servlet implementation class CheckUserServlet
 */
@WebServlet("/CheckUser")
public class CheckUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nick = request.getParameter("nick");
		String password = request.getParameter("password");
		if(nick==null || nick.isBlank() || nick.isEmpty() || password == null || password.isBlank() || password.isEmpty() ) {
			response.sendRedirect("error.jsp?msg=Datos incorrectos");
		}else {
			String encriptedPassword = DigestUtils.md5Hex(password);
			try {
				if(DaoUser.userIsValid(nick, encriptedPassword)) {
					response.sendRedirect("ShowArticles?nick=" + nick);
				}else {
					response.sendRedirect("error.jsp?msg=La contraseña no es correcta");
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
