package com.jacaranda.control;


import java.net.ConnectException;
import java.sql.SQLException;

import org.hibernate.HibernateException;
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
	private static Session session;
	
	
//	public static SessionFactory getSessionFactory() {
//		sf 
//		if(sf==null) {
//			
//		}
//		return sf;
//	}
	
	public static Session getSession() throws HibernateException {
		if(sf==null) {
			throw new HibernateException("Error en la conexión con la base de datos");
		}
		if(session ==null || !session.isOpen()) {
			session = sf.openSession();
		}
		return session;
	}
	
}
