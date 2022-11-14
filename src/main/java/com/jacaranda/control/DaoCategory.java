package com.jacaranda.control;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.jacaranda.model.Category;

public class DaoCategory {

	public DaoCategory() {
		super();
		
	}
	
	public static Category getCategory(int cod) throws DaoException, HibernateException {
		Session session = ConnectionDB.getSession();
		Category category = (Category) session.get(Category.class, cod);
			if(category==null) {
				throw new DaoException("No existe el articulo");
			}
		return category;
	}
	
	public static ArrayList<Category> getCategories() throws DaoException {
		ArrayList<Category> categories = null;
		try {
			Session session = ConnectionDB.getSession();
			String hql = "SELECT cod, name, description FROM category c;";
			Query<Category> query = session.createNativeQuery(hql, Category.class);
			categories = (ArrayList<Category>) query.getResultList();
		}catch(HibernateException e) {
			throw new DaoException(e.getMessage());
		}catch(Exception f) {
			throw new DaoException(f.getMessage());
		}
		
		return categories;          
	}
	
}
