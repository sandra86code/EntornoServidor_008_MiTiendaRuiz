package com.jacaranda.control;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Método que crea una sesión con la base de datos
 * @author sandra
 *
 */
public class ConnectionDB {
	
	/**
	 * Atributos estáticos porque son compartidos por todos los objetos
	 */
	private static StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
	private static SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
	private static Session session = sf.openSession();
	

	public static Session getSession() throws DaoException {
		try {
			return session;
		}catch(Exception e) {
			throw new DaoException("Error de conexión con la base de datos. Consulte con el administrador.");
		}
	}
	
}
