package com.jacaranda.control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.jacaranda.model.User;
import com.jacaranda.model.UserException;


public class DaoUser {
	
	

	public DaoUser() {
		
	}
	
	

	public static User getUser(String nick) throws DaoException, HibernateException {
		Session session = ConnectionDB.getSession();
		User result = (User)session.get(User.class, nick);
		if(result == null) {
			throw new DaoException("Usuario no encontrado");
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
		}catch (HibernateException e) {
			throw new DaoException("42 - Error en la conexion con la BBDD");
		}catch (DaoException f) { 
			throw new DaoException("44 - " + f.getMessage());
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
		}catch (HibernateException e) {
			throw new DaoException("58 - Error en la conexion con la BBDD");
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
		}catch (HibernateException e) {
			throw new DaoException("Error en la conexion con la BBDD");
		}catch(UserException f) {
			throw new DaoException(f.getMessage());
		}catch(Exception g) {
			if(session.isOpen()) {
				session.getTransaction().rollback();
			}
			throw new DaoException("Usuario repetido.");
		}
		return result;
	}
}
