package com.jacaranda.control;

import java.util.ArrayList;

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
			Session session = ConnectionDB.getSession();
			category = (Category) session.get(Category.class, cod);
			if(category==null) {
				throw new DaoException("No existe el articulo");
			}
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
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
		}catch(DaoException e) {
			throw new DaoException(e.getMessage());
		}
		
		return categories;          
	}
	
}
