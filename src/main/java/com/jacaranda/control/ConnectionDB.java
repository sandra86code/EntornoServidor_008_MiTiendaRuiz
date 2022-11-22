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
	
	
	private StandardServiceRegistry sr;
	private SessionFactory sf;
	private Session session;
	
	

	
	public ConnectionDB() throws DaoException {
		super();
		this.sr = new StandardServiceRegistryBuilder().configure().build();
		if(this.sr==null) {
			throw new DaoException("Error en la base de datos");
		}
		this.sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		if(this.sf==null) {
			throw new DaoException("Error en la base de datos");
		}
		this.session = sf.openSession();
		if(this.session==null) {
			throw new DaoException("Error en la base de datos");
		}
	}

	
	public boolean closeSession() throws DaoException {
		boolean result = false;
		if(this.session!=null && this.session.isOpen()) {
			this.session.close();
			result = true;
		}else {
			throw new DaoException("Error en la base de datos");
		}
		return result;
			
	}

	
	public Session getSession() {
		return session;
	}
}
