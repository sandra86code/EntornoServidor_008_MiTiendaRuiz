package com.jacaranda.control;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.jacaranda.model.Category;

public class DaoCategory {

	
	public DaoCategory() {
		super();
	}
	
	
	public static Category getCategory(int cod) throws DaoException {
		Category category = null;
		try {
			ConnectionDB connection = new ConnectionDB();
			Session session = connection.getSession();
			category = session.get(Category.class, cod);
			session.close();
			if(category==null) {
				throw new DaoException("No existe la categoria");
			}
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}
		return category;
	}
	
	
	public static List<Category> getCategories() throws DaoException {
		ArrayList<Category> categories = null;
		Session session = null;
		try {
			ConnectionDB connection = new ConnectionDB();
			session = connection.getSession();
			String hql = "SELECT cod, name, description FROM category c;";
			Query<Category> query = session.createNativeQuery(hql, Category.class);
			categories = (ArrayList<Category>) query.getResultList();
			session.close();
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}catch(Exception f) {
			if(session!=null && session.isOpen()) {
				session.close();
			}
			throw new DaoException(f.getMessage());
		}
		return categories;          
	}
	
}
