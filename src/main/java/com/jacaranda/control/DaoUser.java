package com.jacaranda.control;


import java.time.LocalDate;
import org.hibernate.Session;
import com.jacaranda.model.User;
import com.jacaranda.model.UserException;


public class DaoUser {
		

	public DaoUser() {
		super();
	}
	
	
	public static User getUser(String nick) throws DaoException {
		User user = null;
		try {
			ConnectionDB connection = new ConnectionDB();
			Session session = connection.getSession();
			user = session.get(User.class, nick);
			if(user == null) {
				throw new DaoException("Usuario no encontrado");
			}
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}
		return user;
	}


	public static boolean userIsValid(String nick, String encriptedPassword) 
			throws DaoException {
		boolean result = false;
		try {
			User u = getUser(nick);
			if(u.getPassword().equals(encriptedPassword)) {
				result = true;
			}
		}catch (DaoException e) { 
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
			throw new DaoException(e.getMessage());
		}
		return result;
	}
	
	
	public static boolean addUser(String nick, String encriptedPassword, String name, String surname, 
			LocalDate birthDate, char sex, boolean admin) throws DaoException {
		boolean result = false;
		Session session = null;
		try {
			ConnectionDB connection = new ConnectionDB();
			session = connection.getSession();
			User u = new User(nick, encriptedPassword, name, surname, birthDate, sex, admin);
			session.getTransaction().begin();
			session.save(u);
			session.getTransaction().commit();
			session.close();
			result = true;
		}catch (DaoException e) {
			throw new DaoException(e.getMessage());
		}catch(UserException f) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			throw new DaoException(f.getMessage());
		}catch(Exception g) {
			if(session!=null && session.isOpen()) {
				session.getTransaction().rollback();
				session.close();
			}
			throw new DaoException("Usuario repetido.");
		}
		return result;
	}
}
