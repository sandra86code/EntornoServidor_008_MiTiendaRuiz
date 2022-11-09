package com.jacaranda.control;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import com.jacaranda.model.User;
import com.jacaranda.model.UserException;

/**
 * Clase que interactúa con la base de datos
 * @author chisela
 */

public class DaoUser {
	
	
	/**
	 * Constructor vacío
	 */
	public DaoUser() {
		
	}
	
	
	
	/**
	 * Método que obtiene un objeto User de la base de datos a partir de su código de usuario
	 * @param nick código de usuario
	 * @return User cuyo userCod sea el mismo que se ha pasado por parámetro
	 * @throws DaoException lanza la excepción cuando no exista dicho usuario en la base de datos
	 */
	public static User getUser(String nick) throws DaoException {
		Session session = ConnectionDB.getSession();
		User result = (User)session.get(User.class, nick);
		if(result == null) {
			throw new DaoException("Usuario no encontrado");
		}
		return result;
	}
	
	/**
	 * Método para validar el login, comprueba si el nick del usuario y contraseña son correctos
	 * @param nick el nick del usuario
	 * @param encriptedPassword contraseña 
	 * @return boolean true si coinciden los datos con los de la bbdd, false si no 
	 * @throws DaoException 
	 */
	public static boolean userIsValid(String nick, String encriptedPassword) throws DaoException {
		boolean result = false;
		try {
			User u = getUser(nick);
			if(u.getPassword().equals(encriptedPassword)) {
				result = true;
			}
		}catch(DaoException e) {
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
		Session session = ConnectionDB.getSession();
		try {
			User u = new User(nick, encriptedPassword, name, surname, birthDate, sex, admin);
			session.getTransaction().begin();
			session.save(u);
			session.getTransaction().commit();
		}catch(UserException e) {
			throw new DaoException(e.getMessage());
		}catch(Exception e) {
			throw new DaoException("Error en la insercion");
		}
		return result;
	}
}
