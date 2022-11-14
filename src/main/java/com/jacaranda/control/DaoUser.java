package com.jacaranda.control;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import com.jacaranda.model.User;
import com.jacaranda.model.UserException;


public class DaoUser {
	
	

	public DaoUser() {
		
	}
	
	

	public static User getUser(String nick) throws DaoException {
		User result = null;
		try {
			Session session = ConnectionDB.getSession();
			result = (User)session.get(User.class, nick);
			if(result == null) {
				throw new DaoException("Usuario no encontrado");
			}
		}catch(Exception e) {
			throw new DaoException(e.getMessage());
		}
		
		return result;
	}
	

	public static boolean userIsValid(String nick, String encriptedPassword) throws DaoException {
		boolean result = false;
		try {
			User u = getUser(nick);
			if(u.getPassword().equals(encriptedPassword)) {
				result = true;
			}
		}catch(Exception e) {
			throw new DaoException(e.getMessage());
		}
		return result;
	}
	
	
	public static boolean isUserAdmin(String nick) throws DaoException {
		boolean result = false;
		try {
			User u = getUser(nick);
			if(u.isAdmin()) {
				result = true;
			}
		}catch(DaoException e) {
			throw new DaoException("El usuario es nulo.");
		}
		return result;
	}
	
	public static boolean addUser(String nick, String encriptedPassword, String name, String surname, 
			LocalDate birthDate, char sex, boolean admin) throws DaoException {
		boolean result = false;
		Session session = null;
		try {
			session = ConnectionDB.getSession();
			User u = new User(nick, encriptedPassword, name, surname, birthDate, sex, admin);
			session.getTransaction().begin();
			session.save(u);
			session.getTransaction().commit();
		}catch (DaoException e) {
			throw new DaoException(e.getMessage());
		}catch(UserException e) {
			throw new DaoException(e.getMessage());
		}catch(Exception e) {
			session.getTransaction().rollback();
			throw new DaoException("Usuario repetido.");
		}
		return result;
	}
}
