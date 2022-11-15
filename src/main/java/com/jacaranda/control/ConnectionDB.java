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
	private static SessionFactory sf = null;
	private static Session session;
	
	

	
	public static Session getSession() throws DaoException {
		if(sf==null) {
			try {
				sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
			}catch(Exception e) {
				throw new DaoException("Error de base de datos");
			}
		}
		if(session ==null || !session.isOpen()) {
			session = sf.openSession();
		}
		return session;
	}
	
}
