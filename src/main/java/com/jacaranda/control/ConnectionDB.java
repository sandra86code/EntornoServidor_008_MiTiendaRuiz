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
	
	/**
	 * Método que obtiene la sesión en la base de datos
	 * @return la sesión en la base de datos
	 */
	public static Session getSession() {
		return session;
	}
	
}
